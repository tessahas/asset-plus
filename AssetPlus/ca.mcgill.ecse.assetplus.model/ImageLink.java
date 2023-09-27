/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 71 "assetplus.ump"
// line 178 "assetplus.ump"
public class ImageLink
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ImageLink Attributes
  private String linkUrl;

  //ImageLink Associations
  private User imageProvider;
  private MaintenanceTicket concernedTicket;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ImageLink(String aLinkUrl, User aImageProvider, MaintenanceTicket aConcernedTicket)
  {
    linkUrl = aLinkUrl;
    boolean didAddImageProvider = setImageProvider(aImageProvider);
    if (!didAddImageProvider)
    {
      throw new RuntimeException("Unable to create image due to imageProvider. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddConcernedTicket = setConcernedTicket(aConcernedTicket);
    if (!didAddConcernedTicket)
    {
      throw new RuntimeException("Unable to create image due to concernedTicket. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLinkUrl(String aLinkUrl)
  {
    boolean wasSet = false;
    linkUrl = aLinkUrl;
    wasSet = true;
    return wasSet;
  }

  public String getLinkUrl()
  {
    return linkUrl;
  }
  /* Code from template association_GetOne */
  public User getImageProvider()
  {
    return imageProvider;
  }
  /* Code from template association_GetOne */
  public MaintenanceTicket getConcernedTicket()
  {
    return concernedTicket;
  }
  /* Code from template association_SetOneToMany */
  public boolean setImageProvider(User aImageProvider)
  {
    boolean wasSet = false;
    if (aImageProvider == null)
    {
      return wasSet;
    }

    User existingImageProvider = imageProvider;
    imageProvider = aImageProvider;
    if (existingImageProvider != null && !existingImageProvider.equals(aImageProvider))
    {
      existingImageProvider.removeImage(this);
    }
    imageProvider.addImage(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setConcernedTicket(MaintenanceTicket aConcernedTicket)
  {
    boolean wasSet = false;
    if (aConcernedTicket == null)
    {
      return wasSet;
    }

    MaintenanceTicket existingConcernedTicket = concernedTicket;
    concernedTicket = aConcernedTicket;
    if (existingConcernedTicket != null && !existingConcernedTicket.equals(aConcernedTicket))
    {
      existingConcernedTicket.removeImage(this);
    }
    concernedTicket.addImage(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    User placeholderImageProvider = imageProvider;
    this.imageProvider = null;
    if(placeholderImageProvider != null)
    {
      placeholderImageProvider.removeImage(this);
    }
    MaintenanceTicket placeholderConcernedTicket = concernedTicket;
    this.concernedTicket = null;
    if(placeholderConcernedTicket != null)
    {
      placeholderConcernedTicket.removeImage(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "linkUrl" + ":" + getLinkUrl()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "imageProvider = "+(getImageProvider()!=null?Integer.toHexString(System.identityHashCode(getImageProvider())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "concernedTicket = "+(getConcernedTicket()!=null?Integer.toHexString(System.identityHashCode(getConcernedTicket())):"null");
  }
}