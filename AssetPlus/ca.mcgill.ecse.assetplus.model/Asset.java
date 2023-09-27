/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 17 "assetplus.ump"
// line 112 "assetplus.ump"
public class Asset
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextAssetNumber = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Asset Attributes
  private String assetType;
  private Date purchaseDate;
  private String expectedLifespan;
  private int floor;
  private int roomNumber;

  //Autounique Attributes
  private int assetNumber;

  //Asset Associations
  private List<MaintenanceTicket> tickets;
  private Manager assetCreator;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Asset(String aAssetType, Date aPurchaseDate, String aExpectedLifespan, int aFloor, Manager aAssetCreator)
  {
    assetType = aAssetType;
    purchaseDate = aPurchaseDate;
    expectedLifespan = aExpectedLifespan;
    floor = aFloor;
    roomNumber = 0;
    assetNumber = nextAssetNumber++;
    tickets = new ArrayList<MaintenanceTicket>();
    boolean didAddAssetCreator = setAssetCreator(aAssetCreator);
    if (!didAddAssetCreator)
    {
      throw new RuntimeException("Unable to create asset due to assetCreator. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAssetType(String aAssetType)
  {
    boolean wasSet = false;
    assetType = aAssetType;
    wasSet = true;
    return wasSet;
  }

  public boolean setPurchaseDate(Date aPurchaseDate)
  {
    boolean wasSet = false;
    purchaseDate = aPurchaseDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setExpectedLifespan(String aExpectedLifespan)
  {
    boolean wasSet = false;
    expectedLifespan = aExpectedLifespan;
    wasSet = true;
    return wasSet;
  }

  public boolean setFloor(int aFloor)
  {
    boolean wasSet = false;
    floor = aFloor;
    wasSet = true;
    return wasSet;
  }

  public boolean setRoomNumber(int aRoomNumber)
  {
    boolean wasSet = false;
    roomNumber = aRoomNumber;
    wasSet = true;
    return wasSet;
  }

  public String getAssetType()
  {
    return assetType;
  }

  public Date getPurchaseDate()
  {
    return purchaseDate;
  }

  public String getExpectedLifespan()
  {
    return expectedLifespan;
  }

  public int getFloor()
  {
    return floor;
  }

  public int getRoomNumber()
  {
    return roomNumber;
  }

  public int getAssetNumber()
  {
    return assetNumber;
  }
  /* Code from template association_GetMany */
  public MaintenanceTicket getTicket(int index)
  {
    MaintenanceTicket aTicket = tickets.get(index);
    return aTicket;
  }

  public List<MaintenanceTicket> getTickets()
  {
    List<MaintenanceTicket> newTickets = Collections.unmodifiableList(tickets);
    return newTickets;
  }

  public int numberOfTickets()
  {
    int number = tickets.size();
    return number;
  }

  public boolean hasTickets()
  {
    boolean has = tickets.size() > 0;
    return has;
  }

  public int indexOfTicket(MaintenanceTicket aTicket)
  {
    int index = tickets.indexOf(aTicket);
    return index;
  }
  /* Code from template association_GetOne */
  public Manager getAssetCreator()
  {
    return assetCreator;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTickets()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addTicket(MaintenanceTicket aTicket)
  {
    boolean wasAdded = false;
    if (tickets.contains(aTicket)) { return false; }
    Asset existingAsset = aTicket.getAsset();
    if (existingAsset == null)
    {
      aTicket.setAsset(this);
    }
    else if (!this.equals(existingAsset))
    {
      existingAsset.removeTicket(aTicket);
      addTicket(aTicket);
    }
    else
    {
      tickets.add(aTicket);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTicket(MaintenanceTicket aTicket)
  {
    boolean wasRemoved = false;
    if (tickets.contains(aTicket))
    {
      tickets.remove(aTicket);
      aTicket.setAsset(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTicketAt(MaintenanceTicket aTicket, int index)
  {  
    boolean wasAdded = false;
    if(addTicket(aTicket))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTickets()) { index = numberOfTickets() - 1; }
      tickets.remove(aTicket);
      tickets.add(index, aTicket);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTicketAt(MaintenanceTicket aTicket, int index)
  {
    boolean wasAdded = false;
    if(tickets.contains(aTicket))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTickets()) { index = numberOfTickets() - 1; }
      tickets.remove(aTicket);
      tickets.add(index, aTicket);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTicketAt(aTicket, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAssetCreator(Manager aAssetCreator)
  {
    boolean wasSet = false;
    if (aAssetCreator == null)
    {
      return wasSet;
    }

    Manager existingAssetCreator = assetCreator;
    assetCreator = aAssetCreator;
    if (existingAssetCreator != null && !existingAssetCreator.equals(aAssetCreator))
    {
      existingAssetCreator.removeAsset(this);
    }
    assetCreator.addAsset(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while( !tickets.isEmpty() )
    {
      tickets.get(0).setAsset(null);
    }
    Manager placeholderAssetCreator = assetCreator;
    this.assetCreator = null;
    if(placeholderAssetCreator != null)
    {
      placeholderAssetCreator.removeAsset(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "assetNumber" + ":" + getAssetNumber()+ "," +
            "assetType" + ":" + getAssetType()+ "," +
            "expectedLifespan" + ":" + getExpectedLifespan()+ "," +
            "floor" + ":" + getFloor()+ "," +
            "roomNumber" + ":" + getRoomNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "purchaseDate" + "=" + (getPurchaseDate() != null ? !getPurchaseDate().equals(this)  ? getPurchaseDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "assetCreator = "+(getAssetCreator()!=null?Integer.toHexString(System.identityHashCode(getAssetCreator())):"null");
  }
}