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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController implements Initializable {

    @FXML
    private TextField search_text;
    @FXML
    private Label target_word;
    @FXML
    private Button speech;
    @FXML
    private Label sound;
    @FXML
    private TextArea mean;
    private boolean result;
    @FXML
    private Button getVoice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mean.setEditable(false);
        speech.setVisible(false);
        getVoice.setVisible(false);

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
    private void dictSearch(ActionEvent event) throws Exception {

        String w = search_text.getText();
        if (!w.isEmpty()) {
            Word list = search(w);
            sound.setVisible(result);
            mean.setVisible(result);/*
            getVoice.setVisible(true);
            speech.setVisible(false);*/
            getVoice.setVisible(result);
            speech.setVisible(false);
            if (result) {
                target_word.setText(list.getWord_target());
                sound.setText(list.getSound());
                mean.setText(DictionaryManagement.renderExplain(list.getWord_explain()));
            } else {
                target_word.setText("Không tìm thấy!");

            }
        }
    }

    @FXML
    private void speechIt(ActionEvent event) throws Exception {
        textToSpeech.play();
    }

    /**
     * ham tim kiem tu
     * @param w tu can tra
     * @return doi tuong word da tim thay
     * @throws Exception
     */
    private Word search(String w) throws Exception {
        Word tmp = null;
        for (Word list : Dictionary.word_list) {
            if (list.getWord_target().equals(w)) {
                result = true;
                tmp = list;
                break;
            } else {
                result = false;
            }
        }
        return tmp;
    }

    @FXML
    private void getVoice(ActionEvent event) throws Exception {
        textToSpeech.speechIt(target_word.getText());
        getVoice.setVisible(false);
        speech.setVisible(true);
    }
}
