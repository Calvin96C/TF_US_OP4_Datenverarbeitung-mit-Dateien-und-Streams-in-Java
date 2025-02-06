package Uebung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Server
{
    public static void main(String[] args)
    {

        try(ServerSocket serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true)
            )
        {
            System.out.println("Verbindung wurde erfolgreich erstellt.");

            String readerResult;
            Path uebungPath = Paths.get("","src","Uebung");
            Path uebungFilePath = Paths.get(uebungPath.toString(),"log.txt");

            if (Files.notExists(uebungPath))
            {
                Files.createDirectory(uebungPath);
            }

            if (Files.notExists(uebungFilePath))
            {
                Files.createFile(uebungFilePath);
            }

            while(!socket.isClosed())
            {
                readerResult = reader.readLine();

                if (readerResult.equalsIgnoreCase("exit"))
                {
                    System.out.println("Es wurde ''Exit'' geschrieben.");
                    socket.close();
                    break;
                }

                //TODO Muss irgendwie append nutzen & Rückmeldung an den Client wäre auch noch cool
                String fileResult = Files.readString(uebungFilePath);
                fileResult += readerResult;
                Files.writeString(uebungFilePath, fileResult);

            }
            System.out.println("Ich habe den Socket geschlossen.");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }
}
