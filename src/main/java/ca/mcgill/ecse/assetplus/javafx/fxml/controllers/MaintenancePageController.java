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
  @FXML private TextField createTicketIdTextField, createTicketRaiserEmailTextField, createTicketDateTextField, createTicketDescriptionTextField, createTicketAssetNumberTextField,
        updateTicketIdTextField, updateTicketRaiserEmailTextField, updateTicketDateTextField, updateTicketDescriptionTextField, updateTicketAssetNumberTextField,
        imageTicketIdTextField, addImageURLTextField, deleteImageURLTextField;

  @FXML private Button createButton, updateButton, deleteButton, addImageButton, deleteImageButton;


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
    } 
    else if (idString == null || idString.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid ticket ID");
    } 
    else {
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
    String emailString = createTicketRaiserEmailTextField.getText();

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
    else if (emailString == null || emailString.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid user email");
    }
    else {
      int id = Integer.parseInt(idString);
      Date date = Date.valueOf(dateString);
      int assetNumber = Integer.parseInt(assetString);
      // TODO user email may not always be the manager
      String errorMessage = AssetPlusFeatureSet4Controller.addMaintenanceTicket(id, date, description, emailString, assetNumber);
        // clear textfields
      if (errorMessage.isEmpty()) {
        createTicketIdTextField.setText(null);
        createTicketDateTextField.setText(null);
        createTicketDescriptionTextField.setText(null);
        createTicketRaiserEmailTextField.setText(null);
        createTicketAssetNumberTextField.setText(null);

      } else {
        ViewUtils.showError(errorMessage);
      }


    }
  }
  // Event Listener on Button[#updateButton].onAction
  @FXML
  public void updateTicket(ActionEvent event) {
    String idString = updateTicketIdTextField.getText();
    String dateString = updateTicketDateTextField.getText();
    String description = updateTicketDescriptionTextField.getText();
    String email = updateTicketRaiserEmailTextField.getText();
    String assetString = updateTicketAssetNumberTextField.getText();

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
    else if (email == null || email.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid user email");
    }
    else {
      int id = Integer.parseInt(idString);
      Date date = Date.valueOf(dateString);
      int assetNumber = Integer.parseInt(assetString);
      String errorMessage = AssetPlusFeatureSet4Controller.updateMaintenanceTicket(id, date, description, email, assetNumber);
        // clear textfields
      if (errorMessage.isEmpty()) {
        updateTicketIdTextField.setText(null);
        updateTicketDateTextField.setText(null);
        updateTicketDescriptionTextField.setText(null);
        updateTicketRaiserEmailTextField.setText(null);
        updateTicketAssetNumberTextField.setText(null);

      } else {
        ViewUtils.showError(errorMessage);
      }


    }
   }
  // Event Listener on Button[#deleteButton].onAction
  @FXML
  public void deleteTicket(ActionEvent event) {
    String idString = updateTicketIdTextField.getText();
    if (idString == null || idString.trim().isEmpty()) {
      ViewUtils.showError("Please input a ticket id");
    }
    else {
      int id = Integer.parseInt(idString);
      String errorMessage = AssetPlusFeatureSet4Controller.deleteMaintenanceTicket(id);
      
      if (errorMessage.isEmpty()) {
        updateTicketIdTextField.setText(null);
      }
      else {
        ViewUtils.showError(errorMessage);
      }
    }

  }
}

