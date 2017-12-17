package application;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ChatClientController  implements Runnable
{

	@FXML
	private TextArea chat_box;
	@FXML
	private TextField user_messages;
	private boolean connected;
	private Socket socket;
	private Scanner read;
	private PrintWriter writer;
	private String username;

	@FXML
	public void initialize()
	{
		
	}
	public void initializeChatClient(String IP_address , int port, String username)
	{
		this.username = username;
		try
		{
			socket = new Socket( IP_address , port);
			read = new Scanner((socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream());
			connected = true;		
			Thread t1 = new Thread(this);
			t1.start();
			chat_box.textProperty().addListener(new ChangeListener<Object>() {
			    @Override
			    public void changed(ObservableValue<?> observable, Object oldValue,
			            Object newValue) {
			    	chat_box.setScrollTop(Double.MAX_VALUE); //this will scroll to the bottom
			        //use Double.MIN_VALUE to scroll to the top
			    }
			});
			chat_box.setEditable(false);
		}
		catch(Exception e)
		{
			connected = false;
		}
	}
	
	public boolean isConnected()
	{
		return connected;
	}
	public void run()
	{
		readMessages();
		System.out.println("readMessages done");
	}
	public void sendMessage(ActionEvent event)
	{
		String m = user_messages.getText();
		if(!m.isEmpty())
		{
			writer.println(username + ": " + m);
			writer.flush();
			System.out.println(m);
			user_messages.setText("");
		}
		
	}
	
	public void readMessages()
	
	{
		
		try
		{
			while(true)
			{
				String message;
				if((read.hasNextLine()))
				{
					message = read.nextLine();
					chat_box.appendText(message + "\n");
				}
				else
					System.out.println("no messages");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
}
