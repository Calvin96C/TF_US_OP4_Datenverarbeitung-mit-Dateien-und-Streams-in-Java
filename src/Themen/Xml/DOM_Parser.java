package Themen.Xml;

import org.w3c.dom.*;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;


public class DOM_Parser
{
    public static void main(String[] args)
    {

        parseXML();
        Entry.entries.values().forEach(System.out::println);
        writeXML();
    }

    private static void parseXML()
    {
        // Definiert eine Fabrik-API zur Erzeugung von DOM-Parsern:
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try
        {
            // optional, aber empfohlen:
            // Sichere Verarbeitung von XML, Vermeidung von Angriffen wie XML External Entities (XXE)
            // https://portswigger.net/web-security/xxe
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // Wir haben unserem XML Dokument eine Document Type Definition (DTD) gegeben.
            // Damit können wir das Dokument vor der Verarbeitung validieren.
            // https://wiki.selfhtml.org/wiki/XML/DTD
            //factory.setValidating(true);

            // Eine andere Variante zum Validieren von XML Dokumenten ist das XML Schema.
            factory.setSchema(SchemaFactory.newDefaultInstance().newSchema(new File("resources/input.xsd")));

            // Erzeugt einen neuen DocumentBuilder:
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Error Handling für die Validierung setzen:
            builder.setErrorHandler(new ErrorHandler()
            {
                @Override
                public void warning(SAXParseException exception)
                {
                    System.err.println(exception.getMessage());
                }

                @Override
                public void error(SAXParseException exception) throws SAXException
                {
                    System.err.println(exception.getMessage());
                    throw exception;
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException
                {
                    System.err.println(exception.getMessage());
                    throw exception;
                }
            });

            // Achtung auf richtigen Import! org.w3c.dom.*
            Document document = builder.parse("resources/input.xml");

            // Die DTD lässt sich aus dem document-Objekt abfragen.
            DocumentType docType = document.getDoctype();

            // optional, aber empfohlen:
            // https://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            document.getDocumentElement().normalize();

            System.out.println("Document Element: " + document.getDocumentElement().getNodeName());

            // Abfrage aller Knoten, die "entry" heißen:
            NodeList list = document.getElementsByTagName("entry");

            for (int i = 0; i < list.getLength(); i++)
            {
                // Jedes Element in der NodeLIst ist eine Node.
                Node node = list.item(i);

                // Handelt es sich um ein Element Node?
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    // Wenn ja, können wir den Knoten in ein Element casten.
                    Element element = (Element) node;

                    // Ausgabe der Kind-Knoten von diesem Element.
                    NodeList children = element.getChildNodes();
                    for (int j = 0; j < children.getLength(); j++)
                    {
                        Node childNode = children.item(j);
                        NodeList subList = childNode.getChildNodes();
                        for (int k = 0; k < subList.getLength(); k++)
                            System.out.println(subList.item(k));
                    }

                    // Fragt den Wert des Attributs ab und gibt String zurück.
                    String id = element.getAttribute("id");

                    // Abfrage des Kind-Knotens mit dem Namen "title".
                    // Der Typ ist auch hier ELEMENT_NODE
                    Node titleNode = element.getElementsByTagName("title").item(0);
                    System.out.println(titleNode.getNodeType());
                    String title = titleNode.getTextContent();

                    String description = element.getElementsByTagName("description").item(0).getTextContent();
                    String date = element.getElementsByTagName("date").item(0).getTextContent();

                    Node salaryNode = element.getElementsByTagName("salary").item(0);
                    String salary = salaryNode.getTextContent();

                    // getAttributes() liefert eine NodeMap zurück.
                    // Das heißt: Attribute werden so ebenfalls als Node dargestellt. Der NodeType ist hier ATTRIBUTE_NODE.
                    Node currencyNode = salaryNode.getAttributes().getNamedItem("currency");
                    System.out.println(currencyNode.getNodeType());
                    String currency = currencyNode.getNodeValue();

                    // Da wir wissen, dass es sich bei dem Knoten um ein Attribute Node handelt, können wir auch in Attr casten.
                    Attr currencyNodeAttribute = (Attr) currencyNode;
                    currency = currencyNodeAttribute.getValue();

                    // Objekte der Model-Klasse erzeugen:
                    new Entry(id, title, description, LocalDate.parse(date), Integer.parseInt(salary), currency);
                }
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void writeXML()
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Erstelle ein neues Document.
            Document document = builder.newDocument();

            // Erstelle das Wurzelelement des Dokuments:
            Element root = document.createElement("op4_xml");

            // Füge dem Wurzelelement die Kind-Elemente hinzu:
            for (Entry e : Entry.entries.values())
            {
                Element entry = document.createElement("entry");
                entry.setAttribute("id", e.getId());

                Element title = document.createElement("title");
                title.setTextContent(e.getTitle());
                entry.appendChild(title);

                Element description = document.createElement("description");
                description.setTextContent(e.getDescription());
                entry.appendChild(description);

                Element date = document.createElement("date");
                date.setTextContent(e.getDate().toString());
                entry.appendChild(date);

                Element salary = document.createElement("salary");
                salary.setAttribute("currency", e.getCurrency());
                salary.setTextContent(String.valueOf(e.getSalary()));
                entry.appendChild(salary);

                root.appendChild(entry);
            }

            // Füge das Wurzelelement dem Dokument hinzu.
            document.appendChild(root);

            // Um aus dem Dokument eine Datei zu erstellen, verwenden wir einen Transformer.
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Damit hat das Dokument Zeilenumbrüche für bessere Lesbarkeit.

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("resources/output.xml"));

            // Schreibe das Dokument in die Datei.
            transformer.transform(source, result);
        }
        catch (ParserConfigurationException | TransformerException e)
        {
            e.printStackTrace();
        }
    }
}
