package ca.mcgill.ecse.assetplus.features;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet6Controller;
import ca.mcgill.ecse.assetplus.controller.TOMaintenanceTicket;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import java.util.Map;
import java.util.PrimitiveIterator;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet7Controller;
import ca.mcgill.ecse.assetplus.model.*;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket.PriorityLevel;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket.TimeEstimate;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket.TicketStatus;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MaintenanceTicketsStepDefinitions {
    private AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
    private List<TOMaintenanceTicket> tickets;

    /**
     * This step checks if the following employees exist in the system.
     * @author Luis Jarquin
     * @param dataTable This is a table containing emails, names, passwords, and phone numbers associated to different employees
     */
    @Given("the following employees exist in the system")
    public void the_following_employees_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
            List<Map<String, String>> rows = dataTable.asMaps();
            for (var row: rows) {
                String email = row.get("email");
                String name = row.get("name");
                String password = row.get("password");
                String phoneNumber = row.get("phoneNumber");
                new Employee(email, name, password, phoneNumber, assetPlus);
            }
    }

    /**
     * This step initializes the manager account with the email and password provided in the dataTable.
     * @author Jerome Desrosiers
     * @param dataTable This is a table containing the email and password that will be linked to the manager account.
     */
    @Given("the following manager exists in the system")
    public void the_following_manager_exists_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(); // Gets data from the given dataTable

        for (var row : rows) { // Iterating through the rows of the dataTable (should only be one here)
            new Manager(row.get("email"), "", row.get("password"), "", assetPlus);
        }
    }

    /**
     * Initializes asset types with the given name and expected life span.
     * @author Kevin Li
     * @param dataTable
     */
    @Given("the following asset types exist in the system")
    public void the_following_asset_types_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, Object>> rows = dataTable.asMaps(String.class, Object.class);

        for (Map<String, Object> row : rows) {
            String name = (row.get("name")).toString();
            int expectedLifeSpan = Integer.parseInt(row.get("expectedLifeSpan").toString());
            assetPlus.addAssetType(name, expectedLifeSpan);
        }
    }

    /**
     * Initializes assets each with a specific assetNumber, floorNumber, roomNumber, purchaseDate, and type for future testing.
     * @author Tessa Hason
     * @param dataTable
     */

    @Given("the following assets exist in the system")
    public void the_following_assets_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(); // Retrieving the data from the feature
        // file is a usable format

        for (var row : rows) { // iterating through the rows of the data from the
            assetPlus.addSpecificAsset(Integer.parseInt(row.get("assetNumber")),
                    Integer.parseInt(row.get("floorNumber")),
                    Integer.parseInt(row.get("roomNumber")),
                    Date.valueOf(row.get("purchaseDate")),
                    AssetType.getWithName(row.get("type")));
        }
    }

    /**
     * THIS STEP DEF'S DEFINITION
     * @author Mathieu Allaire
     * @param dataTable
     */
    @Given("the following tickets exist in the system")
    public void the_following_tickets_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }

    /**
     * Initializes the notes with the date they were added and the description, then adds them to the corresponding notes.
     * @author Yuri Sorice
     * @param dataTable
     */
    @Given("the following notes exist in the system")
    public void the_following_notes_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(); // Getting Data

        for (var row : rows) {
          MaintenanceTicket noteAdd = assetPlus.getMaintenanceTicket(Integer.parseInt(row.get("ticketId")));
          noteAdd.addTicketNote(Date.valueOf(row.get("addedOnDate")),
                                String.format(row.get("description")),
                                (HotelStaff) HotelStaff.getWithEmail(row.get("noteTaker")));
        }
    }

    /**
     * This step definition initializes the images is the system
     * @author Luis Jarquin
     * @param dataTable This table contains the image URLs associated to different ticket IDs
     */
    @Given("the following ticket images exist in the system")
    public void the_following_ticket_images_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        for (var row : rows) {
            String imageUrl = row.get("imageUrl");
            int ticketId = Integer.parseInt(row.get("ticketId"));
            new TicketImage(imageUrl, MaintenanceTicket.getWithId(ticketId));
        }
    }

    /**
     * This step initializes the maintenace ticket with ticket iD ticketId to the state ticketState and if needsManagerApproval is "True", assigns a ticket approver to the ticket.
     * @author Jerome Desrosiers
     * @param ticketId This string contains the ticket iD of the desired maintenance ticket.
     * @param ticketState This string contains the state of the maintenance ticket.
     * @param needsManagerApproval This string contains either "True" or "False" and is used to determine if the maintenance ticket needs manager approval.
     */
    @Given("ticket {ticketId} is marked as {ticketState} with requires approval {needsManagerApproval}")
    public void ticket_is_marked_as_with_requires_approval(String ticketId, String ticketState, String needsManagerApproval) {
        MaintenanceTicket markedTicket = assetPlus.getMaintenanceTicket(Integer.parseInt(ticketId)); // Getting the maintenance ticket from the input ticketId.

        // General values (HotelStaff, PriorityLevel, TimeEstimate)
        HotelStaff ticketFixer = (HotelStaff) assetPlus.getManager(); // Here we chose the manager as the assigned hotel staff to a ticket as there's always a manager but we are unaware of the existing employees and it is possible that no employees other than the manager exists.
        PriorityLevel priorityLevel = PriorityLevel.Low; // Initialise at lowest possible value.
        TimeEstimate timeEstimate = TimeEstimate.LessThanADay; // Initialise at lowest possible value.

        // If needsManagerApproval is "true", the maintenance ticket requires manager approval.
        if (needsManagerApproval.equalsIgnoreCase("true")) {
            markedTicket.setFixApprover(assetPlus.getManager());
        }

        // Set the state of the maintenance ticket according to the ticketState input.
        // Note, if ticketState is "Open", nothing happens.
        if (ticketState.equalsIgnoreCase("assigned")) {
            markedTicket.assign(ticketFixer, priorityLevel, timeEstimate, false);
        }
        else if (ticketState.equalsIgnoreCase("inprogress")) {
            markedTicket.assign(ticketFixer, priorityLevel, timeEstimate, false);
            markedTicket.startWork();
        }
        else if (ticketState.equalsIgnoreCase("resolved")) {
            markedTicket.assign(ticketFixer, priorityLevel, timeEstimate, true);
            markedTicket.startWork();
            markedTicket.markAsResolved();
        }
        else if (ticketState.equalsIgnoreCase("closed")) {
            markedTicket.assign(ticketFixer, priorityLevel, timeEstimate, false);
            markedTicket.startWork();
            markedTicket.markAsResolved();
        }
    }

    /**
     * THIS STEP DEF'S DEFINITION
     * @author Kevin Li
     * @param string
     * @param string2
     */
    @Given("ticket {string} is marked as {string}")
    public void ticket_is_marked_as(String string, String string2) {
        MaintenanceTicket ticket = assetPlus.getMaintenanceTicket(Integer.parseInt(string));
        ticket.getTicketStatusFullName();
    }

    /**
     * Initializes the tickets field
     * @author Tessa Hason
     */
    @When("the manager attempts to view all maintenance tickets in the system")
    public void the_manager_attempts_to_view_all_maintenance_tickets_in_the_system() {
        tickets = AssetPlusFeatureSet6Controller.getTickets();
    }

    /**
     * THIS STEP DEF'S DEFINITION
     * @author Mathieu Allaire
     * @param string
     * @param string2
     * @param string3
     * @param string4
     * @param string5
     */
    @When("the manager attempts to assign the ticket {string} to {string} with estimated time {string}, priority {string}, and requires approval {string}")
    public void the_manager_attempts_to_assign_the_ticket_to_with_estimated_time_priority_and_requires_approval(
            String string, String string2, String string3, String string4, String string5) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    /**
     * This method sets the ticket status to inProgress
     * @author Yuri Sorice
     * @param ticketID ID of the given maintenance ticket
     */
    @When("the hotel staff attempts to start the ticket {ticketID}")
    public void the_hotel_staff_attempts_to_start_the_ticket(String ticketID) {
        MaintenanceTicket toStart = assetPlus.getMaintenanceTicket(Integer.parseInt(ticketID)); //getting the maintenance ticket from input
        toStart.startWork(); //setting the ticket status to inProgress
    }

    /**
     * Sets the ticket status to approved
     * @author Luis Jarquin
     * @param ticketID ticket ID of desired ticket
     */
    @When("the manager attempts to approve the ticket {ticketID}")
    public void the_manager_attempts_to_approve_the_ticket(String ticketID) {
        MaintenanceTicket toApprove = assetPlus.getMaintenanceTicket(Integer.parseInt(ticketID));
        toApprove.approveWork();
    }

    /**
     * This step sets the ticket's status as Closed.
     * @author Jerome Desrosiers
     * @param ticketId This is a string containing the ticketId of the ticket to mark as Closed.
     */
    @When("the hotel staff attempts to complete the ticket {ticketId}")
    public void the_hotel_staff_attempts_to_complete_the_ticket(String ticketId) {
        MaintenanceTicket toComplete = assetPlus.getMaintenanceTicket(Integer.parseInt(ticketId)); // Getting the maintenance ticket from the input ticketId.
        toComplete.markAsResolved(); // Setting the ticket status to Closed.
    }

    /**
     * This step attempts to disapprove work on a ticket and set its status as InProgress.
     * @author Kevin Li
     * @param string
     * @param string2
     * @param string3
     */
    @When("the manager attempts to disapprove the ticket {string} on date {string} and with reason {string}")
    public void the_manager_attempts_to_disapprove_the_ticket_on_date_and_with_reason(String string, String string2, String string3) {
        MaintenanceTicket toDisapprove = assetPlus.getMaintenanceTicket(Integer.parseInt(string));
        toDisapprove.disapproveWork(string2, string3);
    }

    /**
     * Checks if the ticket in question has expected status expectedStatus.
     * @author Tessa Hason
     * @param givenTicketID
     * @param expectedStatus
     */
    @Then("the ticket {givenTicketID} shall be marked as {expectedStatus}")
    public void the_ticket_shall_be_marked_as(String givenTicketID, String expectedStatus) {
        int ticketID = Integer.parseInt(givenTicketID);

        TOMaintenanceTicket ticketInQuestion = null;
        for (TOMaintenanceTicket ticket : tickets) { //check all tickets for the ticket in question with the specific ticketID
            if (ticketID == ticket.getId()) {
                ticketInQuestion = ticket;
            }
        }

        assertNotNull(ticketInQuestion);

        //expected, actual
        assertEquals(expectedStatus, ticketInQuestion.getStatus());
    }

    /**
     * THIS STEP DEF'S DEFINITION
     * @author Mathieu Allaire
     * @param string
     */
    @Then("the system shall raise the error {string}")
    public void the_system_shall_raise_the_error(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    /**
     * Checks if the given ticketID does not exist
     * @author Yuri Sorice
     * @param ticketID ID of a maintenance ticket
     */
    @Then("the ticket {ticketID} shall not exist in the system")
    public void the_ticket_shall_not_exist_in_the_system(String ticketID) {
      Assert.assertNull(assetPlus.getMaintenanceTicket(Integer.parseInt(ticketID))); 
      //Asserts that when trying to retrieve the nonexisting ticket, nothing is returned
    }

    /**
     * Verifies that the ticket in question has the appropriate estimated time, priority, and approval requirement
     * @author Luis Jarquin
     * @param ticketID Ticket ID
     * @param estimatedTime Estimated time for ticket resolution
     * @param priorityLevel Priority level of the ticket
     * @param requiresApproval Approval is or is not required (boolean)
     */
    @Then("the ticket {ticketID} shall have estimated time {estimatedTime}, priority {priorityLevel}, and requires approval {requiresApproval}")
    public void the_ticket_shall_have_estimated_time_priority_and_requires_approval(String ticketID,
    String estimatedTime, String priorityLevel, String requiresApproval) {
        MaintenanceTicket timePriorityApprovalTicket = assetPlus.getMaintenanceTicket(Integer.parseInt(ticketID));
        Assert.assertEquals(estimatedTime, timePriorityApprovalTicket.getTimeToResolve().toString());
        Assert.assertEquals(priorityLevel, timePriorityApprovalTicket.getPriority().toString());
        if (timePriorityApprovalTicket.getTicketStatusFullName().equalsIgnoreCase("open")){
            assertNull(timePriorityApprovalTicket.getFixApprover());
        } else {
            Assert.assertEquals(Boolean.parseBoolean(requiresApproval), timePriorityApprovalTicket.hasFixApprover());
        }
    }

    /**
     * This step makes sure that the assigned ticket is assigned to the right employee.
     * @author Jerome Desrosiers
     * @param ticketId This string contains the ticket iD of the ticket that should be assigned to an hotel employee.
     * @param employeeEmail This string contains the employee email of the employee the ticket should be assigned to.
     */
    @Then("the ticket {ticketId} shall be assigned to {employeeEmail}")
    public void the_ticket_shall_be_assigned_to(String ticketId, String employeeEmail) {
        MaintenanceTicket isAssigned = assetPlus.getMaintenanceTicket(Integer.parseInt(ticketId)); // Gets the desired ticket using the ticket iD stored in ticketId string. 
        assertEquals(employeeEmail, isAssigned.getTicketFixer().getEmail());
    }

    /**
     * This step checks that the number of tickets in the system is the expected number.
     * @author Kevin Li
     * @param string
     */
    @Then("the number of tickets in the system shall be {string}")
    public void the_number_of_tickets_in_the_system_shall_be(String string) {
        int expectedNumberOfTickets = Integer.parseInt(string);
        int actualNumberOfTickets = assetPlus.numberOfMaintenanceTickets();
        Assertions.assertEquals(expectedNumberOfTickets, actualNumberOfTickets);
    }

    /**
     * Checks if the maintenance tickets are present in the system. Checks if they have all of the same features (ie ticketID, raisedByEmail, etc). 
     * @author Tessa Hason
     * @param dataTable
     */
    @Then("the following maintenance tickets shall be presented")
    public void the_following_maintenance_tickets_shall_be_presented(
            io.cucumber.datatable.DataTable dataTable) {

        List<Map<String, String>> rows = dataTable.asMaps();

        int i = 0;

        for (var row : rows)  {
            TOMaintenanceTicket currentTicket = tickets.get(i);

            //check if each attribute is equal

            //check if ticket ID is the same
            int ticketID = Integer.parseInt(row.get("id"));
            Assert.assertEquals(ticketID, currentTicket.getId());

            //check if ticket Raiser is the same
            String ticketRaiserEmail = row.get("ticketRaiser");
            Assert.assertEquals(ticketRaiserEmail, currentTicket.getRaisedByEmail());

            //check if ticket raised on Date is equal
            Date raisedOnDate = Date.valueOf(row.get("raisedOnDate"));
            Assert.assertEquals(raisedOnDate, currentTicket.getRaisedOnDate());

            //check if ticket description is equal
            String description = row.get("description");
            Assert.assertEquals(description, currentTicket.getDescription());

            //check if ticket asset is the same
            String asset = row.get("asset");
            Assert.assertEquals(asset, currentTicket.getAssetName());

            //check if ticket expected lifespan is the same
            String expectedLifeSpanString = row.get("expectLifeSpan");
            int expectedLifeSpan = -1; //initialize variable
            if (expectedLifeSpanString != null) {
                expectedLifeSpan = Integer.parseInt(expectedLifeSpanString);
            }
            Assert.assertEquals(expectedLifeSpan, currentTicket.getExpectLifeSpanInDays());


            //check if ticket purchase date is same
            String purchaseDateString = row.get("purchaseDate");
            Date purchaseDate = null;
            if (purchaseDateString != null) {
                purchaseDate = Date.valueOf(purchaseDateString);
            }
            Assert.assertEquals(purchaseDate, currentTicket.getPurchaseDate());

            //check if ticket floor Number is same
            String floorNumberString = row.get("floorNumber");
            int floorNumber = -1;
            if (floorNumberString != null) {
                floorNumber = Integer.parseInt(floorNumberString);
            }
            Assert.assertEquals(floorNumber, currentTicket.getFloorNumber());

            //check if ticket room Number is same
            String roomNumberString = row.get("roomNumber");
            int roomNumber = -1;
            if (roomNumberString != null) {
                roomNumber = Integer.parseInt(roomNumberString);
            }
            Assert.assertEquals(roomNumber, currentTicket.getRoomNumber());

            //check if status is the same

            String status = row.get("status");
            Assert.assertEquals(status, currentTicket.getStatus());


            //check if fixedByEmail is the same

            String fixedByEmail = row.get("fixedByEmail");
            Assert.assertEquals(fixedByEmail, currentTicket.getFixedByEmail());

            //check if time to resolve is the same
            String timeToResolve = row.get("timeToResolve");
            Assert.assertEquals(timeToResolve, currentTicket.getTimeToResolve());

            //check if priority to resolve is the same

            String priority = row.get("priority");
            Assert.assertEquals(priority, currentTicket.getPriority());

            String approvalRequiredString = row.get("approvalRequired");
            boolean approvalRequired;
            if (approvalRequiredString.equals("true")){
                approvalRequired = (currentTicket.getApprovalRequired() == true);
                Assert.assertTrue(approvalRequired);
            } else if (approvalRequiredString.equals("false")){
                approvalRequired = (currentTicket.getApprovalRequired() == false);
                Assert.assertTrue(approvalRequired);
            } else{
                Assert.assertNull(currentTicket.getApprovalRequired());
            }
            
            //increment i
            i++;
        }
    }

    /**
     * THIS STEP DEF'S DEFINITION
     * @author Mathieu Allaire
     * @param dataTable
     */
    @Then("the ticket with id {string} shall have the following notes")
    public void the_ticket_with_id_shall_have_the_following_notes(String string,
                                                                  io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }

    /**
     * Makes sure that the number of notes of a specific maintenance ticket is zero
     * @author Yuri Sorice
     * @param ticketID ID of a maintenance ticket
     */
    @Then("the ticket with id {string} shall have no notes")
    public void the_ticket_with_id_shall_have_no_notes(String ticketID) {
      MaintenanceTicket noNotes = assetPlus.getMaintenanceTicket(Integer.parseInt(ticketID));
      Assert.assertEquals(0, noNotes.numberOfTicketNotes());
    }
    }

    /**
     * Verifies that the ticket has the following images
     * @author Luis Jarquin, Jerome Desrosiers
     * @param ticketID ticket ID
     * @param dataTable Contains image URLs of corresponding ticket ID
     */
    @Then("the ticket with id {ticketID} shall have the following images")
    public void the_ticket_with_id_shall_have_the_following_images(String ticketID, io.cucumber.datatable.DataTable dataTable) {
        
        MaintenanceTicket hasTheseImages = assetPlus.getMaintenanceTicket(Integer.parseInt(ticketID)); // gets the maintenance ticket using string parameter
        List<Map<String, String>> rows = dataTable.asMaps();
        for (var row : rows){
            assertTrue(hasTheseImages.getTicketImages().contains(row.get("imageUrl")));
        }

    }

    /**
     * This step makes sure that the ticket with a specific id does not have any images.
     * @author Jerome Desrosiers
     * @param ticketId This string contains the ticket iD of the ticket that should not have any images.
     */
    @Then("the ticket with id {ticketId} shall have no images")
    public void the_ticket_with_id_shall_have_no_images(String ticketId) {
        MaintenanceTicket noImages = assetPlus.getMaintenanceTicket(Integer.parseInt(ticketId)); // Gets desired maintenance ticket using the ticket iD given in ticketId input.
        assertFalse(noImages.hasTicketImages()); // Verifies that the maintenance ticket has no associated images.
    }
}
