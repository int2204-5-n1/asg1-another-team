/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dict;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField search_text;
    @FXML
    private Button search;
    @FXML
    private Label target_word;
    @FXML
    private Button speech;
    @FXML
    private Label sound;
    @FXML
    private Label mean;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        speech.setVisible(false);
        target_word.setVisible(false);
    }

    @FXML
    private void showDictMangement(ActionEvent event) throws IOException {
        Parent dictm = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene dict_scene = new Scene(dictm);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(dict_scene);
        app_stage.show();
    }

    @FXML
    private void dictSearch(ActionEvent event) {
        String w = search_text.getText();
        if (!w.isEmpty()) {
            target_word.setVisible(true);
            for (Word list : Dictionary.word_list) {
                if (list.getWord_target().equals(w)) {
                    target_word.setText(list.getWord_target());
                    sound.setText(list.getSound());
                    mean.setText(list.getWord_explain());
                    speech.setVisible(true);
                    break;

                } else {
                    target_word.setText("Không tìm thấy!");
                    sound.setText("");
                    mean.setText("Chuyển sang My Dictionary Management để thêm từ mới");
                    speech.setDisable(true);
                }
            }
        }
    }

    @FXML
    private void speechIt(ActionEvent event) {

    }

}
