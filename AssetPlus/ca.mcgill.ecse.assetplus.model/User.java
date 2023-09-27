/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 1 "assetplus.ump"
// line 89 "assetplus.ump"
public abstract class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String email;
  private String password;
  private String name;
  private String phoneNumber;

  //User Associations
  private List<MaintenanceTicket> createdTickets;
  private List<ImageLink> images;
  private List<MaintenanceTicket> viewedTickets;
  private AssetPlus assetPlus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aEmail, String aPassword, AssetPlus aAssetPlus)
  {
    email = aEmail;
    password = aPassword;
    name = null;
    phoneNumber = null;
    createdTickets = new ArrayList<MaintenanceTicket>();
    images = new ArrayList<ImageLink>();
    viewedTickets = new ArrayList<MaintenanceTicket>();
    boolean didAddAssetPlus = setAssetPlus(aAssetPlus);
    if (!didAddAssetPlus)
    {
      throw new RuntimeException("Unable to create user due to assetPlus. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPassword()
  {
    return password;
  }

  public String getName()
  {
    return name;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }
  /* Code from template association_GetMany */
  public MaintenanceTicket getCreatedTicket(int index)
  {
    MaintenanceTicket aCreatedTicket = createdTickets.get(index);
    return aCreatedTicket;
  }

  public List<MaintenanceTicket> getCreatedTickets()
  {
    List<MaintenanceTicket> newCreatedTickets = Collections.unmodifiableList(createdTickets);
    return newCreatedTickets;
  }

  public int numberOfCreatedTickets()
  {
    int number = createdTickets.size();
    return number;
  }

  public boolean hasCreatedTickets()
  {
    boolean has = createdTickets.size() > 0;
    return has;
  }

  public int indexOfCreatedTicket(MaintenanceTicket aCreatedTicket)
  {
    int index = createdTickets.indexOf(aCreatedTicket);
    return index;
  }
  /* Code from template association_GetMany */
  public ImageLink getImage(int index)
  {
    ImageLink aImage = images.get(index);
    return aImage;
  }

  public List<ImageLink> getImages()
  {
    List<ImageLink> newImages = Collections.unmodifiableList(images);
    return newImages;
  }

  public int numberOfImages()
  {
    int number = images.size();
    return number;
  }

  public boolean hasImages()
  {
    boolean has = images.size() > 0;
    return has;
  }

  public int indexOfImage(ImageLink aImage)
  {
    int index = images.indexOf(aImage);
    return index;
  }
  /* Code from template association_GetMany */
  public MaintenanceTicket getViewedTicket(int index)
  {
    MaintenanceTicket aViewedTicket = viewedTickets.get(index);
    return aViewedTicket;
  }

  public List<MaintenanceTicket> getViewedTickets()
  {
    List<MaintenanceTicket> newViewedTickets = Collections.unmodifiableList(viewedTickets);
    return newViewedTickets;
  }

  public int numberOfViewedTickets()
  {
    int number = viewedTickets.size();
    return number;
  }

  public boolean hasViewedTickets()
  {
    boolean has = viewedTickets.size() > 0;
    return has;
  }

  public int indexOfViewedTicket(MaintenanceTicket aViewedTicket)
  {
    int index = viewedTickets.indexOf(aViewedTicket);
    return index;
  }
  /* Code from template association_GetOne */
  public AssetPlus getAssetPlus()
  {
    return assetPlus;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCreatedTickets()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MaintenanceTicket addCreatedTicket(String aDescription, String aDate, boolean aIsOpen, boolean aIsResolved, boolean aRequiresManagerApproval, MaintenanceTicket.PriorityLevel aPriorityLevel, MaintenanceTicket.TimeEstimate aTimeEstimate, AssetPlus aAssetPlus, HotelEmployee aTaskAssignee)
  {
    return new MaintenanceTicket(aDescription, aDate, aIsOpen, aIsResolved, aRequiresManagerApproval, aPriorityLevel, aTimeEstimate, this, aAssetPlus, aTaskAssignee);
  }

  public boolean addCreatedTicket(MaintenanceTicket aCreatedTicket)
  {
    boolean wasAdded = false;
    if (createdTickets.contains(aCreatedTicket)) { return false; }
    User existingTicketCreator = aCreatedTicket.getTicketCreator();
    boolean isNewTicketCreator = existingTicketCreator != null && !this.equals(existingTicketCreator);
    if (isNewTicketCreator)
    {
      aCreatedTicket.setTicketCreator(this);
    }
    else
    {
      createdTickets.add(aCreatedTicket);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCreatedTicket(MaintenanceTicket aCreatedTicket)
  {
    boolean wasRemoved = false;
    //Unable to remove aCreatedTicket, as it must always have a ticketCreator
    if (!this.equals(aCreatedTicket.getTicketCreator()))
    {
      createdTickets.remove(aCreatedTicket);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCreatedTicketAt(MaintenanceTicket aCreatedTicket, int index)
  {  
    boolean wasAdded = false;
    if(addCreatedTicket(aCreatedTicket))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCreatedTickets()) { index = numberOfCreatedTickets() - 1; }
      createdTickets.remove(aCreatedTicket);
      createdTickets.add(index, aCreatedTicket);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCreatedTicketAt(MaintenanceTicket aCreatedTicket, int index)
  {
    boolean wasAdded = false;
    if(createdTickets.contains(aCreatedTicket))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCreatedTickets()) { index = numberOfCreatedTickets() - 1; }
      createdTickets.remove(aCreatedTicket);
      createdTickets.add(index, aCreatedTicket);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCreatedTicketAt(aCreatedTicket, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfImages()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ImageLink addImage(String aLinkUrl, MaintenanceTicket aConcernedTicket)
  {
    return new ImageLink(aLinkUrl, this, aConcernedTicket);
  }

  public boolean addImage(ImageLink aImage)
  {
    boolean wasAdded = false;
    if (images.contains(aImage)) { return false; }
    User existingImageProvider = aImage.getImageProvider();
    boolean isNewImageProvider = existingImageProvider != null && !this.equals(existingImageProvider);
    if (isNewImageProvider)
    {
      aImage.setImageProvider(this);
    }
    else
    {
      images.add(aImage);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeImage(ImageLink aImage)
  {
    boolean wasRemoved = false;
    //Unable to remove aImage, as it must always have a imageProvider
    if (!this.equals(aImage.getImageProvider()))
    {
      images.remove(aImage);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addImageAt(ImageLink aImage, int index)
  {  
    boolean wasAdded = false;
    if(addImage(aImage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfImages()) { index = numberOfImages() - 1; }
      images.remove(aImage);
      images.add(index, aImage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveImageAt(ImageLink aImage, int index)
  {
    boolean wasAdded = false;
    if(images.contains(aImage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfImages()) { index = numberOfImages() - 1; }
      images.remove(aImage);
      images.add(index, aImage);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addImageAt(aImage, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfViewedTickets()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addViewedTicket(MaintenanceTicket aViewedTicket)
  {
    boolean wasAdded = false;
    if (viewedTickets.contains(aViewedTicket)) { return false; }
    viewedTickets.add(aViewedTicket);
    if (aViewedTicket.indexOfViewer(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aViewedTicket.addViewer(this);
      if (!wasAdded)
      {
        viewedTickets.remove(aViewedTicket);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeViewedTicket(MaintenanceTicket aViewedTicket)
  {
    boolean wasRemoved = false;
    if (!viewedTickets.contains(aViewedTicket))
    {
      return wasRemoved;
    }

    int oldIndex = viewedTickets.indexOf(aViewedTicket);
    viewedTickets.remove(oldIndex);
    if (aViewedTicket.indexOfViewer(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aViewedTicket.removeViewer(this);
      if (!wasRemoved)
      {
        viewedTickets.add(oldIndex,aViewedTicket);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addViewedTicketAt(MaintenanceTicket aViewedTicket, int index)
  {  
    boolean wasAdded = false;
    if(addViewedTicket(aViewedTicket))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfViewedTickets()) { index = numberOfViewedTickets() - 1; }
      viewedTickets.remove(aViewedTicket);
      viewedTickets.add(index, aViewedTicket);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveViewedTicketAt(MaintenanceTicket aViewedTicket, int index)
  {
    boolean wasAdded = false;
    if(viewedTickets.contains(aViewedTicket))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfViewedTickets()) { index = numberOfViewedTickets() - 1; }
      viewedTickets.remove(aViewedTicket);
      viewedTickets.add(index, aViewedTicket);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addViewedTicketAt(aViewedTicket, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAssetPlus(AssetPlus aAssetPlus)
  {
    boolean wasSet = false;
    if (aAssetPlus == null)
    {
      return wasSet;
    }

    AssetPlus existingAssetPlus = assetPlus;
    assetPlus = aAssetPlus;
    if (existingAssetPlus != null && !existingAssetPlus.equals(aAssetPlus))
    {
      existingAssetPlus.removeUser(this);
    }
    assetPlus.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=createdTickets.size(); i > 0; i--)
    {
      MaintenanceTicket aCreatedTicket = createdTickets.get(i - 1);
      aCreatedTicket.delete();
    }
    for(int i=images.size(); i > 0; i--)
    {
      ImageLink aImage = images.get(i - 1);
      aImage.delete();
    }
    ArrayList<MaintenanceTicket> copyOfViewedTickets = new ArrayList<MaintenanceTicket>(viewedTickets);
    viewedTickets.clear();
    for(MaintenanceTicket aViewedTicket : copyOfViewedTickets)
    {
      aViewedTicket.removeViewer(this);
    }
    AssetPlus placeholderAssetPlus = assetPlus;
    this.assetPlus = null;
    if(placeholderAssetPlus != null)
    {
      placeholderAssetPlus.removeUser(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "," +
            "name" + ":" + getName()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "assetPlus = "+(getAssetPlus()!=null?Integer.toHexString(System.identityHashCode(getAssetPlus())):"null");
  }
}