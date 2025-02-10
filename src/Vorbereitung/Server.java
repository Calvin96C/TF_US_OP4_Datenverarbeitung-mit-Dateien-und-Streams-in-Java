package Vorbereitung;

import Vorbereitung.Models.Buch;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Server
{
    public static void main(String[] args)
    {
        try(ServerSocket serverSocket = new ServerSocket(8080))
        {
            System.out.println("Warte auf Verbindung....");

            try(Socket clientSocket = serverSocket.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream()))
            {
                System.out.println("Verbindung mit dem Client wurde erfolgreich aufgebaut.");

//                @SuppressWarnings("unchecked")
//                List<Buch> buecher = (List<Buch>) objectInputStream.readObject(); // immutable

                // Fehler, da die Liste immutable ist
                //Collections.sort(buecher, Comparator.comparing(Buch::getId));
                //buecher.sort(Comparator.comparing(Buch::getId));

                // Lösung
                //List<Buch> sortierteBuecher = new ArrayList<>(buecher);

                // Alternative
                @SuppressWarnings("unchecked")
                List<Buch> sortierteBuecher = new ArrayList<>((List<Buch>) objectInputStream.readObject());

                if (!sortierteBuecher.isEmpty())
                {
                    sortierteBuecher.sort(Comparator.comparing(Buch::getId));

                    objectOutputStream.writeObject(sortierteBuecher);
                    objectOutputStream.flush();
                }
            }
            catch (ClassNotFoundException e)
            {
                throw new RuntimeException(e);
            }
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }
}
