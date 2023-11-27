package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;
/**
 * Sample Skeleton for 'ListView.fxml' Controller Class
 */

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import ca.mcgill.ecse.assetplus.controller.*;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.beans.binding.Bindings;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import java.time.LocalDate;
import ca.mcgill.ecse.assetplus.javafx.fxml.pages.AssetPlusFxmlView;

public class ListViewController {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="SearchById"
    private TextField SearchById; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="searchByDate"
    private DatePicker searchByDate; // Value injected by FXMLLoader

    @FXML // fx:id="viewTicketDetailsButton"
    private Button viewTicketDetailsButton; // Value injected by FXMLLoader

    @FXML private TableView<TOMaintenanceTicket> overviewTable;

    @FXML private DatePicker datePicker;

    public void initialize() {
        // initialize the overview table by adding new columns
        
        //TICKET ID COLUMN (int)
        overviewTable.getColumns().add(createTableColumn("Ticket ID", "number"));

        //STATUS COLUMN (string)
        overviewTable.getColumns().add(createTableColumn("Status", "status"));
    
        //RAISED BY EMAIL COLUMN (customizable string)
        var raisedColumn = new TableColumn<TOMaintenanceTicket, String>("Ticket Raiser");
        raisedColumn.setCellValueFactory(data -> Bindings.createStringBinding(
            () -> data.getValue().getRaisedByEmail()));
        overviewTable.getColumns().add(raisedColumn);

        //DESCRIPTION COLUMN (customizable string)
        var descriptionColumn = new TableColumn<TOMaintenanceTicket, String>("Description");
        descriptionColumn.setCellValueFactory(data -> Bindings.createStringBinding(
            () -> data.getValue().getDescription()));
        overviewTable.getColumns().add(descriptionColumn);

        //FIXED BY EMAIL COLUMN (customizable string)
        var fixedempColumn = new TableColumn<TOMaintenanceTicket, String>("Assigned Employee");
        fixedempColumn.setCellValueFactory(data -> Bindings.createStringBinding(
            () -> data.getValue().getFixedByEmail()));
        overviewTable.getColumns().add(fixedempColumn);

        //TIME TO RESOLVE COLUMN (customizable string)
        var timeResColumn = new TableColumn<TOMaintenanceTicket, String>("Time To Resolve");
        timeResColumn.setCellValueFactory(data -> Bindings.createStringBinding(
            () -> data.getValue().getTimeToResolve()));
        overviewTable.getColumns().add(timeResColumn);

        //PRIORITY COLUMN (string)
        overviewTable.getColumns().add(createTableColumn("Priority", "prioritylevel"));

        //APPROVAL REQUIRED (Boolean)
        overviewTable.getColumns().add(createTableColumn("Approval Required", "approvalRequired"));

        //ASSET NAME (customizable string)
        var assetNameColumn = new TableColumn<TOMaintenanceTicket, String>("Asset Name");
        assetNameColumn.setCellValueFactory(data -> Bindings.createStringBinding(
            () -> data.getValue().getAssetName()));
        overviewTable.getColumns().add(assetNameColumn);

        //LIFESPAN COLUMN (string)
        overviewTable.getColumns().add(createTableColumn("Lifespan", "lifespan"));

        //Purchase date (Date)
        var purchaseDateColumn = new TableColumn<TOMaintenanceTicket, String>("Purchase Date");
        purchaseDateColumn.setCellValueFactory(data -> Bindings.createStringBinding(
            () -> data.getValue().getPurchaseDate().toString()));
        overviewTable.getColumns().add(purchaseDateColumn);

        //FloorNumber int
        overviewTable.getColumns().add(createTableColumn("Floor Number", "floornumber"));

        //RoomNumber int
        overviewTable.getColumns().add(createTableColumn("Room Number", "roomnumber"));

        //Image URLS 
        var imageColumn = new TableColumn<TOMaintenanceTicket, String>("Images");
        imageColumn.setCellValueFactory(data -> Bindings.createStringBinding(
            () -> data.getValue().getImageURLs().toString()));
        overviewTable.getColumns().add(imageColumn);

        //Maintenance Notes 
        var notesColumn = new TableColumn<TOMaintenanceTicket, String>("Notes");
        notesColumn.setCellValueFactory(data -> Bindings.createStringBinding(
            () -> data.getValue().getNotes().toString()));
        overviewTable.getColumns().add(notesColumn);
    
        // configure data picker
        // set editable to false so that the user cannot choose from the calendar
        datePicker.setEditable(false);
        // set default value to today
        datePicker.setValue(LocalDate.now());
    
        // overview table if a refreshable element
        overviewTable.addEventHandler(AssetPlusFxmlView.REFRESH_EVENT, e -> overviewTable.setItems(getOverviewItems()));
    
        // register refreshable nodes
        AssetPlusFxmlView.getInstance().registerRefreshEvent(overviewTable);
      }

    @FXML
    void selectedDateChanged(ActionEvent event) {
        AssetPlusFxmlView.getInstance().refresh();
    }

    // the table column will automatically display the string value of the property for each instance in the table
    public static TableColumn<TOMaintenanceTicket, String> createTableColumn(String header, String propertyName) {
        TableColumn<TOMaintenanceTicket, String> column = new TableColumn<>(header);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return column;
    }

    public ObservableList<TOMaintenanceTicket> getOverviewItems() {
        LocalDate selectedDate = datePicker.getValue();
        if (selectedDate == null) {
          ViewUtils.showError("Please select a valid date");
          return FXCollections.emptyObservableList();
        }
        var date = Date.valueOf(selectedDate);
        return FXCollections.observableList(AssetPlusFeatureSet6Controller.getTicketForSpecificDay(date));
    }

    
    /*@FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert SearchById != null : "fx:id=\"SearchById\" was not injected: check your FXML file 'ListView.fxml'.";
        assert overviewTable != null : "fx:id=\"overviewTable\" was not injected: check your FXML file 'ListView.fxml'.";
        assert searchByDate != null : "fx:id=\"searchByDate\" was not injected: check your FXML file 'ListView.fxml'.";

    }*/

}
