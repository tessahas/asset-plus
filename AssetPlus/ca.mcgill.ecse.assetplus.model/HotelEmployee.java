/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 76 "assetplus.ump"
// line 185 "assetplus.ump"
public abstract class HotelEmployee extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HotelEmployee Associations
  private List<MaintenanceNote> notes;
  private List<MaintenanceTicket> assignedTasks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HotelEmployee(String aEmail, String aPassword, AssetPlus aAssetPlus)
  {
    super(aEmail, aPassword, aAssetPlus);
    notes = new ArrayList<MaintenanceNote>();
    assignedTasks = new ArrayList<MaintenanceTicket>();
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  public MaintenanceTicket getAssignedTask(int index)
  {
    MaintenanceTicket aAssignedTask = assignedTasks.get(index);
    return aAssignedTask;
  }

  public List<MaintenanceTicket> getAssignedTasks()
  {
    List<MaintenanceTicket> newAssignedTasks = Collections.unmodifiableList(assignedTasks);
    return newAssignedTasks;
  }

  public int numberOfAssignedTasks()
  {
    int number = assignedTasks.size();
    return number;
  }

  public boolean hasAssignedTasks()
  {
    boolean has = assignedTasks.size() > 0;
    return has;
  }

  public int indexOfAssignedTask(MaintenanceTicket aAssignedTask)
  {
    int index = assignedTasks.indexOf(aAssignedTask);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfNotes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MaintenanceNote addNote(Date aDate, String aDescription, MaintenanceTicket aTicket)
  {
    return new MaintenanceNote(aDate, aDescription, aTicket, this);
  }

  public boolean addNote(MaintenanceNote aNote)
  {
    boolean wasAdded = false;
    if (notes.contains(aNote)) { return false; }
    HotelEmployee existingNoteCreator = aNote.getNoteCreator();
    boolean isNewNoteCreator = existingNoteCreator != null && !this.equals(existingNoteCreator);
    if (isNewNoteCreator)
    {
      aNote.setNoteCreator(this);
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
    //Unable to remove aNote, as it must always have a noteCreator
    if (!this.equals(aNote.getNoteCreator()))
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
  public static int minimumNumberOfAssignedTasks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MaintenanceTicket addAssignedTask(String aDescription, String aDate, boolean aIsOpen, boolean aIsResolved, boolean aRequiresManagerApproval, MaintenanceTicket.PriorityLevel aPriorityLevel, MaintenanceTicket.TimeEstimate aTimeEstimate, User aTicketCreator, AssetPlus aAssetPlus)
  {
    return new MaintenanceTicket(aDescription, aDate, aIsOpen, aIsResolved, aRequiresManagerApproval, aPriorityLevel, aTimeEstimate, aTicketCreator, aAssetPlus, this);
  }

  public boolean addAssignedTask(MaintenanceTicket aAssignedTask)
  {
    boolean wasAdded = false;
    if (assignedTasks.contains(aAssignedTask)) { return false; }
    HotelEmployee existingTaskAssignee = aAssignedTask.getTaskAssignee();
    boolean isNewTaskAssignee = existingTaskAssignee != null && !this.equals(existingTaskAssignee);
    if (isNewTaskAssignee)
    {
      aAssignedTask.setTaskAssignee(this);
    }
    else
    {
      assignedTasks.add(aAssignedTask);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAssignedTask(MaintenanceTicket aAssignedTask)
  {
    boolean wasRemoved = false;
    //Unable to remove aAssignedTask, as it must always have a taskAssignee
    if (!this.equals(aAssignedTask.getTaskAssignee()))
    {
      assignedTasks.remove(aAssignedTask);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssignedTaskAt(MaintenanceTicket aAssignedTask, int index)
  {  
    boolean wasAdded = false;
    if(addAssignedTask(aAssignedTask))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignedTasks()) { index = numberOfAssignedTasks() - 1; }
      assignedTasks.remove(aAssignedTask);
      assignedTasks.add(index, aAssignedTask);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssignedTaskAt(MaintenanceTicket aAssignedTask, int index)
  {
    boolean wasAdded = false;
    if(assignedTasks.contains(aAssignedTask))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignedTasks()) { index = numberOfAssignedTasks() - 1; }
      assignedTasks.remove(aAssignedTask);
      assignedTasks.add(index, aAssignedTask);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssignedTaskAt(aAssignedTask, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=notes.size(); i > 0; i--)
    {
      MaintenanceNote aNote = notes.get(i - 1);
      aNote.delete();
    }
    for(int i=assignedTasks.size(); i > 0; i--)
    {
      MaintenanceTicket aAssignedTask = assignedTasks.get(i - 1);
      aAssignedTask.delete();
    }
    super.delete();
  }

}