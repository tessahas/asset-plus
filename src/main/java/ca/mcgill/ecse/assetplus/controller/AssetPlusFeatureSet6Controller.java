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
   * This method is used to delete an Employee or a Guest within the AssetPlus application.
   * @param email The employee or guest with the given email must be deleted.
   */

  public static void deleteEmployeeOrGuest(String email) {
    User userToDelete = User.getWithEmail(email);
    if(userToDelete instanceof Guest || userToDelete instanceof Employee){
      userToDelete.delete();
    }

  }

  /**
   * @author Tessa Hason
   * The method is used to view the status of Maintenance Tickets for an employee/manager.
   * @return Returns a list of TOMaintenanceTicket objects representing all MaintenanceTickets
   * in the AssetPlus System.
   */

  public static List<TOMaintenanceTicket> getTickets() {
    AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

    if (assetPlus.hasMaintenanceTickets()){
      List<MaintenanceTicket> maintenanceTickets = assetPlus.getMaintenanceTickets();
      List<TOMaintenanceTicket> TOMaintenanceTickets = new ArrayList<TOMaintenanceTicket>();

      for (MaintenanceTicket ticket : maintenanceTickets) {
        List<String> urls = new ArrayList<String>();
        List<TicketImage> images = ticket.getTicketImages();
          for (TicketImage TicketImage : images) {
            urls.add(TicketImage.getImageURL());
          }

        TOMaintenanceNote[] notes = new TOMaintenanceNote[ticket.numberOfTicketNotes()];
        int i = 0;
        for (MaintenanceNote note : ticket.getTicketNotes()) {
          Date noteDate = note.getDate();
          String noteDescription = note.getDescription();
          String noteTakerEmail = note.getNoteTaker().getEmail();
          TOMaintenanceNote TOMaintenanceNote = new TOMaintenanceNote(noteDate, noteDescription, noteTakerEmail);
          notes[i] = TOMaintenanceNote;
          i++;
        }

        TOMaintenanceTicket TOticket = new TOMaintenanceTicket(ticket.getId(), ticket.getRaisedOnDate(), ticket.getDescription(), ticket.getTicketRaiser().getEmail(), ticket.getAsset().getAssetType().getName(), ticket.getAsset().getAssetType().getExpectedLifeSpan(), ticket.getAsset().getPurchaseDate(), ticket.getAsset().getFloorNumber(), ticket.getAsset().getRoomNumber(), urls, notes);
        TOMaintenanceTickets.add(TOticket);

      }

      return TOMaintenanceTickets;

    }
    return null;
    
  }

}
