package ca.mcgill.ecse.assetplus.application;

import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.persistence.AssetPlusPersistence;
import javafx.application.Application;
import ca.mcgill.ecse.assetplus.javafx.fxml.pages.*;

public class AssetPlusApplication {

  private static AssetPlus assetPlus;
  public static final boolean DARK_MODE = true;

  public static void main(String[] args) {
    Application.launch(AssetPlusFxmlView.class, args);
  }

  public static AssetPlus getAssetPlus() {
    if (assetPlus == null) {
      // these attributes are default, you should set them later with the setters
      assetPlus = AssetPlusPersistence.load();
    }
    return assetPlus;
  }

}
