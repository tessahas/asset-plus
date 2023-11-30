package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import java.sql.Date;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet2Controller;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet3Controller;
import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;

public class AssetPageController {
  @FXML private TextField addAssetNumberTextField;
  @FXML private TextField addPurchaseDateTextField;
  @FXML private TextField addFloorNumberTextField;
  @FXML private TextField addRoomNumberTextField;
  @FXML private TextField updateAssetNumberTextField;
  @FXML private TextField updatePurchaseDateTextField;
  @FXML private TextField updateFloorNumberTextField;
  @FXML private TextField updateRoomNumberTextField;
  @FXML private Button addButton;
  @FXML private Button updateButton;
  @FXML private Button deleteButton;
  @FXML private ChoiceBox<String> addAssetTypeChoiceBox;
  @FXML private ChoiceBox<String> updateAssetTypeChoiceBox;

   @FXML
  public void initialize() {
    // the driver and assignment choice boxes are refreshable
    addAssetTypeChoiceBox.addEventHandler(AssetPlusFxmlView.REFRESH_EVENT, e -> {
      addAssetTypeChoiceBox.setItems(AssetPlusFeatureSet2Controller.getAssetTypes());
      addAssetTypeChoiceBox.setValue(null);
    });

    updateAssetTypeChoiceBox.addEventHandler(AssetPlusFxmlView.REFRESH_EVENT, e -> {
      updateAssetTypeChoiceBox.setItems(AssetPlusFeatureSet2Controller.getAssetTypes());
      updateAssetTypeChoiceBox.setValue(null);
    });

    // register the refreshable nodes
    AssetPlusFxmlView.getInstance().registerRefreshEvent(addAssetTypeChoiceBox);
    AssetPlusFxmlView.getInstance().registerRefreshEvent(updateAssetTypeChoiceBox);
  }

  @FXML
  public void addClicked(ActionEvent event) {
    String assetNumberString = addAssetNumberTextField.getText();
    String purchaseDateString = addPurchaseDateTextField.getText();
    String floorNumberString = addFloorNumberTextField.getText();
    String roomNumberString = addRoomNumberTextField.getText();
    String dateString = addPurchaseDateTextField.getText();

    if (assetNumberString == null || assetNumberString.trim().isEmpty()) {
      ViewUtils.showError("The asset number cannot be empty");
    }
    else if (purchaseDateString == null || purchaseDateString.trim().isEmpty()) {
      ViewUtils.showError("The purchase date cannot be empty");
    }
    else if (floorNumberString == null || floorNumberString.trim().isEmpty()) {
      ViewUtils.showError("The floor number cannot be empty");
    }
    else if (dateString == null || dateString.trim().isEmpty()) {
      ViewUtils.showError("The date cannot be empty");
    }
    else {
      String assetType = addAssetTypeChoiceBox.getValue();
      int assetNumber = Integer.parseInt(assetNumberString);
      int floorNumber = Integer.parseInt(floorNumberString);
      int roomNumber = Integer.parseInt(roomNumberString);
      Date purchaseDate = Date.valueOf(dateString);
      if (ViewUtils.successful(AssetPlusFeatureSet3Controller.addSpecificAsset(assetNumber, floorNumber, roomNumber, purchaseDate, assetType))) {
        addAssetNumberTextField.setText("");
        addPurchaseDateTextField.setText("");
        addFloorNumberTextField.setText("");
        addRoomNumberTextField.setText("");
      }
    }
  }
}
