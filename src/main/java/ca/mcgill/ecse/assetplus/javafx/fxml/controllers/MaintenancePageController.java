package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet5Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class MaintenancePageController {
  @FXML private TextField createTicketDateTextField;
  @FXML private TextField createTicketDescriptionTextField;
  @FXML private TextField updateTicketIdTextField;
  @FXML private TextField updateTicketDateTextField;
  @FXML private TextField updateTicketDescriptionTextField;
  @FXML private TextField imageTicketIdTextField;
  @FXML private TextField addImageURLTextField;
  @FXML private TextField deleteImageURLTextField;
  @FXML private Button createButton;
  @FXML private Button updateButton;
  @FXML private Button deleteButton;
  @FXML private Button addImageButton;
  @FXML private Button deleteImageButton;
  @FXML private ChoiceBox<String> createTicketPriorityChoiceBox;
  @FXML private ChoiceBox<String> createTicketTimeEstimateChoiceBox;
  @FXML private ChoiceBox<String> createTicketApprovalChoiceBox;
  @FXML private ChoiceBox<String> updateTicketPriorityChoiceBox;
  @FXML private ChoiceBox<String> updateTicketTimeEstimateChoiceBox;
  @FXML private ChoiceBox<String> updateTicketApprovalChoiceBox;


  @FXML void initialize() {
    ObservableList<String> priorities =
      FXCollections.observableArrayList("Low", "Normal", "Urgent");
    
    ObservableList<String> timeEstimate =
      FXCollections.observableArrayList("LessThanADay", "OneToThreeDays", "ThreeToSevenDays", "OneToThreeWeeks", "ThreeOrMoreWeeks");
    
    ObservableList<String> approvalRequired =
      FXCollections.observableArrayList("Yes", "No");

    createTicketPriorityChoiceBox.setItems(priorities);
    createTicketPriorityChoiceBox.setValue(null);
    updateTicketPriorityChoiceBox.setItems(priorities);
    updateTicketApprovalChoiceBox.setValue(null);

    createTicketTimeEstimateChoiceBox.setItems(timeEstimate);
    createTicketTimeEstimateChoiceBox.setValue(null);
    updateTicketTimeEstimateChoiceBox.setItems(timeEstimate);
    updateTicketTimeEstimateChoiceBox.setValue(null);

    createTicketApprovalChoiceBox.setItems(approvalRequired);
    createTicketApprovalChoiceBox.setValue("No");
    updateTicketApprovalChoiceBox.setItems(approvalRequired);
    updateTicketApprovalChoiceBox.setValue("No");
  }
  // Event Listener on Button[#addImageButton].onAction
  @FXML
  public void addImageClicked(ActionEvent event) {
    String url = addImageURLTextField.getText();
    String idString = imageTicketIdTextField.getText();
    if (url == null || url.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid image URL");
    } else if (idString == null || idString.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid ticket ID");
    } else {
      // reset the text field if success
      int id = Integer.parseInt(idString);
      if (ViewUtils.successful(AssetPlusFeatureSet5Controller.addImageToMaintenanceTicket(url,id))) {
        addImageURLTextField.setText("");
      }
    }
  }

  // Event Listener on Button[#deleteImageButton].onAction
  @FXML
  public void deleteImageClicked(ActionEvent event) {
    String name = deleteImageURLTextField.getText();
    String idString = imageTicketIdTextField.getText();
    if (name == null || name.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid image URL");
    } else if (idString == null || idString.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid ticket ID");
    } else {
      // reset the text field if success
      int id = Integer.parseInt(idString);
      if (ViewUtils.successful(AssetPlusFeatureSet5Controller.deleteImageFromMaintenanceTicket(name,id))) {
        deleteImageURLTextField.setText("");
      }
    }
  }

  // Event Listener on Button[#createButton].onAction
  @FXML
  public void createTicket(ActionEvent event) {
    String date = createTicketDateTextField.getText();
    String description = createTicketDescriptionTextField.getText();
    
  } 
}

