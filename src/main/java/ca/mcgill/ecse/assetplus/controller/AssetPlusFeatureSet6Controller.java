package ca.mcgill.ecse.assetplus.controller;
import java.util.List;
import ca.mcgill.ecse.assetplus.model.*;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import java.util.Map;


public class AssetPlusFeatureSet6Controller {

  public static void deleteEmployeeOrGuest(String email) {

    AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

    int index = email.length() - 7;

    if (email.substring(index).equals("@ap.com")){
      if (User.hasWithEmail(email)) {
        Employee employeeToDelete = (Employee) User.getWithEmail(email);
        assetPlus.removeEmployee(employeeToDelete);
      }
    }
    else {
      if (User.hasWithEmail(email)){
        Guest guestToDelete = (Guest) User.getWithEmail(email);
        assetPlus.removeGuest(guestToDelete);
      }
    }
  }

  // returns all tickets
  public static List<TOMaintenanceTicket> getTickets() {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }

}
