import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ContentServer {

    public static int counter = 0;
    private String fileName, id, keyFileName;
    private LamportClock lamportClock;

    public ContentServer() {
        ContentServer.counter++;
        this.fileName = "content" + counter + ".xml";
        this.keyFileName = "key" + counter + ".txt";
        this.lamportClock = new LamportClock();
        //check if the key exist, if not, it's a newly start server
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(keyFileName)));
            String key = "";
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                key += line;
            }
            this.id = key;
        } catch (IOException e) {
            this.id = null;
        }
    }

    public void PUTRequest(String hostName, int port) throws Exception {
        String line;
        String body = "";
        //read feed from local file
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
        while ((line = bufferedReader.readLine()) != null) {
            body += line;
        }
        //construct request content
        StringBuffer put = new StringBuffer();
        put.append("PUT /atom.xml HTTP/1.1\r\n"
                + "User-Agent: ATOMClient/1/0\r\n"
                + "Content-type: text/xml, application/xml\r\n"
                + "Content-Length: " + body.length() + "\r\n"
                + "Lamport-Time: " + lamportClock.getLamportTime() + "\r\n"
                + "ID: " + (id != null ? id : "None") + "\r\n"
                + "\r\n"
                + body);
        Socket socket = new Socket(hostName, port);
        DataInputStream inStream = new DataInputStream(socket.getInputStream());
        BufferedWriter outStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        outStream.write(put.toString());
        outStream.flush();
        //parse response from aggregation server
        HttpParser httpParser = new HttpParser(inStream);
        httpParser.parseResponse();
        int time = Integer.parseInt(httpParser.getHeader("Lamport-Time"));
        //newly started server, the aggregation will assign an ID to it
        if (id == null) {
            id = httpParser.getHeader("ID");
            BufferedWriter writer = new BufferedWriter(new FileWriter(keyFileName));
            writer.write(id + "\r\n");
            writer.close();
        }
        lamportClock.getLamportTime(time);
        inStream.close();
        outStream.close();
        socket.close();
    }

    public void POSTRequest(String hostName, int port) throws Exception{
        String line;
        String body = "";
        //read feed from local file
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
        while ((line = bufferedReader.readLine()) != null) {
            body += line;
        }
        //construct request content
        StringBuffer put = new StringBuffer();
        put.append("POST /atom.xml HTTP/1.1\r\n"
                + "User-Agent: ATOMClient/1/0\r\n"
                + "Content-type: text/xml, application/xml\r\n"
                + "Content-Length: " + body.length() + "\r\n"
                + "Lamport-Time: " + lamportClock.getLamportTime() + "\r\n"
                + "ID: " + (id != null ? id : "None") + "\r\n"
                + "\r\n"
                + body);
        Socket socket = new Socket(hostName, port);
        DataInputStream inStream = new DataInputStream(socket.getInputStream());
        BufferedWriter outStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        outStream.write(put.toString());
        outStream.flush();
        //parse response from aggregation server
        HttpParser httpParser = new HttpParser(inStream);
        httpParser.parseResponse();
        inStream.close();
        outStream.close();
        socket.close();
    }

    public static void main(String[] args) {
        // write your code here
        String arg, hostName, input;
        int clients;
        if (args.length == 1)
            clients = Integer.parseInt(args[0]);
        else
            clients = 1;
        if (args.length == 2)
            arg = args[1];
        else
            arg = "a";
        int port;
        Scanner scanner = new Scanner(System.in);
        if ("m".equals(arg.toLowerCase())) {
            System.out.print("Enter host name: ");
            hostName = scanner.next();
            System.out.print("Enter port number: ");
            port = Integer.parseInt(scanner.next());
        } else {
            hostName = "127.0.0.1";
            port = 4567;
        }
        try {
            for (int i = 0; i < clients; i++) {
                ContentServer contentServer = new ContentServer();
                if ("p".equals(arg.toLowerCase())) {
                    contentServer.POSTRequest(hostName, port);
                    return;
                }
                while (true) {
                    contentServer.PUTRequest(hostName, port);
                    if ("m".equals(arg)) {
                        System.out.println("Enter S to send the new content or Q to leave");
                        input = scanner.next();
                    } else {
                        input = "q";
                    }
                    if (input.toLowerCase().equals("q")) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
