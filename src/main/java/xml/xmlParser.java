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


    public xmlParser() {
        this.createDocument();
    }

    public void saveContratosToDB() {


        Document document = this.getDocument();

        document.getDocumentElement().normalize();


        NodeList nodeList = document.getElementsByTagName("Data");
        String textContent;
        Contrato contrato;
        ArrayList<Object> data = new ArrayList<>();
        for (int i = 8; i < nodeList.getLength(); i++) {
            textContent = nodeList.item(i).getTextContent();
            // Con este check nos aseguramos que el bucle se rompa y lea la siguiente fila de datos del XML
            // ya que no se encuentra bien formateado estos datos y nos rompe la lógica de la aplicación
            // el motivo es porque aparecen el nif junto al nombre en vez de estar separados por columnas
            if (textContent.equals("A25027145 SERVICIOS MICROINFORMÁTICOS, S.A.")) {
                i = i + 6;
                continue;
            }

            if (textContent.toLowerCase().contains("menor") || textContent.toLowerCase().contains("abierto") || textContent.toLowerCase().contains("adjudicación") || textContent.toLowerCase().contains("negociado")) {
                int proveedores_consultados;
                // Comprobamos que el campo "Proveedores Consultados" no esté vacío o contenga un guión o una raya
                if (data.get(6).equals("_") || data.get(6).equals("-") || data.get(6).equals("MATERIAL HOMOLOGADO")) {
                    proveedores_consultados = 0;
                } else {
                    proveedores_consultados = Integer.parseInt(data.get(6).toString());
                }
                contrato = new Contrato(data.get(0).toString(), data.get(1).toString(), data.get(2).toString(), data.get(3).toString(), data.get(4).toString(), data.get(5).toString(), proveedores_consultados);
                db.saveContrato(contrato);
                data.removeAll(data);
                continue;
            }
            data.add(textContent);
        }

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
