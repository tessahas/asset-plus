package ca.mcgill.ecse.assetplus.controller;
import ca.mcgill.ecse.assetplus.model.*;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import java.util.List;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket.PriorityLevel;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket.TimeEstimate;
import java.sql.Date;
import ca.mcgill.ecse.assetplus.persistence.AssetPlusPersistence;


public class AssetPlusAPI {

private static AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

  /**
   * <h2>getEstimateEnum<h2>
   * @param timeEstimate - Time estimate of the ticket as a string
   * @return TimeEstimate, Priority - Returns the input as their enum counterpart
   * 
   * @author Yuri Sorice
   */
  public static TimeEstimate getTimeEstimateEnum(String timeEstimateString) {
    TimeEstimate timeEstimate = TimeEstimate.valueOf(timeEstimateString);
    return timeEstimate;
  }

  /**
   * <h2>getPriorityEnum<h2>
   * @param priority - priority of the ticket as a string
   * @return Priority - Returns the input as their enum counterpart
   * 
   * @author Yuri Sorice
   */
  public static PriorityLevel getPriorityEnum(String priorityString) {
    PriorityLevel priority = PriorityLevel.valueOf(priorityString);
    return priority;
  }

  public static MaintenanceTicket getTicketwithId(int ticketId) {
    return MaintenanceTicket.getWithId(ticketId);
  }


  /**
  * <h1>assign</h1> 
  * This method is called when a Hotel Staff member is assigned to a ticket
  * @param ticketId - Id of the ticket that a Hotel Staff member is assigned to.
  * @param employeeEmail - Email of the Hotel Staff member assigned to the ticket. 
  * @param timeEstimate - Time Estimate of the ticket.
  * @param priority - Priority of the ticket.
  * @param requiresApproval - Whether the ticket requires approval from the manager to be closed.
  * @return String - This returns a string with an error message gathered during execution. If it is empty, the Employee or Guest's account registration was successful.
  *
  * @author Mathieu Allaire
  */
  
  public static String assign(int ticketId, String employeeEmail, TimeEstimate timeEstimate, PriorityLevel priority, Boolean requiresApproval){
  		String errorMessage = "";
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
  		if(right_ticket.getStatusFullName().equalsIgnoreCase("assigned")){
  			errorMessage += "The maintenance ticket is already assigned.";
  		}
  		else if(right_ticket.getStatusFullName().equalsIgnoreCase("resolved")){
  			errorMessage += "Cannot assign a maintenance ticket which is resolved.";
  		}
  		else if(right_ticket.getStatusFullName().equalsIgnoreCase("closed")){
  			errorMessage += "Cannot assign a maintenance ticket which is closed.";
  		}
  		else if(right_ticket.getStatusFullName().equalsIgnoreCase("inprogress")){
  			errorMessage += "Cannot assign a maintenance ticket which is in progress.";
  		}
  		if(!errorMessage.isEmpty()){
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
   * @param  toStart - This is the MaintenanceTicket object which's status will be changed to "InProgress" if the method is successful.
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
    }else if (toStart.getStatusFullName().equalsIgnoreCase("open")) {
      errorMessage += "Cannot start a maintenance ticket which is open.";
    }else if (toStart.getStatusFullName().equalsIgnoreCase("resolved")) {
      errorMessage += "Cannot start a maintenance ticket which is resolved.";
    }else if (toStart.getStatusFullName().equalsIgnoreCase("closed")) {
      errorMessage += "Cannot start a maintenance ticket which is closed.";
    }else if (toStart.getStatusFullName().equalsIgnoreCase("inprogress")) {
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
    }else if (toComplete.getStatusFullName().equalsIgnoreCase("open")) {
      errorMessage += "Cannot complete a maintenance ticket which is open.";
    }else if (toComplete.getStatusFullName().equalsIgnoreCase("assigned")) {
      errorMessage += "Cannot complete a maintenance ticket which is assigned.";
    }else if (toComplete.getStatusFullName().equalsIgnoreCase("closed")) {
      errorMessage += "The maintenance ticket is already closed.";
    }else if (toComplete.getStatusFullName().equalsIgnoreCase("resolved")) {
      errorMessage += "The maintenance ticket is already resolved.";
    }
    if(!errorMessage.isEmpty()){
      return errorMessage;
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
   * @return String - Returns a string with an error message gathered during execution, if this returned string is empty, the status of toApprove maintenance ticket was succesfully changed to "Closed".
   */
  public static String approveTicketWork(MaintenanceTicket toApprove){
    String errorMessage = "";
    if (toApprove == null){
          errorMessage += "Maintenance ticket does not exist.";
    }else if (!MaintenanceTicket.hasWithId(toApprove.getId())) {
      errorMessage += "Maintenance ticket does not exist.";
    }
    else if(toApprove.getStatusFullName().equalsIgnoreCase("open")) {
      errorMessage += "Cannot approve a maintenance ticket which is open.";
    }
    else if(toApprove.getStatusFullName().equalsIgnoreCase("Assigned")) {
      errorMessage += "Cannot approve a maintenance ticket which is assigned.";
    }
    else if(toApprove.getStatusFullName().equalsIgnoreCase("Closed")) {
      errorMessage += "The maintenance ticket is already closed.";
    }
    else if(toApprove.getStatusFullName().equalsIgnoreCase("InProgress")) {
      errorMessage += "Cannot approve a maintenance ticket which is is progress.";
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
  * @param  disapproveTicketWork - maintenance ticket to disapprove
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
    else if(toDisapprove.getStatusFullName().equalsIgnoreCase("open")) {
      errorMessage += "Cannot disapprove a maintenance ticket which is open.";
    }
    else if(toDisapprove.getStatusFullName().equalsIgnoreCase("Assigned")) {
      errorMessage += "Cannot disapprove a maintenance ticket which is assigned.";
    }
    else if(toDisapprove.getStatusFullName().equalsIgnoreCase("Closed")) {
      errorMessage += "Cannot disapprove a maintenance ticket which is closed.";
    }
    else if(toDisapprove.getStatusFullName().equalsIgnoreCase("InProgress")) {
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

  public static TimeEstimate getTimeEstimate(int ticketId) {
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
    TimeEstimate timeEstimate = ticket.getTimeToResolve();
    return timeEstimate;
  }

  public static PriorityLevel getPriorityLevel(int ticketId) {
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
    PriorityLevel priorityLevel = ticket.getPriority();
    return priorityLevel;
  }

  public static Boolean getRequiresApproval(int ticketId) {
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
    Boolean requiresApproval = ticket.hasFixApprover();
    return requiresApproval;
  }
}
