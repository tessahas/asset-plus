package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import static ca.mcgill.ecse.assetplus.javafx.fxml.controllers.ViewUtils.callController;
import java.sql.Date;
import ca.mcgill.ecse.assetplus.controller.AssetPlusAPI;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet1Controller;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet6Controller;
import ca.mcgill.ecse.assetplus.controller.TOMaintenanceTicket;
import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AssignmentPageController {

  @FXML private TextField ticketIdTextField, noteTextField, dateTextField;
  @FXML private ChoiceBox<String> employeeEmailChoiceBox, priorityChoiceBox, timeEstimateChoiceBox, approvalChoiceBox;
  @FXML private Button assignButton, startWorkButton, completeWorkButton, approveButton, disapproveButton;
  
  @FXML
  public void initialize() {
    // non refrechable choice boxese (always the same list to choose from)
    ObservableList<String> priorities =
      FXCollections.observableArrayList("Low", "Normal", "Urgent");
    
    ObservableList<String> timeEstimate =
      FXCollections.observableArrayList("LessThanADay", "OneToThreeDays", "ThreeToSevenDays", "OneToThreeWeeks", "ThreeOrMoreWeeks");
    
    ObservableList<String> approvalRequired =
      FXCollections.observableArrayList("Yes", "No");

      priorityChoiceBox.setItems(priorities);
      priorityChoiceBox.setValue(null);

      timeEstimateChoiceBox.setItems(timeEstimate);
      timeEstimateChoiceBox.setValue(null);

      approvalChoiceBox.setItems(approvalRequired);
      approvalChoiceBox.setValue("No");

    // the combo boxes are refreshable
    employeeEmailChoiceBox.addEventHandler(AssetPlusFxmlView.REFRESH_EVENT, e -> {
      employeeEmailChoiceBox.setItems(AssetPlusFeatureSet1Controller.getEmployees());
      employeeEmailChoiceBox.setValue(null);
    });

    // register the refreshable nodes
    AssetPlusFxmlView.getInstance().registerRefreshEvent(employeeEmailChoiceBox);
  }

  // Event Listener on Button[#assignButton].onAction
  @FXML
  public void assignClicked(ActionEvent event) {
    String ticketIdString = ticketIdTextField.getText();
    String employeeEmail = employeeEmailChoiceBox.getValue();
    String priorityString = priorityChoiceBox.getValue();
    String timeEstimateString = timeEstimateChoiceBox.getValue();
    String approvalString = approvalChoiceBox.getValue();

    if (ticketIdString == null || ticketIdString.trim().isEmpty()) {
      ViewUtils.showError("The ticket number cannot be empty");
    }
    else if (employeeEmail == null) {
      ViewUtils.showError("An employee must be specified");
    }
    else if (priorityString == null || priorityString.trim().isEmpty()) {
      ViewUtils.showError("Please select a priority");
    }
    else if (timeEstimateString == null || timeEstimateString.trim().isEmpty()) {
      ViewUtils.showError("Please select a time estimate");
    }
    else {
      boolean approval = ("Yes".compareToIgnoreCase(approvalString)==0);
      int ticketId = Integer.parseInt(ticketIdString);
      String errorMessage = AssetPlusAPI.assign(ticketId, employeeEmail, AssetPlusAPI.getTimeEstimateEnum(timeEstimateString), 
      AssetPlusAPI.getPriorityEnum(priorityString), approval);
      if (errorMessage.isEmpty()) {
        ticketIdTextField.setText("");
      } else {
        ViewUtils.showError(errorMessage);
      }
    }
  }

  @FXML
  public void startWorkClicked(ActionEvent event) {
    String ticketIdString = ticketIdTextField.getText();
    if (ticketIdString == null || ticketIdString.trim().isEmpty()) {
      ViewUtils.showError("The ticket number cannot be empty");
    }
    else {
      int ticketId = Integer.parseInt(ticketIdString);
      String errorMessage = AssetPlusAPI.startTicketWork(AssetPlusAPI.getTicketwithId(ticketId));
      if (errorMessage.isEmpty()) {
        ticketIdTextField.setText("");
      } else {
        ViewUtils.showError(errorMessage);
      }
    }
  }

  @FXML
  public void completeWorkClicked(ActionEvent event) {
    String ticketIdString = ticketIdTextField.getText();
    if (ticketIdString == null || ticketIdString.trim().isEmpty()) {
      ViewUtils.showError("The ticket number cannot be empty");
    }
    else {
      int ticketId = Integer.parseInt(ticketIdString);
      String errorMessage = AssetPlusAPI.completeTicketWork(AssetPlusAPI.getTicketwithId(ticketId));
      if (errorMessage.isEmpty()) {
        ticketIdTextField.setText("");
      } else {
        ViewUtils.showError(errorMessage);
      }
    }
  }

  @FXML
  public void approveClicked(ActionEvent event) {
    String ticketIdString = ticketIdTextField.getText();
    if (ticketIdString == null || ticketIdString.trim().isEmpty()) {
      ViewUtils.showError("The ticket number cannot be empty");
    }
    else {
      int ticketId = Integer.parseInt(ticketIdString);
      String errorMessage = AssetPlusAPI.approveTicketWork(AssetPlusAPI.getTicketwithId(ticketId));
      if (errorMessage.isEmpty()) {
        ticketIdTextField.setText("");
      } else {
        ViewUtils.showError(errorMessage);
      }
    }
  }

  @FXML
  public void disapproveClicked(ActionEvent event) {
    String ticketIdString = ticketIdTextField.getText();
    String note = noteTextField.getText();
    String dateString = dateTextField.getText();

    if (ticketIdString == null || ticketIdString.trim().isEmpty()) {
      ViewUtils.showError("The ticket number cannot be empty");
    }
    else {
      int ticketId = Integer.parseInt(ticketIdString);
      Date date = Date.valueOf(dateString);
      String errorMessage = AssetPlusAPI.disapproveTicketWork(date, note, AssetPlusAPI.getTicketwithId(ticketId));
      if (errorMessage.isEmpty()) {
        ticketIdTextField.setText("");
        noteTextField.setText("");
        dateTextField.setText("");
      } else {
        ViewUtils.showError(errorMessage);
      }
    }
  }
}
