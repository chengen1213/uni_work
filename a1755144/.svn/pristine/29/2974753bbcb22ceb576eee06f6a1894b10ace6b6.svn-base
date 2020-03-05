import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class GETClient {

    private LamportClock lamportClock;
    private String fileName;

    public GETClient(String fileName) {
        this.lamportClock = new LamportClock();
        this.fileName = fileName;
    }
    //send get request to the aggregation server to retrieve the latest feeds
    public void GETRequest(String hostName, int port) throws Exception {
        Socket socket = new Socket(hostName, port);
        StringBuffer get = new StringBuffer();
        get.append("GET /atom.xml HTTP/1.1\r\n"
                + "User-Agent: ATOMClient/1/0\r\n"
                + "Lamport-Time: " + lamportClock.getLamportTime() + "\r\n"
                + "\r\n");
        DataInputStream inStream = new DataInputStream(socket.getInputStream());
        BufferedWriter outStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        outStream.write(get.toString());
        outStream.flush();
        HttpParser httpParser = new HttpParser(inStream);
        httpParser.parseResponse();
        int time = Integer.parseInt(httpParser.getHeader("Lamport-Time"));
        lamportClock.getLamportTime(time);
        String body = httpParser.getBody();
        System.out.println(body);
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(body);
        writer.close();
    }

    public static void main(String[] args) {
        //try 4 times when failing to connect to aggregation server
        final int limit = 4;
        String arg, fileName, hostName, input;
        int port;

        if (args.length == 2) {
            fileName = args[0];
            arg = args[1];
        } else if (args.length == 1) {
            fileName = args[0];
            arg = "a";
        } else {
            fileName = "default.txt";
            arg = "a";
        }
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

        while (true) {
            int counter = 1;
            GETClient client = new GETClient(fileName);
            while (counter < limit) {
                System.out.println("Sending request no " + counter);
                try {
                    client.GETRequest(hostName, port);
                    counter = limit;
                } catch (Exception e) {
                    counter++;
//                    e.printStackTrace();
                    if (counter < limit) {
                        System.out.println("Fail to connect to the aggregation server, retry after 5 seconds...");
                        try {
                            Thread.sleep(5000);
                        } catch (Exception ex) {
                            e.printStackTrace();
                        }
                    } else {
                      System.out.println("Retried " + (limit - 1) + " times, client program terminates.");
                    }
                }
            }

            if ("m".equals(arg)) {
                System.out.println("Enter R to retry or Q to quit!");
                input = scanner.next();
            } else {
                input = "q";
            }

            if (input.toLowerCase().equals("q"))
                break;
        }
    }
}
