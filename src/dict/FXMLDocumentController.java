package dict;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author 16021228
 */
public class FXMLDocumentController implements Initializable {

    final ObservableList<String> listItems = FXCollections.observableArrayList("");
    @FXML
    private ListView<String> listBoxMain;
    @FXML
    private Button BtnAdd;
    @FXML
    private Button BtnDelete;
    @FXML
    private TextField wTarget;
    @FXML
    private TextField wSound;
    @FXML
    private TextArea wExplain;
    @FXML
    private TextField eng;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        for (Word tmp : Dictionary.word_list) {
            listItems.add(tmp.getWord_target());
        }

        listBoxMain.setItems(listItems);
        listBoxMain.setEditable(true);

        BtnAdd.setDisable(true);
        BtnDelete.setDisable(true);

        wTarget.focusedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (wTarget.isFocused()) {
                    BtnAdd.setDisable(false);
                }
            }
        });

        listBoxMain.focusedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (listBoxMain.isFocused()) {
                    BtnDelete.setDisable(false);
                }
            }
        });
    }

    @FXML
    private void show(MouseEvent event) {
        Word tmp;
        if (listBoxMain.getSelectionModel().getSelectedIndex() >= 1) {
            int index = listBoxMain.getSelectionModel().getSelectedIndex() - 1;
            tmp = Dictionary.word_list.get(index);
            wTarget.setText(tmp.getWord_target());
            wSound.setText(tmp.getSound());
            wExplain.setText(DictionaryManagement.renderExplain(tmp.getWord_explain()));
            eng.setText(tmp.getWord_target());
        }
    }

    @FXML
    private void addAction(ActionEvent event) {
        String text = wTarget.getText();
        listItems.add(text);
        Word tmp = new Word();
        tmp.setWord_target(wTarget.getText());
        tmp.setWord_explain(wExplain.getText());
        tmp.setSound(wSound.getText());
        Dictionary.word_list.add(tmp);
        showAlert("Đã thêm từ: " + text);

    }

    @FXML
    private void deleteAction(ActionEvent event
    ) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Word");
        alert.setHeaderText("Bạn chắc chắn muốn xóa?");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            int selectedItem = listBoxMain.getSelectionModel().getSelectedIndex();
            if (selectedItem >= 0) {
                listItems.remove(selectedItem);
            }
        }
    }

    @FXML
    private void updateAction(ActionEvent event
    ) {
        Word tmp;
        int index = listBoxMain.getSelectionModel().getSelectedIndex() - 1;
        tmp = Dictionary.word_list.get(index);
        tmp.setWord_target(wTarget.getText());
        tmp.setWord_explain(wExplain.getText());
        tmp.setSound(wSound.getText());
        showAlert("Đã sửa từ: " + wTarget.getText());
    }

    @FXML
    private void updateToFile(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("update words list");
        alert.setHeaderText("Mọi thay đổi sẽ được ghi vào file!");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            DictionaryManagement.deleteFile();
            DictionaryManagement.createFile();
            showAlert("Đã ghi vào file");
            System.out.println("File is done!");
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene main_scene = new Scene(main);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(main_scene);
        app_stage.show();
    }

    /**
     * Hien thi hop thoai thong bao
     * @param text noi dung thong bao
     */
    private void showAlert(String text) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    @FXML
    private void focusOn(ActionEvent event) {
        int index = 0;
        for (Word list : Dictionary.word_list) {
            if (list.getWord_target().equals(eng.getText())) {
                index = Dictionary.word_list.indexOf(list) + 1;
                listBoxMain.scrollTo(index);
                listBoxMain.getSelectionModel().select(index);
            }
        }
    }

}
