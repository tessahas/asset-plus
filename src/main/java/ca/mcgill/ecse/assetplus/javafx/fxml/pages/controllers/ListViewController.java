/**
 * Sample Skeleton for 'ListView.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PleaseProvideControllerClassName {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="SearchById"
    private TextField SearchById; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="overviewTable"
    private TableView<?> overviewTable; // Value injected by FXMLLoader

    @FXML // fx:id="searchByDate"
    private DatePicker searchByDate; // Value injected by FXMLLoader

    @FXML // fx:id="viewTicketDetailsButton"
    private Button viewTicketDetailsButton; // Value injected by FXMLLoader

    @FXML
    void OnViewTicketDetailsButtonClicked(ActionEvent event) {

    }

    @FXML
    void onBackButtonClicked(ActionEvent event) {

    }

    @FXML
    void onTicketIDEntered(ActionEvent event) {

    }

    @FXML
    void selectedDateChanged(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert SearchById != null : "fx:id=\"SearchById\" was not injected: check your FXML file 'ListView.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'ListView.fxml'.";
        assert overviewTable != null : "fx:id=\"overviewTable\" was not injected: check your FXML file 'ListView.fxml'.";
        assert searchByDate != null : "fx:id=\"searchByDate\" was not injected: check your FXML file 'ListView.fxml'.";
        assert viewTicketDetailsButton != null : "fx:id=\"viewTicketDetailsButton\" was not injected: check your FXML file 'ListView.fxml'.";

    }

}
