package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet5Controller;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet4Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.sql.Date;
// making/updating maintenance tickets do not require any of the choiceboxes, need to remove them from scenebuilder and here
// send the choiceboxes to assignment page and its controller
public class MaintenancePageController {
  @FXML private TextField createTicketIdTextField, createTicketDateTextField, createTicketDescriptionTextField, createTicketAssetNumberTextField,
        updateTicketIdTextField, updateTicketDateTextField, updateTicketDescriptionTextField,
        imageTicketIdTextField, addImageURLTextField, deleteImageURLTextField;
@FXML private Button createButton, updateButton, deleteButton, addImageButton, deleteImageButton;
@FXML private ChoiceBox<String> createTicketPriorityChoiceBox, createTicketTimeEstimateChoiceBox, createTicketApprovalChoiceBox,
        updateTicketPriorityChoiceBox, updateTicketTimeEstimateChoiceBox, updateTicketApprovalChoiceBox;



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
    String idString = createTicketIdTextField.getText();
    String dateString = createTicketDateTextField.getText();
    String description = createTicketDescriptionTextField.getText();
    String assetString = createTicketAssetNumberTextField.getText();

    // Checking for null or empty strings
    if (idString == null || idString.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid ticket id");
    }
    else if (dateString == null || dateString.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid date");
    }
    else if (description == null || description.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid description");
    }
    else if (assetString == null || assetString.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid asset number");
    }
    else {
      int id = Integer.parseInt(idString);
      Date date = Date.valueOf(dateString);
      int assetNumber = Integer.parseInt(assetString);
      // TODO user email may not always be the manager
      if(ViewUtils.successful(AssetPlusFeatureSet4Controller.addMaintenanceTicket(id, date, description,"manager@ap.com", assetNumber))) {
        // clear textfields
        createTicketIdTextField.setText(null);
        createTicketDateTextField.setText(null);
        createTicketDescriptionTextField.setText(null);
      }
    }


  } 
}

