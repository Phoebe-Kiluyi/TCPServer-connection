//server that echoes back client messages
//At the end of a dialogue, sends a message indicating
//the number of messages received. Uses TCP

import java.io.*;
import java.net.*;
import java.util.*;

public class TCPEchoServer{
	private static ServerSocket serverSocket;
	private static final int PORT = 1234;

	public static void main(String[]args){
		System.out.println("Opening port...\n");
		try{
			serverSocket = new ServerSocket(PORT);
		}
		catch(IOException ioEx){
			System.out.println("UNable to attach to port!");
			System.exit(1);
		}
		do{
			handleClient();
		}while(true);
	}
	private static void handleClient(){
		Socket link = null; //step 2
		try{
			link = serverSocket.accept(); //step2
			Scanner input = new
			     Scanner(link.getInputStream()); //step 3
			PrintWriter output = new
                  PrintWriter(link.getOutputStream(), true); //step 3

                  int numMessages = 0;
                  String message = input.nextLine(); //step 4

                   while(!message.equals("***CLOSE***")){
                   	System.out.println("Message Received.");
                   	numMessages++;
                   	output.println("Message" + numMessages + ":" + message);
                   	message = input.nextLine(); //step 4
                   }
                   output.println(numMessages + "Message received."); //step 4

		}
		catch(IOException ioEx){
			ioEx.printStackTrace();
		}
		finally{
			try{
				System.out.println("\n * Closing connection...*");
				link.close();
			}
			catch(IOException ioEx){
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}//end of handleClient
}