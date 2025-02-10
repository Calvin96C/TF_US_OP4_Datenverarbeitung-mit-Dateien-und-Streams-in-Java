package Vorbereitung.Models;

import java.io.Serializable;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Buch implements Serializable
{
    //region Fields
    private int id;
    private String title;
    private String description;
    private String currency;
    private String date;
    private int price;
    //endregion

    //region Constructors
    public Buch()
    {
    }

    public Buch(int id, String title, String description, String currency, String date, int price)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.currency = currency;
        this.date = date;
        this.price = price;
    }
    //endregion

    //region Methods
    public static List<Buch> parseXML(String filePath) {
        List<Buch> buecherListe = new ArrayList<>();

        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList buchNodes = document.getElementsByTagName("Buch");

            for (int i = 0; i < buchNodes.getLength(); i++) {
                Node buchNode = buchNodes.item(i);

                if (buchNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element buchElement = (Element) buchNode;

                    int id = Integer.parseInt(buchElement.getAttribute("id"));
                    String title = buchElement.getElementsByTagName("title").item(0).getTextContent();
                    String description = buchElement.getElementsByTagName("description").item(0).getTextContent();
                    String date = buchElement.getElementsByTagName("date").item(0).getTextContent();

                    Element priceElement = (Element) buchElement.getElementsByTagName("price").item(0);
                    String currency = priceElement.getAttribute("currency");
                    int price = Integer.parseInt(priceElement.getTextContent());

                    buecherListe.add(new Buch(id, title, description, date, currency, price));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buecherListe;
    }
    //endregion

    //region Getters & Setters
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }
    //endregion
}
