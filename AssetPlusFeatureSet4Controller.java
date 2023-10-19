package ca.mcgill.ecse.assetplus.controller;
import ca.mcgill.ecse.assetplus.model.*;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;

import java.sql.Date;


public class AssetPlusFeatureSet4Controller {
  // assetNumber -1 means that no asset is specified
  public static String addMaintenanceTicket(int id, Date raisedOnDate, String description,
      String email, int assetNumber) {
        String errorMessage = "";
        if (!MaintenanceTicket.hasWithId(id)) {
          errorMessage += "Ticket id already exists. ";
        }
        if (!SpecificAsset.hasWithAssetNumber(assetNumber)) {
          errorMessage += "The asset does not exist. ";
        }
        if (!HotelStaff.hasWithEmail(email)) {
          errorMessage += "The ticket raiser does not exist. ";
        }
        if (description == null) {
          errorMessage += "The description cannot be empty. ";
        }
        if (!errorMessage.isEmpty()) return errorMessage;
      try {
          MaintenanceTicket newTicket = new MaintenanceTicket(id, raisedOnDate, description, AssetPlusApplication.getAssetPlus(), User.getWithEmail(email));
          if (assetNumber != -1 && assetNumber > 0){
            newTicket.setAsset(SpecificAsset.getWithAssetNumber(assetNumber));
        }
      
      }
      catch (Exception e){
        if (e.getMessage().contains("duplicate")){
          errorMessage += "Cannot create due to duplicate id.";
        }
        if (e.getMessage().contains("assetPlus")){
          errorMessage += "Unable to create maintenanceTicket due to assetPlus.";
        }
        if (e.getMessage().contains("raisedTicket")){
          errorMessage += "Unable to create raisedTicket due to ticketRaiser.";
        }
      }

      return (errorMessage);
  }

  // newAssetNumber -1 means that no asset is specified
  public static String updateMaintenanceTicket(int id, Date newRaisedOnDate, String newDescription,
      String newEmail, int newAssetNumber) {
        String errorMessage = "";
        if (!HotelStaff.hasWithEmail(newEmail)) {
          errorMessage += "The ticket raiser does not exist.";
        }
        if (newDescription == null) {
          errorMessage += "The Description cannot be empty.";
        }
        if (!SpecificAsset.hasWithAssetNumber(newAssetNumber)) {
          errorMessage += "The asset does not exist.";
        }
        MaintenanceTicket ticketToEdit = MaintenanceTicket.getWithId(id);
        ticketToEdit.setRaisedOnDate(newRaisedOnDate);
        ticketToEdit.setDescription(newDescription);
        ticketToEdit.setTicketRaiser(User.getWithEmail(newEmail));
        if (newAssetNumber != -1 && newAssetNumber > 0){
        ticketToEdit.setAsset(SpecificAsset.getWithAssetNumber(newAssetNumber));
        }


        return errorMessage;
    }
        

  public static void deleteMaintenanceTicket(int id) {

    if (MaintenanceTicket.hasWithId(id)) {
        MaintenanceTicket.getWithId(id).delete();
    }
  }
}
