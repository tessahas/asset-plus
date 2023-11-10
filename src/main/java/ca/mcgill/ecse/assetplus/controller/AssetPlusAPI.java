package ca.mcgill.ecse.assetplus.controller;
import ca.mcgill.ecse.assetplus.model.*;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import java.util.List;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket.PriorityLevel;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket.TimeEstimate;
import java.sql.Date;
import ca.mcgill.ecse.assetplus.persistence.AssetPlusPersistence;


public class AssetPlusAPI {

  /**
  * <h1>assign</h1> 
  * This method is called when a Hotel Staff member is assigned to a ticket
  * @param int ticketId - Id of the ticket that a Hotel Staff member is assigned to.
  * @param String employeeEmail - Email of the Hotel Staff member assigned to the ticket. 
  * @param TimeEstimate timeEstimate - Time Estimate of the ticket.
  * @param PriorityLevel priority - Priority of the ticket.
  * @param Boolean requiresApproval - Whether the ticket requires approval from the manager to be closed.
  * @return String - This returns a string with an error message gathered during execution. If it is empty, the Employee or Guest's account registration was successful.
  *
  * @author Mathieu Allaire
  */
  
  public static String assign(int ticketId, String employeeEmail, TimeEstimate timeEstimate, PriorityLevel priority, Boolean requiresApproval){
  		String errorMessage = "";
  		AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
  		if(!(assetPlus.hasMaintenanceTickets())){
  			errorMessage+="There are no maintenance tickets in the system.";
  			return errorMessage;
  		}
  		if(!assetPlus.hasEmployees()){
  			errorMessage += "There are no employees in the system";
  			return errorMessage;
  		}
  		if(!assetPlus.hasManager()){
  			errorMessage += "There is no manager in the system";
  			return errorMessage;
  		}
  		List<Employee> employees = assetPlus.getEmployees();
  		List<MaintenanceTicket> tickets = assetPlus.getMaintenanceTickets();
  		MaintenanceTicket right_ticket = null;
  		Boolean foundTicket = false;
  		for (MaintenanceTicket ticket: tickets){
  		if(ticket.getId()==ticketId){
  			right_ticket = ticket;
  			foundTicket = true;
  			break;
  		}
  	}
  		if(!foundTicket || right_ticket==null){
  			errorMessage += "Maintenance ticket does not exist.";
  			return errorMessage;
  		}
  		if(right_ticket.getTicketStatusFullName().equals("Assigned")){
  			errorMessage += "The maintenance ticket is already assigned.";
  		}
  		else if(right_ticket.getTicketStatusFullName().equals("Resolved")){
  			errorMessage += "Cannot assign a maintenance ticket which is resolved.";
  		}
  		else if(right_ticket.getTicketStatusFullName().equals("Closed")){
  			errorMessage += "Cannot assign a maintenance ticket which is closed.";
  		}
  		else if(right_ticket.getTicketStatusFullName().equals("InProgress")){
  			errorMessage += "Cannot assign a maintenance ticket which is in progress.";
  		}
  		if(!errorMessage.equals("")){
  			return errorMessage;
  		}
  		if(assetPlus.getManager().getEmail().equals(employeeEmail)){
  			HotelStaff manager = assetPlus.getManager();
  			
        try {
          right_ticket.setFixApprover(assetPlus.getManager());
    right_ticket.assign(manager, priority, timeEstimate, requiresApproval);
  		
      AssetPlusPersistence.save();
    }
    catch(Exception e) {
      errorMessage += "Unknown exception";
    }
  			
  			return errorMessage;
  		}
  		Boolean foundEmployee = false;
  		HotelStaff right_employee = null;
  		for (Employee employee: employees){
  		if(employee.getEmail().equals(employeeEmail)){
  			foundEmployee = true;
  			right_employee = employee;
  			break;
  			}
  		}
  		if(!foundEmployee){
  		errorMessage += "Staff to assign does not exist.";
  		return errorMessage;
  		}
  		try {
    right_ticket.setFixApprover(assetPlus.getManager());
    right_ticket.assign(right_employee, priority, timeEstimate, requiresApproval);
      AssetPlusPersistence.save();
    }
    catch(Exception e) {
      errorMessage += "Unknown exception";
    }
    
  		
  		return errorMessage;
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
    if(toStart == null){
        errorMessage += "Maintenance ticket does not exist.";
    }else if (!MaintenanceTicket.hasWithId(toStart.getId())) {
      errorMessage += "Maintenance ticket does not exist.";
    }else if (toStart.getTicketStatusFullName().equalsIgnoreCase("open")) {
      errorMessage += "Cannot start a maintenance ticket which is open.";
    }else if (toStart.getTicketStatusFullName().equalsIgnoreCase("resolved")) {
      errorMessage += "Cannot start a maintenance ticket which is resolved.";
    }else if (toStart.getTicketStatusFullName().equalsIgnoreCase("closed")) {
      errorMessage += "Cannot start a maintenance ticket which is closed.";
    }else if (toStart.getTicketStatusFullName().equalsIgnoreCase("inprogress")) {
      errorMessage += "The maintenance ticket is already in progress.";
    }

    try {
      toStart.startWork();
      AssetPlusPersistence.save();
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
    if (toComplete == null){
        errorMessage += "Maintenance ticket does not exist.";
    }else if (!MaintenanceTicket.hasWithId(toComplete.getId())) {
      errorMessage += "Maintenance ticket does not exist.";
    }else if (toComplete.getTicketStatusFullName().equalsIgnoreCase("open")) {
      errorMessage += "Cannot complete a maintenance ticket which is open.";
    }else if (toComplete.getTicketStatusFullName().equalsIgnoreCase("assigned")) {
      errorMessage += "Cannot complete a maintenance ticket which is assigned.";
    }else if (toComplete.getTicketStatusFullName().equalsIgnoreCase("closed")) {
      errorMessage += "The maintenance ticket is already closed.";
    }else if (toComplete.getTicketStatusFullName().equalsIgnoreCase("resolved")) {
      errorMessage += "The maintenance ticket is already resolved.";
    }

    try {
      toComplete.markAsResolved();
      AssetPlusPersistence.save();
    }
    catch(Exception e) {
      errorMessage += "Unknown exception";
    }

    return errorMessage;
  }
  /**
   * <1>approveTicketWork</1>
   * This method is called when the manager attempts to approve work on a ticket, checking if the given ticket has the right status.
   * @author Yuri Sorice
   * @param toApprove The ticket that the manager wants to approve.
   */
  public static String approveTicketWork(MaintenanceTicket toApprove){
    String errorMessage = "";
    if (toApprove == null){
          errorMessage += "Maintenance ticket does not exist.";
    }else if (!MaintenanceTicket.hasWithId(toApprove.getId())) {
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
      AssetPlusPersistence.save();
    }
    catch(Exception e) {
      errorMessage += "Some unknown error.";
    }
    return errorMessage;
  }

   /**
  * <h1>disapproveTicketWork</h1> 
  * This method is called when a manager wants to disapprove a ticket that requires approval,
  * 
  * @param MaintenanceTicket disapproveTicketWork - maintenance ticket to disapprove
  * @return String - error message
  *
  * @author Tessa Hason
  */

   public static String disapproveTicketWork(Date date, String reason, MaintenanceTicket toDisapprove){
    String errorMessage = "";
    if (toDisapprove == null){
        errorMessage += "Maintenance ticket does not exist.";
    } else if (!MaintenanceTicket.hasWithId(toDisapprove.getId())) {
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
      toDisapprove.disapproveWork(date, reason);
      MaintenanceNote note = new MaintenanceNote(date, reason, toDisapprove, toDisapprove.getAssetPlus().getManager());
      AssetPlusPersistence.save();
    }
    catch(Exception e) {
      errorMessage += "Some unknown error.";
    }
    return errorMessage;
  }
}
