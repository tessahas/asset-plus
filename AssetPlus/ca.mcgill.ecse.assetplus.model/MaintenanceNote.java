/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;

// line 59 "assetplus.ump"
// line 163 "assetplus.ump"
public class MaintenanceNote
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextNoteNumber = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MaintenanceNote Attributes
  private Date date;
  private String description;

  //Autounique Attributes
  private int noteNumber;

  //MaintenanceNote Associations
  private MaintenanceTicket ticket;
  private HotelEmployee noteCreator;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MaintenanceNote(Date aDate, String aDescription, MaintenanceTicket aTicket, HotelEmployee aNoteCreator)
  {
    date = aDate;
    description = aDescription;
    noteNumber = nextNoteNumber++;
    boolean didAddTicket = setTicket(aTicket);
    if (!didAddTicket)
    {
      throw new RuntimeException("Unable to create note due to ticket. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddNoteCreator = setNoteCreator(aNoteCreator);
    if (!didAddNoteCreator)
    {
      throw new RuntimeException("Unable to create note due to noteCreator. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public String getDescription()
  {
    return description;
  }

  public int getNoteNumber()
  {
    return noteNumber;
  }
  /* Code from template association_GetOne */
  public MaintenanceTicket getTicket()
  {
    return ticket;
  }
  /* Code from template association_GetOne */
  public HotelEmployee getNoteCreator()
  {
    return noteCreator;
  }
  /* Code from template association_SetOneToMany */
  public boolean setTicket(MaintenanceTicket aTicket)
  {
    boolean wasSet = false;
    if (aTicket == null)
    {
      return wasSet;
    }

    MaintenanceTicket existingTicket = ticket;
    ticket = aTicket;
    if (existingTicket != null && !existingTicket.equals(aTicket))
    {
      existingTicket.removeNote(this);
    }
    ticket.addNote(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setNoteCreator(HotelEmployee aNoteCreator)
  {
    boolean wasSet = false;
    if (aNoteCreator == null)
    {
      return wasSet;
    }

    HotelEmployee existingNoteCreator = noteCreator;
    noteCreator = aNoteCreator;
    if (existingNoteCreator != null && !existingNoteCreator.equals(aNoteCreator))
    {
      existingNoteCreator.removeNote(this);
    }
    noteCreator.addNote(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    MaintenanceTicket placeholderTicket = ticket;
    this.ticket = null;
    if(placeholderTicket != null)
    {
      placeholderTicket.removeNote(this);
    }
    HotelEmployee placeholderNoteCreator = noteCreator;
    this.noteCreator = null;
    if(placeholderNoteCreator != null)
    {
      placeholderNoteCreator.removeNote(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "noteNumber" + ":" + getNoteNumber()+ "," +
            "description" + ":" + getDescription()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "ticket = "+(getTicket()!=null?Integer.toHexString(System.identityHashCode(getTicket())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "noteCreator = "+(getNoteCreator()!=null?Integer.toHexString(System.identityHashCode(getNoteCreator())):"null");
  }
}