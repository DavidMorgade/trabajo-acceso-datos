package xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

public class xmlParser {

    private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private NodeList nodeList;
    XPathFactory xPathFactory = XPathFactory.newInstance();
    private Document document;


    public static void main(String[] args) {

        xmlParser xmlParser = new xmlParser();

        Document document = xmlParser.getDocument();

        NodeList nodeList = xmlParser.getNodeList();

        System.out.println(nodeList.getLength());

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            System.out.println(element.getTextContent());
        }


    }

    public xmlParser() {
        this.createDocument();
    }

    private void createDocument() {
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.document = builder.parse("https://www.juntadeandalucia.es/datosabiertos/portal/dataset/91f3c95b-a2d2-4828-8880-99f8ea156d0e/resource/f7737217-65d6-4b00-bd10-2f82c69ae2f7/download/contratos-adjudicados-sep-24.xml");

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

        String expression = "//Cell/Data[@ss:Type='String' or @ss:Type='DateTime']";
        this.useXPath(expression, this.document);

    }

    private void useXPath(String expression, Document document) {
        XPath xpath = xPathFactory.newXPath();
        try {
            XPathExpression xPathExpression = xpath.compile(expression);
            this.nodeList = (NodeList) xPathExpression.evaluate(document, javax.xml.xpath.XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }


    public Document getDocument() {
        return this.document;
    }

    public NodeList getNodeList() {
        return this.nodeList;
    }
}
