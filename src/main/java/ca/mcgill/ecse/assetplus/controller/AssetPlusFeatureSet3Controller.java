package ca.mcgill.ecse.assetplus.controller;
import java.sql.Date;
import ca.mcgill.ecse.assetplus.model.*;
import ca.mcgill.ecse.assetplus.persistence.AssetPlusPersistence;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;

/**
  * @author Luis Felipe Jarquin Romero
  * @version 1.0
  * @since 2023-10-18
  */

public class AssetPlusFeatureSet3Controller {

  private static AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

  /** 
   * @author Luis Felipe Jarquin Romero
   * This method is used to add an asset to assetPlus
   * @param assetNumber the asset number of the specific asset. cannot be less than 1.
   * @param floorNumber the floor number of the specific asset. cannot be less than 0.
   * @param roomNumber the room number of the specific asset. cannot be less than -1.
   * @param purchaseDate the purchase date of the specific asset.
   * @param assetTypeName the asset type name.
   * @return Returns an empty string if the addSpecificAsset was successful and no errors
   * were raised. Returns a string with an error message otherwise.
   */

  public static String addSpecificAsset(int assetNumber, int floorNumber, int roomNumber,
      Date purchaseDate, String assetTypeName) {
        String ErrorMessage="";

        if(SpecificAsset.hasWithAssetNumber(assetNumber)){
          ErrorMessage+="The asset already exists";
        }
        if(!AssetType.hasWithName(assetTypeName)){
          ErrorMessage+="The asset type does not exist";
        }
        if(assetNumber < 1){
          ErrorMessage+="The asset number shall not be less than 1";
        }
        if(floorNumber < 0){
          ErrorMessage+="The floor number shall not be less than 0";
        }
        if(roomNumber < -1){
          ErrorMessage+="The room number shall not be less than -1";
        }
        if(!ErrorMessage.equalsIgnoreCase("")){
          return ErrorMessage;
        }
        
        try{
          assetPlus.addSpecificAsset(assetNumber, floorNumber, roomNumber, purchaseDate, AssetType.getWithName(assetTypeName));
          AssetPlusPersistence.save();
        }
        catch(Exception e){
          return ErrorMessage;
        }
        

        return ErrorMessage;
  }



  /**
   * @author Luis Felipe Jarquin Romero
   * This method is used to update an asset in assetPlus.
   * @param assetNumber the asset number of the specific asset. cannot be less than 1.
   * @param newFloorNumber the new floor number of the specific asset. cannot be less than 0.
   * @param newRoomNumber the new room number of the specific asset. cannot be less than -1.
   * @param newPurchaseDate the new purchase date of the specific asset.
   * @param newAssetTypeName the new asset type name.
   * @return Returns an empty string if the updateSpecificAsset was successful and no errors
   * were raised. Returns a string with an error message otherwise.
   */

  public static String updateSpecificAsset(int assetNumber, int newFloorNumber, int newRoomNumber,
      Date newPurchaseDate, String newAssetTypeName)
  {
        String ErrorMessage="";
        
        if(!SpecificAsset.hasWithAssetNumber(assetNumber)){
          ErrorMessage+="The asset does not exist";
        }
        if(!AssetType.hasWithName(newAssetTypeName)){
          ErrorMessage+="The asset type does not exist";
        }
        if(assetNumber < 1){
          ErrorMessage+="The asset number shall not be less than 1";
        }
        if(newFloorNumber < 0){
          ErrorMessage+="The floor number shall not be less than 0";
        }
        if(newRoomNumber < -1){
          ErrorMessage+="The room number shall not be less than -1";
        }
        if(!ErrorMessage.equalsIgnoreCase("")){
          return ErrorMessage;
        }

        try{
          SpecificAsset specificAssetToEdit = SpecificAsset.getWithAssetNumber(assetNumber);
          specificAssetToEdit.setFloorNumber(newFloorNumber);
          specificAssetToEdit.setRoomNumber(newRoomNumber);
          specificAssetToEdit.setPurchaseDate(newPurchaseDate);
          specificAssetToEdit.setAssetType(AssetType.getWithName(newAssetTypeName));
          AssetPlusPersistence.save();
        }
        catch(Exception e){
          return ErrorMessage;
        }
        

        return ErrorMessage;
}



/**
 * @author Luis Felipe Jarquin Romero
 * This method deletes the specific asset.
 * @param assetNumber The asset number of the specific asset to be deleted.
 */


  public static void deleteSpecificAsset(int assetNumber)
  {
    if (SpecificAsset.hasWithAssetNumber(assetNumber))
    {
      try{
        SpecificAsset.getWithAssetNumber(assetNumber).delete();
        AssetPlusPersistence.save();
      } catch (Exception e){}
      
    }
    
  }


  /**
   * This method returns true when a specific asset has an asset with assetNumber
   * @param assetNumber
   * @return boolean that tells you if the asset is in our asset plus.
   * @author Luis Felipe Jarquin Romero
   */
  public static boolean hasAsset(int assetNumber){
    return SpecificAsset.hasWithAssetNumber(assetNumber);
  }

}
