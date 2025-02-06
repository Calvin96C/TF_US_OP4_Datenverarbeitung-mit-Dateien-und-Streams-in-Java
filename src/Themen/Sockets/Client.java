package Themen.Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        try
        {

            //connect(Inet4Address.getByName(null).getHostAddress(), 8080);
            connect("127.0.0.1", 8080);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void connect(String ip, int port) throws IOException
    {
        // Wichtig! AutoFlush nicht vergessen!
        try (Socket socket = new Socket(ip, port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())))
        {
            System.out.println("Verbunden...");

            Scanner scanner = new Scanner(System.in);
            String command;
            while(!socket.isClosed())
            {
                System.out.print("> ");
                command = scanner.nextLine(); // hier wird pausiert & auf die Eingabe des Nutzers gewartet.
                writer.println(command);
                do
                {
                    String answer = reader.readLine();
                    System.out.println(answer);
                } while (reader.ready()); // Wenn eine Nachricht vom Server mehrere Lines beinhaltet (z.B. bei der Auflistung der Dateien und Ordner), müssen alle Lines auch gelesen werden. Solange Zeilen vorhanden sind, gibt ready() true zurück.

                if (command.equalsIgnoreCase("exit"))
                    socket.close();
            }
        }
    }

}
