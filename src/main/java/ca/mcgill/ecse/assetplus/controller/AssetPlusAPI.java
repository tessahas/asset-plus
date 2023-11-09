package ca.mcgill.ecse.assetplus.controller;
import ca.mcgill.ecse.assetplus.model.*;

public class AssetPlusAPI {
  public static String assignTicket(MaintenanceTicket toAssign){
  }

  /**
   * METHOD DEF
   * @author Jerome Desrosiers
   * @param toStart
   * @return
   */
  public static String startTicketWork(MaintenanceTicket toStart){
    String errorMessage = "";
    
    if (!MaintenanceTicket.hasWithId(toStart.getId())) {
      errorMessage += "Maintenance ticket does not exist.";
    }
    if (toStart.getTicketStatusFullName().equalsIgnoreCase("open")) {
      errorMessage += "Cannot start a maintenance ticket which is open.";
    }
    if (toStart.getTicketStatusFullName().equalsIgnoreCase("resolved")) {
      errorMessage += "Cannot start a maintenance ticket which is resolved.";
    }
    if (toStart.getTicketStatusFullName().equalsIgnoreCase("closed")) {
      errorMessage += "Cannot start a maintenance ticket which is closed.";
    }
    if (toStart.getTicketStatusFullName().equalsIgnoreCase("inprogress")) {
      errorMessage += "The maintenance ticket is already in progress.";
    }

    try {
      toStart.startWork();
    }
    catch(Exception e) {
      errorMessage += "Unknown exception";
    }

    return errorMessage;
  }

  public static String completeTicketWork(MaintenanceTicket toComplete){
  }
  
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