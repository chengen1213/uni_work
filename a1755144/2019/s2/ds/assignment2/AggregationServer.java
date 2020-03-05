import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class AggregationServer {

    ServerSocket serverSocket;
    LamportClock lamportClock;
    Map<String, Feed> feeds;
    Map<String, Timer> timers;

    public AggregationServer(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        lamportClock = new LamportClock();
        try {
            FileInputStream fileIn = new FileInputStream("feeds");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object obj = objectIn.readObject();
            feeds = (Map<String, Feed>) obj;
            timers = new HashMap<>();
            initialiseTimers();
        } catch (Exception e) {
            feeds = new HashMap<>();
            timers = new HashMap<>();
        }
    }
    //start the aggregation server and listen on the specified port
    public void run() {
        try {
            while (true) {
                Socket serverClient = serverSocket.accept();  //server accept the contentServerId connection request
                ServerClientThread sct = new ServerClientThread(serverClient, lamportClock, feeds, timers); //send  the request to a separate thread
                sct.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //restart the server, set timers on each feed
    private void initialiseTimers() {
        for (Map.Entry<String, Feed> entry : feeds.entrySet()) {
            String key = entry.getKey();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    feeds.remove(key);
                    timers.remove(key);
                }
            }, 15 * 1000, 15 * 1000);
            timers.put(key, timer);
        }
    }

    public static void main(String[] args) {

        int port;
        if (args.length == 1 && "m".equals(args[0].toLowerCase())) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the port number: ");
            port = Integer.parseInt(scanner.next());
        } else {
            port = 4567;
        }

        AggregationServer aggregationServer;
        try {
            aggregationServer = new AggregationServer(port);
            aggregationServer.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //save feeds to a file for restoring from failure
    public static void saveState(Map<String, Feed> feeds) {
        try {
            FileOutputStream fileOut = new FileOutputStream("feeds");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(feeds);
            objectOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
