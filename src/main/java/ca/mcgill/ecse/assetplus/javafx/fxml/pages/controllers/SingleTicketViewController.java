/**
 * Sample Skeleton for 'SingleTicketView.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class PleaseProvideControllerClassName {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="overviewTable"
    private ListView<?> overviewTable; // Value injected by FXMLLoader

    @FXML
    void onBackButtonClicked(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'SingleTicketView.fxml'.";
        assert overviewTable != null : "fx:id=\"overviewTable\" was not injected: check your FXML file 'SingleTicketView.fxml'.";

    }

}
