package ca.mcgill.ecse.assetplus.controller;
import ca.mcgill.ecse.assetplus.model.*;

public class AssetPlusAPI {
  public static String assignTicket(MaintenanceTicket toAssign){
  }

  /**
   * <h1>startTicketWork</h1>
   * This method is called whenever the hotel staff tries to start work on the maintenance ticket toStart.
   *
   * @param toStart This is the MaintenanceTicket object which's status will be changed to "InProgress" if the method is successful.
   * @return String - This method returns a string containing an error message gathered during execution, if this returned string is empty, the status of toStart maintenance ticket was successfully changed to "InProgress".
   * 
   * @author Jerome Desrosiers
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

  /**
   * <h1>completeTicketWork</h1>
   * This method is called whenever the hotel staff tries to complete work on the maintenance ticket toComplete.
   *
   * @param toComplete This is the MaintenanceTicket object which's status will be changed to "Resolved" if approval is required and "Closed" otherwise.
   * @return String - This method returns a string containing an error message gathered during execution, if this returned string is empty, the status of toStart maintenance ticket was successfully changed to "Resolved" or "Closed" depending on whether or not approval is required.
   * 
   * @author Luis Jarquin, Jerome Desrosiers
   */
  public static String completeTicketWork(MaintenanceTicket toComplete){
    String errorMessage = "";
    
    if (!MaintenanceTicket.hasWithId(toComplete.getId())) {
      errorMessage += "Maintenance ticket does not exist.";
    }
    if (toComplete.getTicketStatusFullName().equalsIgnoreCase("open")) {
      errorMessage += "Cannot complete a maintenance ticket which is open.";
    }
    if (toComplete.getTicketStatusFullName().equalsIgnoreCase("assigned")) {
      errorMessage += "Cannot complete a maintenance ticket which is assigned.";
    }
    if (toComplete.getTicketStatusFullName().equalsIgnoreCase("closed")) {
      errorMessage += "The maintenance ticket is already closed.";
    }
    if (toComplete.getTicketStatusFullName().equalsIgnoreCase("resolved")) {
      errorMessage += "The maintenance ticket is already resolved.";
    }

    try {
      toComplete.markAsResolved();
    }
    catch(Exception e) {
      errorMessage += "Unknown exception";
    }

    return errorMessage;
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
