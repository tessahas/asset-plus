package ca.mcgill.ecse.assetplus.features;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet6Controller;
import ca.mcgill.ecse.assetplus.controller.TOMaintenanceTicket;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Map;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet7Controller;
import ca.mcgill.ecse.assetplus.model.*;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket.TicketStatus;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MaintenanceTicketsStepDefinitions {
    private AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
    private List<TOMaintenanceTicket> tickets;

    /**
     * THIS STEP DEF'S DEFINITION
     * @author Luis Jarquin
     * @param dataTable
     */
    @Given("the following employees exist in the system")
    public void the_following_employees_exist_in_the_system(
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
     * THIS STEP DEF'S DEFINITION
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
     * THIS STEP DEF'S DEFINITION
     * @author Luis Jarquin
     * @param dataTable
     */
    @Given("the following ticket images exist in the system")
    public void the_following_ticket_images_exist_in_the_system(
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
     * THIS STEP DEF'S DEFINITION
     * @author Jerome Desrosiers
     * @param string
     * @param string2
     * @param string3
     */
    @Given("ticket {string} is marked as {string} with requires approval {string}")
    public void ticket_is_marked_as_with_requires_approval(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
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
     * THIS STEP DEF'S DEFINITION
     * @author Yuri Sorice
     * @param string
     */
    @When("the hotel staff attempts to start the ticket {string}")
    public void the_hotel_staff_attempts_to_start_the_ticket(String string) {
        MaintenanceTicket toStart = assetPlus.getMaintenanceTicket(Integer.parseInt(string)); //getting the maintenance ticket from input
        toStart.startWork(); //setting the ticket status to inProgress
    }

    /**
     * THIS STEP DEF'S DEFINITION
     * @author Luis Jarquin
     * @param string
     */
    @When("the manager attempts to approve the ticket {string}")
    public void the_manager_attempts_to_approve_the_ticket(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    /**
     * This step sets the ticket's status as Closed.
     * @author Jerome Desrosiers
     * @param string This is a string containing the ticket iD of the ticket to mark as Closed.
     */
    @When("the hotel staff attempts to complete the ticket {string}")
    public void the_hotel_staff_attempts_to_complete_the_ticket(String string) {
        MaintenanceTicket toComplete = assetPlus.getMaintenanceTicket(Integer.parseInt(string)); // Getting the maintenance ticket from the input ticket iD in string.
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
     * THIS STEP DEF'S DEFINITION
     * @author Yuri Sorice
     * @param string
     */
    @Then("the ticket {string} shall not exist in the system")
    public void the_ticket_shall_not_exist_in_the_system(String string) {
      Assert.assertNull(assetPlus.getMaintenanceTicket(Integer.parseInt(string))); 
      //Asserts that when trying to retrieve the nonexisting ticket, nothing is returned
    }

    /**
     * THIS STEP DEF'S DEFINITION
     * @author Luis Jarquin
     * @param string
     * @param string2
     * @param string3
     * @param string4
     */
    @Then("the ticket {string} shall have estimated time {string}, priority {string}, and requires approval {string}")
    public void the_ticket_shall_have_estimated_time_priority_and_requires_approval(String string,
                                                                                    String string2, String string3, String string4) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    /**
     * This step makes sure that the assigned ticket is assigned to the right employee.
     * @author Jerome Desrosiers
     * @param string This string contains the ticket iD of the ticket that should be assigned.
     * @param string2 This string contains the employee email of the employee the ticket should be assigned to.
     */
    @Then("the ticket {string} shall be assigned to {string}")
    public void the_ticket_shall_be_assigned_to(String string, String string2) {
        int ticketID = Integer.parseInt(string); // Gets the ticket's iD from the string.

        TOMaintenanceTicket ticketInQuestion = null;
        for (TOMaintenanceTicket ticket : tickets) { // Check all tickets for the ticket in question with the specific ticketID specified in string.
            if (ticketID == ticket.getId()) {
                ticketInQuestion = ticket; // Once found, assign it to a TOMaintenanceTicket object and get out of the search loop.
                break;
            }
        }

        // Makes sure the ticket exists and was found by the above code.
        assertNotNull(ticketInQuestion);

        // Expected vs actual employee emails.
        assertEquals(string2, ticketInQuestion.getFixedByEmail());
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
     * THIS STEP DEF'S DEFINITION
     * @author Yuri Sorice
     * @param string
     */
    @Then("the ticket with id {string} shall have no notes")
    public void the_ticket_with_id_shall_have_no_notes(String string) {
      MaintenanceTicket noNotes = assetPlus.getMaintenanceTicket(Integer.parseInt(string));
      Assert.assertEquals(0, noNotes.numberOfTicketNotes());
    }

    /**
     * THIS STEP DEF'S DEFINITION
     * @author Luis Jarquin
     * @param string
     * @param dataTable
     */
    @Then("the ticket with id {string} shall have the following images")
    public void the_ticket_with_id_shall_have_the_following_images(String string,
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
     * This step makes sure that the ticket with a specific id does not have any images.
     * @author Jerome Desrosiers
     * @param string This string contains the ticket iD of the ticket that should not have any images.
     */
    @Then("the ticket with id {string} shall have no images")
    public void the_ticket_with_id_shall_have_no_images(String string) {
         int ticketID = Integer.parseInt(string); // Gets the ticket's iD from the string.

        TOMaintenanceTicket ticketInQuestion = null;
        for (TOMaintenanceTicket ticket : tickets) { // Check all tickets for the ticket in question with the specific ticketID specified in string.
            if (ticketID == ticket.getId()) {
                ticketInQuestion = ticket; // Once found, assign it to a TOMaintenanceTicket object and get out of the search loop.
                break;
            }
        }

        // Makes sure the ticket exists and was found by the above code.
        assertNotNull(ticketInQuestion);

        // Expected vs actual number of images associated with the ticket.
        assertEquals(0, ticketInQuestion.getImageURLs().size());
    }
}
