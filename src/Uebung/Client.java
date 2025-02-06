package Uebung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        // Socket verbinden, IP Adresse & Port
        try(Socket socket = new Socket(InetAddress.getLocalHost(),8080);
            // Reader & Writer erzeugen
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer= new PrintWriter(socket.getOutputStream(),true))
        {
            System.out.println("Verbindung wurde erfolgreich aufgebaut.");

            Scanner userInput = new Scanner(System.in);
            String userInputString;

            // Endlos-Schleife erstellen, um mit dem Socket verbunden zu bleiben
            while(!socket.isClosed())
            {
                System.out.println();
                System.out.println("Gib einen Text ein:");
                System.out.print("> ");

                userInputString = userInput.nextLine();

                writer.println(userInputString);

                // Socket Verbindung trennen
                if (userInputString.equalsIgnoreCase("exit"))
                {
                    socket.close();
                }
            }

            System.out.println("Verbindung wurde getrennt.");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
