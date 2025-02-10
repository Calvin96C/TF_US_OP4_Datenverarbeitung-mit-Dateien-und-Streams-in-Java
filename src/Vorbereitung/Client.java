package Vorbereitung;

import Vorbereitung.Models.Buch;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import Vorbereitung.Utils.ObjectToJsonUtil;

public class Client
{
    public static void main(String[] args)
    {
        try(
                Socket clientSocket = new Socket(InetAddress.getLocalHost(),8080);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream()))
        {
            System.out.println("Verbindung mit dem Server wurde hergestellt.");

            Path regalXML = Paths.get("src","Vorbereitung", "Resources","Regal.xml");
            Path regalJSON = Paths.get(regalXML.getParent().toString(),"Regal.json");

            if (Files.exists(regalXML))
            {
                List<Buch> buecher = Buch.parseXML(regalXML.toString());

                objectOutputStream.writeObject(buecher);
                objectOutputStream.flush();

                @SuppressWarnings("unchecked")
                List<Buch> sortierteBuecher = new ArrayList<>((List<Buch>) objectInputStream.readObject());

                ObjectToJsonUtil.writeObjectListToJson(sortierteBuecher,regalJSON);

            }

            System.out.println("Verbindung wurde getrennt.");
        }
        catch (IOException | ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

}
