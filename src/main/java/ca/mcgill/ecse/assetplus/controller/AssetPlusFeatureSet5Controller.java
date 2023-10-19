package ca.mcgill.ecse.assetplus.controller;

import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.TicketImage;

public class AssetPlusFeatureSet5Controller {

  /**
   * @author Kevin Li
   * This method adds an image to a maintenance ticket.
   * @param imageURL This corresponds to the URL of the image to be added.
   * @param ticketID This corresponds to the ticket ID of the ticket in which the image is to be added.
   * @return This method returns an empty string if the image was added successfully. If the image was not
   * added successfully, then a string containing the corresponding error messages is returned.
   */
  public static String addImageToMaintenanceTicket(String imageURL, int ticketID) {
    String errorMessage = "";

    if (!imageURL.startsWith("http://") || !imageURL.startsWith("https://")) {
      errorMessage+="Image URL must start with http:// or https://. ";
    }

    if (imageURL.isEmpty()) {
      errorMessage+="Image URL cannot be empty. ";
    }

    if (!MaintenanceTicket.hasWithId(ticketID)) {
      errorMessage+="Ticket does not exist. ";
    }

    MaintenanceTicket maintenanceTicket = MaintenanceTicket.getWithId(ticketID);

    for (TicketImage ticketImage: maintenanceTicket.getTicketImages()) {
      if (ticketImage.getImageURL().equals(imageURL)) {
        errorMessage+="Image already exists for the ticket. ";
      }
    }

    if (!errorMessage.isEmpty()) {
      return errorMessage;
    }

    maintenanceTicket.addTicketImage(imageURL);
    return errorMessage;
  }

  /**
   * @author Kevin Li
   * This method deletes an image from a maintenance ticket.
   * @param imageURL This corresponds to the URL of the image to be deleted.
   * @param ticketID This corresponds to the ticket ID of the ticket from which the image is to be deleted.
   * @return This method does not return anything.
   */
  public static void deleteImageFromMaintenanceTicket(String imageURL, int ticketID) {
    MaintenanceTicket maintenanceTicket = MaintenanceTicket.getWithId(ticketID);
    
    for (TicketImage ticketImage: maintenanceTicket.getTicketImages()) {
      if (ticketImage.getImageURL().equals(imageURL)) {
        maintenanceTicket.removeTicketImage(ticketImage);
      }
    }
  }

}
