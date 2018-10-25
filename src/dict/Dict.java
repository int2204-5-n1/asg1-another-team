package dict;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author 16021228
 */
public class Dict extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Dictionary by another team");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) throws IOException {
        DictionaryManagement.insertFromFile2();
        launch(args);
    }
    
}
