package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet5Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;

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

}