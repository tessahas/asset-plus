package ca.mcgill.ecse.assetplus.controller;
import ca.mcgill.ecse.assetplus.model.*;

public class AssetPlusAPI {
  public static String approveTicketWork(MaintenanceTicket toApprove){
    String errorMessage = "";
    if (!MaintenanceTicket.hasWithId(toApprove.getId())) {
      errorMessage += "Maintenance ticket does not exist.";
    }
    else if(toApprove.getTicketStatusFullName().equalsIgnoreCase("open")) {
      errorMessage += "Cannot approve a maintenance ticket which is open.";
    }
    else if(toApprove.getTicketStatusFullName().equalsIgnoreCase("Assigned")) {
      errorMessage += "Cannot approve a maintenance ticket which is open.";
    }
    else if(toApprove.getTicketStatusFullName().equalsIgnoreCase("Closed")) {
      errorMessage += "Cannot approve a maintenance ticket which is open.";
    }
    else if(toApprove.getTicketStatusFullName().equalsIgnoreCase("InProgress")) {
      errorMessage += "Cannot approve a maintenance ticket which is open.";
    }
    try {
      toApprove.approveWork();
    }
    catch(Exception e) {
      errorMessage += "Some unknown error.";
    }
    return errorMessage;
  }

  public static String disapproveTicketWork(MaintenanceTicket toDisapprove){
    String errorMessage = "";
    if (!MaintenanceTicket.hasWithId(toDisapprove.getId())) {
      errorMessage += "Maintenance ticket does not exist.";
    }
    else if(toDisapprove.getTicketStatusFullName().equalsIgnoreCase("open")) {
      errorMessage += "Cannot disapprove a maintenance ticket which is open.";
    }
    else if(toDisapprove.getTicketStatusFullName().equalsIgnoreCase("Assigned")) {
      errorMessage += "Cannot disapprove a maintenance ticket which is assigned.";
    }
    else if(toDisapprove.getTicketStatusFullName().equalsIgnoreCase("Closed")) {
      errorMessage += "Cannot disapprove a maintenance ticket which is closed.";
    }
    else if(toDisapprove.getTicketStatusFullName().equalsIgnoreCase("InProgress")) {
      errorMessage += "Cannot disapprove a maintenance ticket which is in progress.";
    }
    try {
      toDisapprove.disapproveWork();
    }
    catch(Exception e) {
      errorMessage += "Some unknown error.";
    }
    return errorMessage;
  }
}
