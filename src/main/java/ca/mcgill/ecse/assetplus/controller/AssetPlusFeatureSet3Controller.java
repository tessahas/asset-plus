package ca.mcgill.ecse.assetplus.controller;
import java.sql.Date;
import ca.mcgill.ecse.assetplus.model.*;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;

public class AssetPlusFeatureSet3Controller {

  

  public static String addSpecificAsset(int assetNumber, int floorNumber, int roomNumber,
      Date purchaseDate, String assetTypeName) {
        String ErrorMessage="";

        if(SpecificAsset.hasWithAssetNumber(assetNumber)){
          ErrorMessage+="The asset already exists. ";
        }
        if(!AssetType.hasWithName(assetTypeName)){
          ErrorMessage+="The asset type does not exist. ";
        }
        if(assetNumber < 1){
          ErrorMessage+="The asset number shall not be less than 1. ";
        }
        if(floorNumber < 0){
          ErrorMessage+="The floor number shall not be less than 0. ";
        }
        if(roomNumber < -1){
          ErrorMessage+="The room number shall not be less than -1. ";
        }
        AssetPlusApplication.getAssetPlus().addSpecificAsset(assetNumber, floorNumber, roomNumber, purchaseDate, AssetType.getWithName(assetTypeName));

        return ErrorMessage;
  }




  public static String updateSpecificAsset(int assetNumber, int newFloorNumber, int newRoomNumber,
      Date newPurchaseDate, String newAssetTypeName)
  {
        String ErrorMessage="";
        
        if(!SpecificAsset.hasWithAssetNumber(assetNumber)){
          ErrorMessage+="The asset does not exist. ";
        }
        if(!AssetType.hasWithName(newAssetTypeName)){
          ErrorMessage+="The asset type does not exist. ";
        }
        if(assetNumber < 1){
          ErrorMessage+="The asset number shall not be less than 1. ";
        }
        if(newFloorNumber < 0){
          ErrorMessage+="The floor number shall not be less than 0. ";
        }
        if(newRoomNumber < -1){
          ErrorMessage+="The room number shall not be less than -1. ";
        }
        
        SpecificAsset specificAssetToEdit = SpecificAsset.getWithAssetNumber(assetNumber);
        specificAssetToEdit.setFloorNumber(newFloorNumber);
        specificAssetToEdit.setRoomNumber(newRoomNumber);
        specificAssetToEdit.setPurchaseDate(newPurchaseDate);
        specificAssetToEdit.setAssetType(AssetType.getWithName(newAssetTypeName));

        return ErrorMessage;
}






  public static void deleteSpecificAsset(int assetNumber)
  {
    if (SpecificAsset.hasWithAssetNumber(assetNumber))
    {
      SpecificAsset.getWithAssetNumber(assetNumber).delete();
    }
    
  }

}
