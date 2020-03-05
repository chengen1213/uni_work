import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

import java.io.*;

public class XMLParser {

    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;

    public XMLParser() throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
    }

    public Document parse(String xml) throws SAXException, IOException {
        InputStream targetStream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        return builder.parse(targetStream);
    }
}
