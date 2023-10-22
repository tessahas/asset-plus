package ca.mcgill.ecse.assetplus.controller;
import ca.mcgill.ecse.assetplus.model.*;

import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;

/**
 * <h1>AssetPlusFeatureSet1Controller</h1>
 * 
 * The AssetPlusFeatureSet1Controller program is used
 * to control updates and registrations of users made in the 
 * AssetPlus application. These users include the manager,
 * the employees and the guests.
 * 
 * @author Mathieu Allaire
 * @version 1.0
 * @since 2023-10-19
 */


public class AssetPlusFeatureSet1Controller {
	/**
   	* <h3>onlyOneLowerChar</h3>
   	* This method is a private helper method contained inside the updateManager 
   	* method. It occurs when updateManager checks if the input string contains 
   	* only one lower-case character.
   	* 
   	* @param String input - the input string the updateManager method wants to analyze.
   	* @return boolean - true if the string contains a single lower-case character, false otherwise.
   	* 
   	* @author Mathieu Allaire
   	*/
	private static boolean onlyOneLowerChar(String input) {
		boolean flip = false;
	    for (char c : input.toCharArray()) {
	        if (Character.isLowerCase(c)) {
	            if(flip == true) {
	            	return false;
	            }
	        	flip = true;
	        }
	    }
	    return flip;
	}
	
	
	/**
   	* <h3>onlyOneUpperChar</h3>
   	* This method is a private helper method contained inside the updateManager 
   	* method. It occurs when updateManager checks if the input string contains 
   	* only one upper-case character.
   	* 
   	* @param String input - the input string the updateManager method wants to analyze.
   	* @return boolean - true if the string contains a single upper-case character, false otherwise.
   	* 
   	* @author Mathieu Allaire
   	*/
	private static boolean onlyOneUpperChar(String input) {
		boolean flip = false;
	    for (char c : input.toCharArray()) {
	        if (Character.isUpperCase(c)) {
	            if(flip == true) {
	            	return false;
	            }
	        	flip = true;
	        }
	    }
	    return flip;
	}
	

	/**
   	* <h1>updateManager</h1> 
   	* This method is called whenever the Manager attempts to update their own
   	* account's password.
   	* 
   	* @param String password - The new password the Manager would like to have to unlock his account.
   	* @return String - This returns a string with an error message gathered during execution. If it is empty, the Manager's account update was successful.
   	* 
   	* @author Mathieu Allaire
   	*/
	
  public static String updateManager(String password) {
    String errorMessage = "";
    AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
    Manager manager = assetPlus.getManager();
    if (!assetPlus.hasManager()) {
    	errorMessage += "The manager does not yet exist";
    }
    else if (!(manager.getEmail().equals("manager@ap.com"))) {
    	errorMessage += "The manager does not have the correct email address";
    }
    else if (!(manager.getPassword().equals("manager"))) {
    	errorMessage += "You do not have permission to change the password";
    }
    else if(password.length()<4) {
    	errorMessage += "The password must be at least four characters long";
    }
    else if(  (password.contains("!")&& !password.contains("#") && !password.contains("$")) ||  (!password.contains("!")&& password.contains("#") && !password.contains("$"))
    		|| (!password.contains("!")&& !password.contains("#") && password.contains("$")) ){
    	errorMessage += "The password must contain one character out of !#$";		
    }
    else if(password.equals("")) {
    	errorMessage += "The password must not be empty";
    }
    else if(!onlyOneLowerChar(password)) {
    	errorMessage += "The password must contain exactly one lower-case character";
    }
    else if(!onlyOneUpperChar(password)) {
    	errorMessage += "The password must contain exactly one upper-case character";
    }
    else if(password.contains(" ")) {
    	errorMessage += "The password must not contain any spaces";
    }
    if(!errorMessage.isEmpty()) return errorMessage + ", the manager has unsuccessfully updated their password";
    manager.setPassword(password);
    return errorMessage;
  }
  
  /**
 	* <h1>addEmployeeOrGuest</h1> 
 	* This method is called whenever an Employee or a Guest attempts to register
 	* an account in the AssetPlus application.
 	* 
 	* @param String email - the email of the registering user. If the user is an Employee, the email has to end in "@ap.com".
 	* @param String password - the password of the created account.
 	* @param String name - (Optional, can be an empty String) the name of the registering user.
 	* @param String phoneNumber - (Optional, can be an empty String) the phone number of the registering user.
 	* @param boolean isEmployee - true if the user is an Employee, false if the user is a Guest.
 	* @return String - This returns a string with an error message gathered during execution. If it is empty, the Employee or Guest's account registration was successful.
 	* 
 	* @author Mathieu Allaire
 	*/
  public static String addEmployeeOrGuest(String email, String password, String name, String phoneNumber,
        boolean isEmployee) {
	  	String errorMessage = "";
	    AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
	    if (!assetPlus.hasManager()) {
	    	errorMessage += "The manager does not yet exist";
	    }
	    else if(email.equals("manager@ap.com")) {
	    	errorMessage += "Only the manager can have this email";
	    }
	    else if(!(email.substring(email.length()-4).equals(".com"))) {
	    	errorMessage += "The email must end in .com";
	    }
	    else if(email.contains(" ")) {
	    	errorMessage += "The email must not contain any spaces";
	    }
	    
	    else if(!email.contains("@")){
	    	errorMessage += "The email must contain at least one @";
	    }
	    
	    else if(password.equals("")) {
	    	errorMessage += "The password must not be empty";
	    }
	    
	    else if (password.contains(" ")) {
	    	errorMessage +=" The password must not contain any spaces";
	    }
	    else if (isEmployee) {
	    	if(email.length()<8) {
	    		errorMessage += "The employee's email must at least have one character before ending with @ap.com";
	    		
	    	}
	    	else if(!(email.substring(email.length()-7).equals("@ap.com"))) {
	    		errorMessage += "The employee's email must end in @ap.com";
	    		
	    	}
	    	if(!errorMessage.isEmpty()) return errorMessage + ", the employee has unsuccessfully been added";
	    	for(Employee employee:assetPlus.getEmployees()) {
	    		if (employee.getEmail().equals(email)) {
	    			errorMessage += "This email is already linked to another employee's account";
	    			break;
	    		}
	    	}
	    	Employee newEmployee = new Employee(email, password, name, phoneNumber, assetPlus);
	    	assetPlus.addEmployee(newEmployee);
	    	return errorMessage;
	    }
	    else {
	    	if(email.length()<7) {
	    		errorMessage += "The guest's email must at least have 7 characters";
	    		
	    	}
	    	else if(email.substring(email.length()-7).equals("@ap.com")) {
	    		errorMessage += "The guest's email must not end in @ap.com";
	    	
	    	}
	    	if(!errorMessage.isEmpty()) return errorMessage + ", the guest has unsuccessfully been added";
	    	for(Guest guest:assetPlus.getGuests()) {
	    		if (guest.getEmail().equals(email)) {
	    			errorMessage += "This email is already linked to another guest's account";
	    			break;
	    		}
	    	}
	    	Guest newGuest = new Guest(email,password,name,phoneNumber,assetPlus);
	    	assetPlus.addGuest(newGuest);
	    	return errorMessage;
	    	
	    }
	    return errorMessage;
  
  }

  
  /**
 	* <h1>updateEmployeeOrGuest</h1>
 	* This method is called whenever an Employee or a Guest attempts
 	* to update their account's information, whether it is their password,
 	* their name, or their phone number. 
 	* 
 	* @param String email - The email of the Employee or Guest's account. Since an Employee email must end in "@ap.com" and both an Employee and Guest's email must be valid, this email must also be valid as described in the earlier addEmployeeOrGuest method. 
 	* @param String newPassword - The new password the Employee or Guest wants to assign to their account. It cannot be empty or contain spaces.
 	* @param String newName - (Optional, can be an empty String) the new name the Employee or Guest wants to assign to their account.
 	* @param String newPhoneNumber - (Optional, can be an empty String) the new phone number the Employee or Guest wants to assign to their account.
 	* @return String - This returns a string with an error message gathered during execution, if this returned string is empty, the update of the Employee or Guest's account was successful.
 	* 
 	* @author Mathieu Allaire
 	*/
  public static String updateEmployeeOrGuest(String email, String newPassword, String newName, String newPhoneNumber) {
	  	
	  String errorMessage = "";
	  AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
	  
	  if (!assetPlus.hasManager()) {
	    errorMessage += "The manager does not yet exist";
	  }
	  else if(newPassword.equals("")) {
	    errorMessage += "The password must not be empty";
	  }
	    
	  else if (newPassword.contains(" ")) {
	    errorMessage +=" The password must not contain any spaces";
	  }
	  if(!errorMessage.isEmpty()) return errorMessage + ", the guest or employee has not been updated";
	  
	  if(email.substring(email.length()-7).equals("@ap.com")) {
		  for(Employee employee:assetPlus.getEmployees()) {
			  if (employee.getEmail().equals(email)) {
				  employee.setPassword(newPassword);
				  employee.setName(newName);
				  employee.setPhoneNumber(newPhoneNumber);
				  return errorMessage;
			  }
			  errorMessage += "This employee does not exist";
		  }
		  
	  }
	  else{
		  for(Guest guest:assetPlus.getGuests()) {
			  if (guest.getEmail().equals(email)) {
				  guest.setPassword(newPassword);
				  guest.setName(newName);
				  guest.setPhoneNumber(newPhoneNumber);
				  return errorMessage;
			  }
			  errorMessage += "This guest does not exist";
		  }
	  } 
	    
	  return errorMessage;
  }

}

