package ui.components.models;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DataProvider;

import static support.web.WebElementHelper.*;
import static ui.components.locators.Locators.AdvertisementPage.*;

public class AdvertisementModel extends MainModel{
    private static final Logger logger = LoggerFactory.getLogger(AdvertisementModel.class);

    public AdvertisementModel(String languagePrefix) {
        super(languagePrefix);
        waitForPage();
    }

    private void waitForPage() {
        waitForVisibility(BTN_BACK_TO_LIST.get());
    }

    @Step("Add item to the favourites")
    public AdvertisementModel addItemToTheFavourites(DataProvider data){
        click(LNK_ADD_TO_FAV.get());
        data.setData("common", "1_ad", getUrl());
        return this;
    }

    @Step("Go to the favourites page")
    public FavouritesModel goToTheFavouritesMidel(){
        click(LNK_FAVOURITES.get());
        return new FavouritesModel(languagePrefix);
    }
}
