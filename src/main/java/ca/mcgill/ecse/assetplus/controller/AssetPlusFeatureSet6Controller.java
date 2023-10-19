package ca.mcgill.ecse.assetplus.controller;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
import ca.mcgill.ecse.assetplus.model.*;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;

/**
 * @author Tessa Hason
 * @version 1.0
 * @since 2023-10-18
 */


public class AssetPlusFeatureSet6Controller {

  /**
   * @author Tessa Hason
   * This method is used to delete an Employee or a Guest within AssetPlus.
   * @param email The employee or guest with the given email must be deleted.
   */

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

  /**
   * @author Tessa Hason
   * This method is used to view the status of Maintenance Tickets for an employee/manager.
   * @return Returns a list of TOMaintenanceTicket objects representing all MaintenanceTickets
   * in the AssetPlus System.
   */

  public static List<TOMaintenanceTicket> getTickets() {
    AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

    if (assetPlus.hasMaintenanceTickets()){
      List<MaintenanceTicket> maintenanceTickets = assetPlus.getMaintenanceTickets();
      List<TOMaintenanceTicket> TOMaintenanceTickets = new ArrayList<TOMaintenanceTicket>();

      for (MaintenanceTicket ticket : maintenanceTickets) {
        int id = ticket.getId();
        Date date = ticket.getRaisedOnDate();
        String description = ticket.getDescription();
        String ticketRaiserEmail = ticket.getTicketRaiser().getEmail();
        String assetName = ticket.getAsset().getAssetType().getName();
        int lifespan = ticket.getAsset().getAssetType().getExpectedLifeSpan();
        Date purchaseDate = ticket.getAsset().getPurchaseDate();
        int floorNumber = ticket.getAsset().getFloorNumber();
        int roomNumber = ticket.getAsset().getRoomNumber();
        List<String> urls = null;
        List<TOMaintenanceNote> notes = null;


        if (ticket.hasTicketImages()){
          urls = new ArrayList<String>();
          for (int i=0; i<ticket.numberOfTicketImages(); i++){
            String currUrl = ticket.getTicketImage(i).getImageURL();
            urls.add(currUrl);
          }
        }

        if (ticket.hasTicketNotes()) {
          List<MaintenanceNote> maintenanceNotes = ticket.getTicketNotes();
          for (MaintenanceNote note : maintenanceNotes) {
            Date noteDate = note.getDate();
            String noteDescription = note.getDescription();
            String noteTakerEmail = note.getNoteTaker().getEmail();

            // Create a TOMaintenanceNote
            TOMaintenanceNote TOMaintenanceNote = new TOMaintenanceNote(noteDate, noteDescription, noteTakerEmail);
            notes.add(TOMaintenanceNote);
          }
        }


        TOMaintenanceTicket TOticket = new TOMaintenanceTicket(id, date, description, ticketRaiserEmail, assetName, lifespan, purchaseDate, floorNumber, roomNumber, urls, notes.toArray(new TOMaintenanceNote[0]));
        TOMaintenanceTickets.add(TOticket);

      }

      return TOMaintenanceTickets;

    }
    return null;
    
  }

}
