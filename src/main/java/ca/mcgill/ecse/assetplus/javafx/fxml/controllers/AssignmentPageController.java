package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import static ca.mcgill.ecse.assetplus.javafx.fxml.controllers.ViewUtils.callController;
import java.sql.Date;
import ca.mcgill.ecse.assetplus.controller.AssetPlusAPI;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet6Controller;
import ca.mcgill.ecse.assetplus.controller.TOMaintenanceTicket;
import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class AssignmentPageController {

  @FXML private ChoiceBox<TOMaintenanceTicket> ticketChoiceBox;
  @FXML private ChoiceBox<String> staffChoiceBox;
  @FXML private ChoiceBox<String> timeEstimateChoiceBox;
  @FXML private ChoiceBox<String> priorityLevelChoiceBox;
  @FXML private ChoiceBox<String> requiresApprovalChoiceBox;
  
  @FXML
  public void initialize() {
    // the combo boxes are refreshable
    ticketChoiceBox.addEventHandler(AssetPlusFxmlView.REFRESH_EVENT, e -> {
      ticketChoiceBox.setItems(ViewUtils.getTickets());
      ticketChoiceBox.setValue(null);
    });

    staffChoiceBox.addEventHandler(AssetPlusFxmlView.REFRESH_EVENT, e -> {
      staffChoiceBox.setItems(ViewUtils.getEmployees());
      staffChoiceBox.setValue(null);
    });

    // register the refreshable nodes
    AssetPlusFxmlView.getInstance().registerRefreshEvent(ticketChoiceBox, staffChoiceBox);
  }

  // Event Listener on Button[#assignButton].onAction
  @FXML
  public void assignClicked(ActionEvent event) {
    var bus = ticketChoiceBox.getValue();
    var route = staffChoiceBox.getValue();
    var selectedDate = datePicker.getValue();

    var error = "";
    if (bus == null) {
      error += "Invalid bus vehicle! ";
    }
    if (route == null) {
      error += "Invalid route! ";
    }
    if (selectedDate == null) {
      error += "Invalid date! ";
    }

    if (!error.isEmpty()) {
      ViewUtils.showError(error);
    } else {
      var date = Date.valueOf(selectedDate);
      callController(assetplusController.assign(bus, route, date));
    }
  }
}
