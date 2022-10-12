import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RunApp extends Application  {

	public static void main(String[] args) {
		 
		launch(args); // star javafx app
	}

	@Override
	public void start(Stage arg0) throws Exception {
		 
		 ClientPane mp = new ClientPane (arg0); // instantiate gridpane
		 
		Scene mp2= new Scene(mp,1200,650); // set dimensions
		
     arg0.centerOnScreen();
    
		arg0.setScene(mp2);
		arg0.setTitle(" Facial Detection");
		arg0.show();
		arg0.setResizable(false);
		
	}

}
