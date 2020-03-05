import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.Socket;
import java.util.*;

class ServerClientThread extends Thread {

    Socket serverClient;
    LamportClock lamportClock;
    String contentServerId = null;
    Map<String, Feed> feeds;
    Map<String, Timer> timers;
    XMLParser xmlParser;
    TimerTask timerTask;

    ServerClientThread(Socket inSocket, LamportClock lamportClock, Map<String, Feed> feeds, Map<String, Timer> timers) throws Exception{
        this.serverClient = inSocket;
        this.lamportClock = lamportClock;
        this.feeds = feeds;
        this.timers = timers;
        this.xmlParser = new XMLParser();
        //delete the feed after 15 secs
        this.timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Content Server ID: " + contentServerId + " has been Deleted!!!");
                feeds.remove(contentServerId);
                timers.get(contentServerId).cancel();
                timers.remove(contentServerId);
                AggregationServer.saveState(feeds);
            }
        };
    }

    @Override
    //set the thread behavior, call the corresponding method according to the request content
    public void run() {
        StringBuilder response = new StringBuilder();
        DataInputStream inStream = null;
        BufferedWriter outStream = null;
        try {
            inStream = new DataInputStream(serverClient.getInputStream());
            outStream = new BufferedWriter(new OutputStreamWriter(serverClient.getOutputStream()));
            HttpParser httpParser = new HttpParser(inStream);
            httpParser.parseRequest();
            String method = httpParser.getMethod();
            if (method.equals("GET")) {
                System.out.println(" >> " + "Get a GET request!");
                doGET(httpParser, response);
            } else if (method.equals("PUT")){
                System.out.println(" >> " + "Get a PUT request!");
                doPUT(httpParser, response);
            } else {
                response.append(HttpParser.getHttpReply(400) + "\r\n"
                        + "Lamport-Time: " + lamportClock.getLamportTime() + "\r\n"
                        + "+\r\n");
            }

        } catch (Exception ex) {
            System.out.println(ex);
            response.setLength(0);
            response.append(HttpParser.getHttpReply(500) + "\r\n"
                    + "Lamport-Time: " + lamportClock.getLamportTime() + "\r\n"
                    + "+\r\n");
            try {
                outStream.write(response.toString());
                outStream.flush();
            } catch (IOException e) {
                System.out.println(e);
            }
        } finally {
            try {
                outStream.write(response.toString());
                outStream.flush();
                inStream.close();
                outStream.close();
                serverClient.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
    //get method, response with the latest feeds
    private void doGET(HttpParser httpParser, StringBuilder response) {
        int time = Integer.parseInt(httpParser.getHeader("Lamport-Time"));
        lamportClock.getLamportTime(time);
        //construct response content
        List<Feed> sorted = new ArrayList<>(feeds.values());
        Collections.sort(sorted);
        StringBuilder body = new StringBuilder();
        body.append("<?xml version='1.0' encoding='iso-8859-1' ?> ");
        for (Feed feed: sorted) {
            body.append(feed.getContent());
        }
        response.append(HttpParser.getHttpReply(200) + "\r\n"
                + "Content-type: text/xml, application/xml\r\n"
                + "Content-Length: " + body.length() + "\r\n"
                +"Lamport-Time: " + lamportClock.getLamportTime() + "\r\n"
                + "\r\n"
                + body.toString());
    }
    //put method, aggregate the feeds
    private void doPUT(HttpParser httpParser, StringBuilder response) {
        //identify the content sever by ID, if there's no ID, generate one
        String key = httpParser.getHeader("ID");
        if ("None".equals(key))
            key = UUID.randomUUID().toString();
        contentServerId = key;
        //add or update the feed to the map
        Feed feed = feeds.get(key);
        Timer timer = timers.get(key);
        if (feed == null) {
            feed = new Feed();
            feeds.put(key, feed);
            response.append(HttpParser.getHttpReply(201) + "\r\n");
        } else {
            response.append(HttpParser.getHttpReply(200) + "\r\n");
        }
        //set or reset the timer
        if (timer == null) {
            timer = new Timer();
            timers.put(key, timer);
            timer.schedule(timerTask, 15 * 1000, 15 * 1000);
        } else {
            System.out.println(timer + ", cancelled" + ", id = " + key);
            timer.cancel();
            timer = new Timer();
            timers.put(key, timer);
            timer.schedule(timerTask, 15 *1000, 15 * 1000);
        }
        //parse the content of the feed
        String body = httpParser.getBody();
        try {
            Document document = xmlParser.parse(body);
            int length = document.getElementsByTagName("title").getLength();
            if (length == 0) {
                response.setLength(0);
                response.append(HttpParser.getHttpReply(204) + "\r\n");
            } else {
                Element element = document.getDocumentElement();
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                DOMSource source = new DOMSource(element);
                StreamResult result = new StreamResult(new StringWriter());
                transformer.transform(source, result);
                String strObject = result.getWriter().toString();
                feed.setContent(strObject);
                int time = Integer.parseInt(httpParser.getHeader("Lamport-Time"));
                feed.setLamportTime(lamportClock.getLamportTime(time));
                AggregationServer.saveState(feeds);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setLength(0);
            response.append(HttpParser.getHttpReply(500) + "\r\n");
        }
        response.append("Lamport-Time: " + lamportClock.getLamportTime() + "\r\n"
                + "ID: " + key + "\r\n"
                + "\r\n");
    }
}