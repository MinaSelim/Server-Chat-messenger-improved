package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client implements Runnable
{
	private static ArrayList<Client> clients = new ArrayList<Client>();
	private Socket client_socket;
	private Scanner scan;
	private PrintWriter write;
	
	public Client(Socket s)
	{
		this.client_socket = s;
		clients.add(this);
		try {
			scan = new Scanner(client_socket.getInputStream());
			write = new PrintWriter(client_socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while(true)
		{
			if(scan.hasNext())
			{
				writeToAll(scan.nextLine());
				
			}
		}
		
	}
	
	public void writeToAll(String x)
	{
		for(Client a : clients)
		{
			a.write.println(x);
			a.write.flush();
		}
	}
	
	
	

}
