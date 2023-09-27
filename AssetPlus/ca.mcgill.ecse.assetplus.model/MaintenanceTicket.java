/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 29 "assetplus.ump"
// line 121 "assetplus.ump"
public class MaintenanceTicket
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum PriorityLevel { Urgent, Normal, Low }
  public enum TimeEstimate { LessThanOneDay, OneToThreeDays, ThreeToSevenDays, OneToThreeWeeks, MoreThanThreeWeeks }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextTicketNumber = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MaintenanceTicket Attributes
  private String description;
  private String date;
  private boolean isOpen;
  private boolean isResolved;
  private boolean requiresManagerApproval;
  private PriorityLevel priorityLevel;
  private TimeEstimate timeEstimate;

  //Autounique Attributes
  private int ticketNumber;

  //MaintenanceTicket Associations
  private List<MaintenanceNote> notes;
  private List<ImageLink> images;
  private List<User> viewers;
  private User ticketCreator;
  private Asset asset;
  private Manager ticketSupervisor;
  private AssetPlus assetPlus;
  private HotelEmployee taskAssignee;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MaintenanceTicket(String aDescription, String aDate, boolean aIsOpen, boolean aIsResolved, boolean aRequiresManagerApproval, PriorityLevel aPriorityLevel, TimeEstimate aTimeEstimate, User aTicketCreator, AssetPlus aAssetPlus, HotelEmployee aTaskAssignee)
  {
    description = aDescription;
    date = aDate;
    isOpen = aIsOpen;
    isResolved = aIsResolved;
    requiresManagerApproval = aRequiresManagerApproval;
    priorityLevel = aPriorityLevel;
    timeEstimate = aTimeEstimate;
    ticketNumber = nextTicketNumber++;
    notes = new ArrayList<MaintenanceNote>();
    images = new ArrayList<ImageLink>();
    viewers = new ArrayList<User>();
    boolean didAddTicketCreator = setTicketCreator(aTicketCreator);
    if (!didAddTicketCreator)
    {
      throw new RuntimeException("Unable to create createdTicket due to ticketCreator. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAssetPlus = setAssetPlus(aAssetPlus);
    if (!didAddAssetPlus)
    {
      throw new RuntimeException("Unable to create ticket due to assetPlus. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddTaskAssignee = setTaskAssignee(aTaskAssignee);
    if (!didAddTaskAssignee)
    {
      throw new RuntimeException("Unable to create assignedTask due to taskAssignee. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(String aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsOpen(boolean aIsOpen)
  {
    boolean wasSet = false;
    isOpen = aIsOpen;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsResolved(boolean aIsResolved)
  {
    boolean wasSet = false;
    isResolved = aIsResolved;
    wasSet = true;
    return wasSet;
  }

  public boolean setRequiresManagerApproval(boolean aRequiresManagerApproval)
  {
    boolean wasSet = false;
    requiresManagerApproval = aRequiresManagerApproval;
    wasSet = true;
    return wasSet;
  }

  public boolean setPriorityLevel(PriorityLevel aPriorityLevel)
  {
    boolean wasSet = false;
    priorityLevel = aPriorityLevel;
    wasSet = true;
    return wasSet;
  }

  public boolean setTimeEstimate(TimeEstimate aTimeEstimate)
  {
    boolean wasSet = false;
    timeEstimate = aTimeEstimate;
    wasSet = true;
    return wasSet;
  }

  public String getDescription()
  {
    return description;
  }

  public String getDate()
  {
    return date;
  }

  public boolean getIsOpen()
  {
    return isOpen;
  }

  public boolean getIsResolved()
  {
    return isResolved;
  }

  public boolean getRequiresManagerApproval()
  {
    return requiresManagerApproval;
  }

  public PriorityLevel getPriorityLevel()
  {
    return priorityLevel;
  }

  public TimeEstimate getTimeEstimate()
  {
    return timeEstimate;
  }

  public int getTicketNumber()
  {
    return ticketNumber;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsOpen()
  {
    return isOpen;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsResolved()
  {
    return isResolved;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isRequiresManagerApproval()
  {
    return requiresManagerApproval;
  }
  /* Code from template association_GetMany */
  public MaintenanceNote getNote(int index)
  {
    MaintenanceNote aNote = notes.get(index);
    return aNote;
  }

  public List<MaintenanceNote> getNotes()
  {
    List<MaintenanceNote> newNotes = Collections.unmodifiableList(notes);
    return newNotes;
  }

  public int numberOfNotes()
  {
    int number = notes.size();
    return number;
  }

  public boolean hasNotes()
  {
    boolean has = notes.size() > 0;
    return has;
  }

  public int indexOfNote(MaintenanceNote aNote)
  {
    int index = notes.indexOf(aNote);
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
  public User getViewer(int index)
  {
    User aViewer = viewers.get(index);
    return aViewer;
  }

  public List<User> getViewers()
  {
    List<User> newViewers = Collections.unmodifiableList(viewers);
    return newViewers;
  }

  public int numberOfViewers()
  {
    int number = viewers.size();
    return number;
  }

  public boolean hasViewers()
  {
    boolean has = viewers.size() > 0;
    return has;
  }

  public int indexOfViewer(User aViewer)
  {
    int index = viewers.indexOf(aViewer);
    return index;
  }
  /* Code from template association_GetOne */
  public User getTicketCreator()
  {
    return ticketCreator;
  }
  /* Code from template association_GetOne */
  public Asset getAsset()
  {
    return asset;
  }

  public boolean hasAsset()
  {
    boolean has = asset != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Manager getTicketSupervisor()
  {
    return ticketSupervisor;
  }

  public boolean hasTicketSupervisor()
  {
    boolean has = ticketSupervisor != null;
    return has;
  }
  /* Code from template association_GetOne */
  public AssetPlus getAssetPlus()
  {
    return assetPlus;
  }
  /* Code from template association_GetOne */
  public HotelEmployee getTaskAssignee()
  {
    return taskAssignee;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfNotes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MaintenanceNote addNote(Date aDate, String aDescription, HotelEmployee aNoteCreator)
  {
    return new MaintenanceNote(aDate, aDescription, this, aNoteCreator);
  }

  public boolean addNote(MaintenanceNote aNote)
  {
    boolean wasAdded = false;
    if (notes.contains(aNote)) { return false; }
    MaintenanceTicket existingTicket = aNote.getTicket();
    boolean isNewTicket = existingTicket != null && !this.equals(existingTicket);
    if (isNewTicket)
    {
      aNote.setTicket(this);
    }
    else
    {
      notes.add(aNote);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeNote(MaintenanceNote aNote)
  {
    boolean wasRemoved = false;
    //Unable to remove aNote, as it must always have a ticket
    if (!this.equals(aNote.getTicket()))
    {
      notes.remove(aNote);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addNoteAt(MaintenanceNote aNote, int index)
  {  
    boolean wasAdded = false;
    if(addNote(aNote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNotes()) { index = numberOfNotes() - 1; }
      notes.remove(aNote);
      notes.add(index, aNote);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveNoteAt(MaintenanceNote aNote, int index)
  {
    boolean wasAdded = false;
    if(notes.contains(aNote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNotes()) { index = numberOfNotes() - 1; }
      notes.remove(aNote);
      notes.add(index, aNote);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addNoteAt(aNote, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfImages()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ImageLink addImage(String aLinkUrl, User aImageProvider)
  {
    return new ImageLink(aLinkUrl, aImageProvider, this);
  }

  public boolean addImage(ImageLink aImage)
  {
    boolean wasAdded = false;
    if (images.contains(aImage)) { return false; }
    MaintenanceTicket existingConcernedTicket = aImage.getConcernedTicket();
    boolean isNewConcernedTicket = existingConcernedTicket != null && !this.equals(existingConcernedTicket);
    if (isNewConcernedTicket)
    {
      aImage.setConcernedTicket(this);
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
    //Unable to remove aImage, as it must always have a concernedTicket
    if (!this.equals(aImage.getConcernedTicket()))
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
  public static int minimumNumberOfViewers()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addViewer(User aViewer)
  {
    boolean wasAdded = false;
    if (viewers.contains(aViewer)) { return false; }
    viewers.add(aViewer);
    if (aViewer.indexOfViewedTicket(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aViewer.addViewedTicket(this);
      if (!wasAdded)
      {
        viewers.remove(aViewer);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeViewer(User aViewer)
  {
    boolean wasRemoved = false;
    if (!viewers.contains(aViewer))
    {
      return wasRemoved;
    }

    int oldIndex = viewers.indexOf(aViewer);
    viewers.remove(oldIndex);
    if (aViewer.indexOfViewedTicket(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aViewer.removeViewedTicket(this);
      if (!wasRemoved)
      {
        viewers.add(oldIndex,aViewer);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addViewerAt(User aViewer, int index)
  {  
    boolean wasAdded = false;
    if(addViewer(aViewer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfViewers()) { index = numberOfViewers() - 1; }
      viewers.remove(aViewer);
      viewers.add(index, aViewer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveViewerAt(User aViewer, int index)
  {
    boolean wasAdded = false;
    if(viewers.contains(aViewer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfViewers()) { index = numberOfViewers() - 1; }
      viewers.remove(aViewer);
      viewers.add(index, aViewer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addViewerAt(aViewer, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setTicketCreator(User aTicketCreator)
  {
    boolean wasSet = false;
    if (aTicketCreator == null)
    {
      return wasSet;
    }

    User existingTicketCreator = ticketCreator;
    ticketCreator = aTicketCreator;
    if (existingTicketCreator != null && !existingTicketCreator.equals(aTicketCreator))
    {
      existingTicketCreator.removeCreatedTicket(this);
    }
    ticketCreator.addCreatedTicket(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setAsset(Asset aAsset)
  {
    boolean wasSet = false;
    Asset existingAsset = asset;
    asset = aAsset;
    if (existingAsset != null && !existingAsset.equals(aAsset))
    {
      existingAsset.removeTicket(this);
    }
    if (aAsset != null)
    {
      aAsset.addTicket(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setTicketSupervisor(Manager aTicketSupervisor)
  {
    boolean wasSet = false;
    Manager existingTicketSupervisor = ticketSupervisor;
    ticketSupervisor = aTicketSupervisor;
    if (existingTicketSupervisor != null && !existingTicketSupervisor.equals(aTicketSupervisor))
    {
      existingTicketSupervisor.removeExistingTicket(this);
    }
    if (aTicketSupervisor != null)
    {
      aTicketSupervisor.addExistingTicket(this);
    }
    wasSet = true;
    return wasSet;
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
      existingAssetPlus.removeTicket(this);
    }
    assetPlus.addTicket(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setTaskAssignee(HotelEmployee aTaskAssignee)
  {
    boolean wasSet = false;
    if (aTaskAssignee == null)
    {
      return wasSet;
    }

    HotelEmployee existingTaskAssignee = taskAssignee;
    taskAssignee = aTaskAssignee;
    if (existingTaskAssignee != null && !existingTaskAssignee.equals(aTaskAssignee))
    {
      existingTaskAssignee.removeAssignedTask(this);
    }
    taskAssignee.addAssignedTask(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (notes.size() > 0)
    {
      MaintenanceNote aNote = notes.get(notes.size() - 1);
      aNote.delete();
      notes.remove(aNote);
    }
    
    for(int i=images.size(); i > 0; i--)
    {
      ImageLink aImage = images.get(i - 1);
      aImage.delete();
    }
    ArrayList<User> copyOfViewers = new ArrayList<User>(viewers);
    viewers.clear();
    for(User aViewer : copyOfViewers)
    {
      aViewer.removeViewedTicket(this);
    }
    User placeholderTicketCreator = ticketCreator;
    this.ticketCreator = null;
    if(placeholderTicketCreator != null)
    {
      placeholderTicketCreator.removeCreatedTicket(this);
    }
    if (asset != null)
    {
      Asset placeholderAsset = asset;
      this.asset = null;
      placeholderAsset.removeTicket(this);
    }
    if (ticketSupervisor != null)
    {
      Manager placeholderTicketSupervisor = ticketSupervisor;
      this.ticketSupervisor = null;
      placeholderTicketSupervisor.removeExistingTicket(this);
    }
    AssetPlus placeholderAssetPlus = assetPlus;
    this.assetPlus = null;
    if(placeholderAssetPlus != null)
    {
      placeholderAssetPlus.removeTicket(this);
    }
    HotelEmployee placeholderTaskAssignee = taskAssignee;
    this.taskAssignee = null;
    if(placeholderTaskAssignee != null)
    {
      placeholderTaskAssignee.removeAssignedTask(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "ticketNumber" + ":" + getTicketNumber()+ "," +
            "description" + ":" + getDescription()+ "," +
            "date" + ":" + getDate()+ "," +
            "isOpen" + ":" + getIsOpen()+ "," +
            "isResolved" + ":" + getIsResolved()+ "," +
            "requiresManagerApproval" + ":" + getRequiresManagerApproval()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "priorityLevel" + "=" + (getPriorityLevel() != null ? !getPriorityLevel().equals(this)  ? getPriorityLevel().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "timeEstimate" + "=" + (getTimeEstimate() != null ? !getTimeEstimate().equals(this)  ? getTimeEstimate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "ticketCreator = "+(getTicketCreator()!=null?Integer.toHexString(System.identityHashCode(getTicketCreator())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "asset = "+(getAsset()!=null?Integer.toHexString(System.identityHashCode(getAsset())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "ticketSupervisor = "+(getTicketSupervisor()!=null?Integer.toHexString(System.identityHashCode(getTicketSupervisor())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assetPlus = "+(getAssetPlus()!=null?Integer.toHexString(System.identityHashCode(getAssetPlus())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "taskAssignee = "+(getTaskAssignee()!=null?Integer.toHexString(System.identityHashCode(getTaskAssignee())):"null");
  }
}