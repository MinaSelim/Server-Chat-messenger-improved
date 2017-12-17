package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utility.Document;

public class ConnectController implements Initializable
{
	private ArrayList<String> data = new ArrayList<String>();
	@FXML
	private TextField username;
	@FXML
	private TextField ip_address;
	@FXML
	private TextField port;
	@FXML
	private Label warning;
	@FXML
	private Button connect;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		try 
		{
				System.out.println("initialized");
				data = Document.Read("Config.txt");
				username.setText(Document.getValue(data.get(0)));
				System.out.println("initialized");
				ip_address.setText(Document.getValue(data.get(1)));
				port.setText(Document.getValue(data.get(2)));
				
				
		}
		catch(IOException e)
		{
			System.out.println("hello");
			e.printStackTrace();
		}
	
			
	
	}
	public void connectToServer(ActionEvent event)
	{
		
		if(!(username.getText().equals("") || ip_address.getText().equals("") || port.getText().equals("")))
		{
			if(isInteger(port.getText()))
			{
				if(startClientGUI())
				{
					Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
					st.close();
				}
		
			}
			else
				warning.setText("Port must be an integer");
		}
		else
			warning.setText("All the three fields must be full");
	}
	
	private void createConfigFile()
	{
		Document.CreateFile("config.txt");
		Document.writeToFile("Username: " + username.getText());
		Document.writeToFile("Ip_Address: " + ip_address.getText());
		Document.writeToFile("Port: " + port.getText());
		Document.closeFile();
	}
	
	private boolean isInteger(String a)
	{
		try {
			Integer.parseInt(a);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}
	
	private boolean startClientGUI()
	{
		try {
			Stage chat_stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ChatBox.fxml"));
			Parent root = loader.load();
			ChatClientController controller = loader.<ChatClientController>getController();
			controller.initializeChatClient(ip_address.getText(), Integer.parseInt(port.getText()), username.getText());
			if(controller.isConnected())
			{
				createConfigFile();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				chat_stage.setScene(scene);
				chat_stage.show();
				chat_stage.setOnCloseRequest(Event -> System.exit(1));
				return true;
			}
			else
			{
				warning.setText("Error, Can't connect to server");
				return false;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
