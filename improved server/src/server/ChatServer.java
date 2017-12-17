package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer implements Runnable
{
	private ServerSocket server;
	private Socket temp_socket;
	
	public ChatServer(int port)
	{
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		//Thread t1 = new Thread(this);
		//t1.start();
	}

	@Override
	public void run() {
		while(true)
		{
			try {
				temp_socket = server.accept();
				new Client(temp_socket);
				System.out.println("Client added");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args)
	{
		ChatServer a = new ChatServer(9999);
		a.run();
	}
	

}
