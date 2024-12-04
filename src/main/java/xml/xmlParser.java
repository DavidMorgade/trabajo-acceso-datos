package xml;

import org.example.db.Contrato;
import org.example.db.db;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.util.ArrayList;

public class xmlParser {

    private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    XPathFactory xPathFactory = XPathFactory.newInstance();
    private Document document;
    ArrayList<Contrato> contratos = new ArrayList<>();
    static db db = new db();


    public static void main(String[] args) {

        xmlParser xmlParser = new xmlParser();

        Document document = xmlParser.getDocument();

        document.getDocumentElement().normalize();


        NodeList nodeList = document.getElementsByTagName("Data");

        String textContent;
        Contrato contrato = new Contrato();
        ArrayList<Object> data = new ArrayList<>();
        for (int i = 8; i < nodeList.getLength(); i++) {
            textContent = nodeList.item(i).getTextContent();
            if (textContent.contains("_") || textContent == "") {
                contrato.setProveedores_consultados(0);
                continue;
            }
            data.add(nodeList.item(i));
            if (textContent.toLowerCase().contains("menor") || textContent.toLowerCase().contains("abierto") || textContent.toLowerCase().contains("basado") || textContent.toLowerCase().contains("adjudicación") || textContent.toLowerCase().contains("negociado")) {
                continue;
            }

            // this need fix
            contrato.setNif(data.get(0).toString());
            contrato.setAdjudicatario(data.get(1).toString());
            contrato.setObjeto_generico(data.get(2).toString());
            contrato.setObjeto(data.get(3).toString());
            contrato.setFecha_adjudicacion(data.get(4).toString());
            contrato.setImporte(data.get(5).toString());
            System.out.println(textContent);
            contrato.setProveedores_consultados(Integer.parseInt(data.get(6).toString()));

            db.saveContrato(contrato);

            data.removeAll(data);
        }

        ArrayList<Contrato> contratos = db.getAllContratos();
        for (Contrato contrato1 : contratos) {
            System.out.println(contrato1.getAdjudicatario());
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
