package app;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class App extends Application{
	
	@FXML
	TextField input = new TextField();

	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("Calculator");
		primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("App.fxml"))));
		primaryStage.show();
	}

	public static void main(final String[] args) {
		App.launch(args);
	}
}