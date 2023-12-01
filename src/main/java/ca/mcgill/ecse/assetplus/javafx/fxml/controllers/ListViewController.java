package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;
/**
 * Sample Skeleton for 'ListView.fxml' Controller Class
 */

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import ca.mcgill.ecse.assetplus.controller.*;
import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import java.time.LocalDate;

public class ListViewController {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="searchByDate"
    private DatePicker searchByDate; // Value injected by FXMLLoader

    @FXML private Button searchByDateButton;

    @FXML private Button seeAllTicketsButton;

    @FXML private TableView<TOMaintenanceTicket> overviewTable;

    @FXML private Button searchButton;

    @FXML private Button searchByRaiserButton;
    
    @FXML private TextField searchRaiserTextField;

    @FXML private TextField searchTicketTextField;

    public void initialize() {
        // initialize the overview table by adding new columns

        /*  id = aId;
     private int id; GOOD
  private Date raisedOnDate; GOOD
  private String description; GOOD
  private String raisedByEmail;
  private String status;
  private String fixedByEmail;
  private String timeToResolve;
  private String priority; GOOD
  private boolean approvalRequired;
  private String assetName;
  private int expectLifeSpanInDays;
  private Date purchaseDate;
  private int floorNumber;
  private int roomNumber;
  private List<String> imageURLs;*/
        
        //TICKET ID COLUMN (int)
        var idcolumn = createTableColumn("Ticket ID", "id");
        overviewTable.getColumns().add(idcolumn);


        //STATUS COLUMN (string)
        var statusColumn = createTableColumn("Status", "status");
        overviewTable.getColumns().add(statusColumn);
    
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

        var timeResColumn = new TableColumn<TOMaintenanceTicket, String>("Time To Resolve");
        timeResColumn.setCellValueFactory(data -> Bindings.createStringBinding(
            () -> {
                TOMaintenanceTicket ticket = data.getValue();
                if (ticket != null && ticket.getTimeToResolve() != null) {
                    return ticket.getTimeToResolve();
                } else {
                    return ""; // or any default value or appropriate handling
                }
            }
            ));
        overviewTable.getColumns().add(timeResColumn);

        //PRIORITY COLUMN (string)
        overviewTable.getColumns().add(createTableColumn("Priority", "priority"));

        //APPROVAL REQUIRED (Boolean)
        overviewTable.getColumns().add(createTableColumn("Approval Required", "approvalRequired"));

        //ASSET NAME (customizable string)
        var assetNameColumn = new TableColumn<TOMaintenanceTicket, String>("Asset Name");
        assetNameColumn.setCellValueFactory(data -> Bindings.createStringBinding(
            () -> data.getValue().getAssetName()));
        overviewTable.getColumns().add(assetNameColumn);

        //LIFESPAN COLUMN (string)
        overviewTable.getColumns().add(createTableColumn("Lifespan", "expectLifeSpanInDays"));

        //raised on date (Date)
        var raisedOnDateColumn = new TableColumn<TOMaintenanceTicket, String>("Raised On");
        raisedOnDateColumn.setCellValueFactory(data -> Bindings.createStringBinding(
            () -> data.getValue().getRaisedOnDate().toString()));
        overviewTable.getColumns().add(raisedOnDateColumn);

        //Purchase date (Date)
        var purchaseDateColumn = new TableColumn<TOMaintenanceTicket, String>("Purchase Date");
        purchaseDateColumn.setCellValueFactory(data -> Bindings.createStringBinding(
            () -> data.getValue().getPurchaseDate().toString()));
        overviewTable.getColumns().add(purchaseDateColumn);

        //FloorNumber int
        overviewTable.getColumns().add(createTableColumn("Floor Number", "floorNumber"));

        //RoomNumber int
        overviewTable.getColumns().add(createTableColumn("Room Number", "roomNumber"));

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


        //Set colors 
        overviewTable.setRowFactory(tv -> new TableRow<TOMaintenanceTicket>() {
            @Override
            protected void updateItem(TOMaintenanceTicket item, boolean empty) {
                super.updateItem(item, empty);
    
                if (item == null || item.getStatus() == null) {
                    // If the status is null, set the default row color
                    setStyle("");
                } else {
                    // Set row color based on the value of the "Status" column
                    switch (item.getStatus()) {
                        case "Open":
                            setStyle("-fx-background-color: lightgreen;");
                            break;
                        case "InProgress":
                            setStyle("-fx-background-color: #FFFFE0;"); //Light yellow
                            break;
                        case "Assigned":
                            setStyle("-fx-background-color: #FFDAB9;");  //light orange
                            break;
                        case "Closed":
                            setStyle("-fx-background-color: lightred"); //light red 
                            break;
                        case "Resolved":
                            setStyle("-fx-background-color: #E6E6FA"); //light purple
                            break;
                        default:
                            // Set the default row color for other statuses
                            setStyle("");
                            break;
                    }
                }
            }
        });


        // configure data picker
        // set editable to false so that the user cannot choose from the calendar
        //searchByDate.setEditable(false);
        // set default value to today
        searchByDate.setValue(LocalDate.now());
    
        overviewTable.addEventHandler(AssetPlusFxmlView.REFRESH_EVENT, e -> overviewTable.setItems(getAllTickets()));
    
        // register refreshable nodes
        AssetPlusFxmlView.getInstance().registerRefreshEvent(overviewTable);

        assert overviewTable != null : "fx:id=\"overviewTable\" was not injected: check your FXML file 'ListView.fxml'.";
        assert searchByDate != null : "fx:id=\"searchByDate\" was not injected: check your FXML file 'ListView.fxml'.";
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



    @FXML
    void seeAllTicketsButtonClicked(ActionEvent event) {
        overviewTable.setItems(FXCollections.observableList(getAllTickets()));
        AssetPlusFxmlView.getInstance().registerRefreshEvent(overviewTable);
        AssetPlusFxmlView.getInstance().refresh();
    }

    @FXML
    void searchRaiserButtonClicked(ActionEvent event) {
        String selectedRaiser = searchRaiserTextField.getText().trim();
        if (selectedRaiser == null) {
            ViewUtils.showError("Please input a valid user email");
        }else{
            ObservableList<TOMaintenanceTicket> filteredTickets =  getAllTickets().filtered(ticket -> ticket.getRaisedByEmail().equalsIgnoreCase(selectedRaiser));

            if (filteredTickets.isEmpty()) {
                ViewUtils.showError("No tickets found with the specified Raiser");
            } else {
                overviewTable.setItems(FXCollections.observableList(filteredTickets));
                AssetPlusFxmlView.getInstance().registerRefreshEvent(overviewTable);
            }
        }

    }
  
    @FXML 
    void searchButtonClicked(ActionEvent event) { //FOR TICKET ID
        String stringid = searchTicketTextField.getText().trim();
        if (stringid.isEmpty()) {
            ViewUtils.showError("Please input a valid ticket ID");
        } else {
            int id = Integer.parseInt(stringid);
            // Search tickets by ID and update the table
            ObservableList<TOMaintenanceTicket> filteredTickets = getAllTickets().filtered(ticket -> ticket.getId() ==id);

            if (filteredTickets.isEmpty()) {
                ViewUtils.showError("No tickets found with the specified ID");
            } else {
                overviewTable.setItems(filteredTickets);
                AssetPlusFxmlView.getInstance().registerRefreshEvent(overviewTable);
            }
        }
    }

    public ObservableList<TOMaintenanceTicket> getAllTickets() {
        if (AssetPlusFeatureSet6Controller.getTickets() == null) {
            return FXCollections.emptyObservableList();
        }
        return FXCollections.observableList(AssetPlusFeatureSet6Controller.getTickets());
    }

    @FXML
    void searchByDateButtonClicked(ActionEvent event) { //FOR SEARCHING BY A SPECIFIC DAY 
        LocalDate selectedDate = searchByDate.getValue();
        if (selectedDate == null) {
            ViewUtils.showError("Please select a valid date");
        }else{
            var date = Date.valueOf(selectedDate);
            ObservableList<TOMaintenanceTicket> filteredTickets = getAllTickets().filtered(ticket -> 0==ticket.getRaisedOnDate().compareTo(date));

            if (filteredTickets.isEmpty()) {
                ViewUtils.showError("No tickets found with the specified date");
            } else {
                overviewTable.setItems(filteredTickets);
                AssetPlusFxmlView.getInstance().registerRefreshEvent(overviewTable);
            }
        }

    }


}
