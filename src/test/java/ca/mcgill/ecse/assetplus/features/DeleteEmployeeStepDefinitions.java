package ca.mcgill.ecse.assetplus.features;
import ca.mcgill.ecse.assetplus.model.*;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;

public class DeleteEmployeeStepDefinitions {

  private AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

  @Given("the following employees exist in the system \\(p1)")
  public void the_following_employees_exist_in_the_system_p1(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row: rows) {
      String email = row.get("email");
      String name = row.get("name");
      String password = row.get("password");
      String phoneNumber = row.get("phoneNumber");
      new Employee(email, name, password, phoneNumber, assetPlus);
    }
  }

  @Given("the following manager exists in the system \\(p1)")
  public void the_following_manager_exists_in_the_system_p1(
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

  @When("the employee attempts to delete their own account linked to the {string} \\(p1)")
  public void the_employee_attempts_to_delete_their_own_account_linked_to_the_p1(String string) {
    ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet6Controller.deleteEmployeeOrGuest(string);
  }
  
  /**
   * This step checks that after the employee attempts to delete their own account, their account does not exist in the system.
   * @author Jerome Desrosiers
   */
  @Then("the employee account linked to {string} shall not exist in the system \\(p1)")
  public void the_employee_account_linked_to_shall_not_exist_in_the_system_p1(String string) {
    
    User actualEmployee = Employee.getWithEmail(string);
    Assertions.assertEquals(null, actualEmployee);
  }
  /**
   * @author Yuri Sorice
   */
  @Then("the manager account linked to {string} shall exist in the system \\(p1)")
  public void the_manager_account_linked_to_shall_exist_in_the_system_p1(String string) {
    User expectedManager = Manager.getWithEmail(string);
    User actualManager = assetPlus.getManager();
    Assertions.assertEquals(expectedManager, actualManager);
  }
  /**
   * @author Tessa Hason
   */
  @Then("the number of employees in the system shall be {string} \\(p1)")
  public void the_number_of_employees_in_the_system_shall_be_p1(String string) {
    int expectedNumOfEmployees = Integer.parseInt(string);
    int actualNumOfEmployees = assetPlus.numberOfGuests();
    Assertions.assertEquals(expectedNumOfEmployees,actualNumOfEmployees);
  }
}
