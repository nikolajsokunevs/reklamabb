package ui.components.models;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DataProvider;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static support.web.WebElementHelper.*;
import static ui.components.locators.Locators.FavoritesPage.*;

public class FavouritesModel extends MainModel {

    private static final Logger logger = LoggerFactory.getLogger(FavouritesModel.class);

    public FavouritesModel(String languagePrefix) {
        super(languagePrefix);
        waitForPage();
    }

    private void waitForPage() {
        waitForVisibility(LBL_YOUR_FAVOURITES.get());
    }

    @Step("Verify the total number of records")
    public FavouritesModel verifyTotalNumberOfRecords(DataProvider data, int expectedNumber) {
        assertThat(getText(LBL_YOUR_FAVOURITES.get())).isEqualTo(data.getData(languagePrefix, "favouritesText") + String.valueOf(expectedNumber));
        List<String> actualValues=new ArrayList<>();
        for (int i = expectedNumber; i > 0; i--) {
            actualValues.add(data.getData(String.valueOf(i) + "_ad"));
        }
        for (int i=0;i<expectedNumber;i++){
            String currentAd=getAttribute(LNK_TMP_ADS_FROM_THE_LIST.get(String.valueOf(expectedNumber)), "href");
            assertThat(actualValues.contains(currentAd)).withFailMessage("The ad '"+currentAd+"' is not found").isEqualTo(true);
        }
        return this;
    }
}
