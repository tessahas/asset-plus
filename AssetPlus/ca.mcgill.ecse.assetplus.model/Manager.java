/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 51 "assetplus.ump"
// line 153 "assetplus.ump"
public class Manager extends HotelEmployee
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Manager Attributes
  private String email;
  private String password;

  //Manager Associations
  private List<MaintenanceTicket> existingTickets;
  private List<Asset> assets;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Manager(String aEmail, String aPassword, AssetPlus aAssetPlus)
  {
    super(aEmail, aPassword, aAssetPlus);
    email = "manager@ap.com";
    resetPassword();
    existingTickets = new ArrayList<MaintenanceTicket>();
    assets = new ArrayList<Asset>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean resetPassword()
  {
    boolean wasReset = false;
    password = getDefaultPassword();
    wasReset = true;
    return wasReset;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPassword()
  {
    return password;
  }
  /* Code from template attribute_GetDefaulted */
  public String getDefaultPassword()
  {
    return "manager";
  }
  /* Code from template association_GetMany */
  public MaintenanceTicket getExistingTicket(int index)
  {
    MaintenanceTicket aExistingTicket = existingTickets.get(index);
    return aExistingTicket;
  }

  public List<MaintenanceTicket> getExistingTickets()
  {
    List<MaintenanceTicket> newExistingTickets = Collections.unmodifiableList(existingTickets);
    return newExistingTickets;
  }

  public int numberOfExistingTickets()
  {
    int number = existingTickets.size();
    return number;
  }

  public boolean hasExistingTickets()
  {
    boolean has = existingTickets.size() > 0;
    return has;
  }

  public int indexOfExistingTicket(MaintenanceTicket aExistingTicket)
  {
    int index = existingTickets.indexOf(aExistingTicket);
    return index;
  }
  /* Code from template association_GetMany */
  public Asset getAsset(int index)
  {
    Asset aAsset = assets.get(index);
    return aAsset;
  }

  public List<Asset> getAssets()
  {
    List<Asset> newAssets = Collections.unmodifiableList(assets);
    return newAssets;
  }

  public int numberOfAssets()
  {
    int number = assets.size();
    return number;
  }

  public boolean hasAssets()
  {
    boolean has = assets.size() > 0;
    return has;
  }

  public int indexOfAsset(Asset aAsset)
  {
    int index = assets.indexOf(aAsset);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfExistingTickets()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addExistingTicket(MaintenanceTicket aExistingTicket)
  {
    boolean wasAdded = false;
    if (existingTickets.contains(aExistingTicket)) { return false; }
    Manager existingTicketSupervisor = aExistingTicket.getTicketSupervisor();
    if (existingTicketSupervisor == null)
    {
      aExistingTicket.setTicketSupervisor(this);
    }
    else if (!this.equals(existingTicketSupervisor))
    {
      existingTicketSupervisor.removeExistingTicket(aExistingTicket);
      addExistingTicket(aExistingTicket);
    }
    else
    {
      existingTickets.add(aExistingTicket);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeExistingTicket(MaintenanceTicket aExistingTicket)
  {
    boolean wasRemoved = false;
    if (existingTickets.contains(aExistingTicket))
    {
      existingTickets.remove(aExistingTicket);
      aExistingTicket.setTicketSupervisor(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addExistingTicketAt(MaintenanceTicket aExistingTicket, int index)
  {  
    boolean wasAdded = false;
    if(addExistingTicket(aExistingTicket))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfExistingTickets()) { index = numberOfExistingTickets() - 1; }
      existingTickets.remove(aExistingTicket);
      existingTickets.add(index, aExistingTicket);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveExistingTicketAt(MaintenanceTicket aExistingTicket, int index)
  {
    boolean wasAdded = false;
    if(existingTickets.contains(aExistingTicket))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfExistingTickets()) { index = numberOfExistingTickets() - 1; }
      existingTickets.remove(aExistingTicket);
      existingTickets.add(index, aExistingTicket);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addExistingTicketAt(aExistingTicket, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssets()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Asset addAsset(String aAssetType, Date aPurchaseDate, String aExpectedLifespan, int aFloor)
  {
    return new Asset(aAssetType, aPurchaseDate, aExpectedLifespan, aFloor, this);
  }

  public boolean addAsset(Asset aAsset)
  {
    boolean wasAdded = false;
    if (assets.contains(aAsset)) { return false; }
    Manager existingAssetCreator = aAsset.getAssetCreator();
    boolean isNewAssetCreator = existingAssetCreator != null && !this.equals(existingAssetCreator);
    if (isNewAssetCreator)
    {
      aAsset.setAssetCreator(this);
    }
    else
    {
      assets.add(aAsset);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAsset(Asset aAsset)
  {
    boolean wasRemoved = false;
    //Unable to remove aAsset, as it must always have a assetCreator
    if (!this.equals(aAsset.getAssetCreator()))
    {
      assets.remove(aAsset);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssetAt(Asset aAsset, int index)
  {  
    boolean wasAdded = false;
    if(addAsset(aAsset))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssets()) { index = numberOfAssets() - 1; }
      assets.remove(aAsset);
      assets.add(index, aAsset);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssetAt(Asset aAsset, int index)
  {
    boolean wasAdded = false;
    if(assets.contains(aAsset))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssets()) { index = numberOfAssets() - 1; }
      assets.remove(aAsset);
      assets.add(index, aAsset);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssetAt(aAsset, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while( !existingTickets.isEmpty() )
    {
      existingTickets.get(0).setTicketSupervisor(null);
    }
    for(int i=assets.size(); i > 0; i--)
    {
      Asset aAsset = assets.get(i - 1);
      aAsset.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "]";
  }
}