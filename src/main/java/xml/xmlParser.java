package xml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

public class xmlParser {

    private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    XPathFactory xPathFactory = XPathFactory.newInstance();
    private Document document;


    public static void main(String[] args) {

        xmlParser xmlParser = new xmlParser();

        Document document = xmlParser.getDocument();

        document.getDocumentElement().normalize();


        NodeList nodeList = document.getElementsByTagName("Data");

        for (int i = 8; i < nodeList.getLength(); i++) {
            String textContent = nodeList.item(i).getTextContent();
            if (textContent.contains("_")) {
                System.out.println("0");
                continue;
            }

            if (textContent.contains("MENOR")) {
                System.out.println("--------------------");
                continue;
            }

            System.out.println(nodeList.item(i).getTextContent());

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


    }


    public Document getDocument() {
        return this.document;
    }

}
