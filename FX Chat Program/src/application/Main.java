package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Connect.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(Event -> System.exit(0));
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
