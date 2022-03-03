package ui.components.models;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DataProvider;

import static support.web.WebElementHelper.*;
import static ui.components.locators.Locators.CategoryPage.*;

public class CategoryModel extends MainModel {

    private static final Logger logger = LoggerFactory.getLogger(CategoryModel.class);

    public CategoryModel(String languagePrefix) {
        super(languagePrefix);
        waitForPage();
    }

    private CategoryModel waitForPage() {
        waitForVisibility(LBL_FILTERS_DIV.get());
        return this;
    }

    @Step("Navigate to the sub-category 'All Ads'")
    public CategoryModel goToTheSubCategoryAllCars() {
        click(LNK_SUBCATEGORY_ALL_ADS.get());
        return this;
    }

    @Step("Add first listing to favorites")
    public CategoryModel addFirstAdToTheFavourites(DataProvider data){
       return addAdsToTheFavourites(data, 1);
    }

    @Step("Add first, second and third listing to favorites")
    public CategoryModel addAdsToTheFavourites(DataProvider data, int numberOfAds){
        for (int i=1; i<=numberOfAds; i++) {
            mouseOver(LNK_TMP_ADS_FROM_THE_LIST.get(String.valueOf(i)));
            jsClick(LNK_TMP_ADS_FROM_THE_LIST_ADD_TO_FAV.get(String.valueOf(i)));
            data.setData("common", String.valueOf(i)+"_ad", getAttribute(LNK_TMP_ADS_FROM_THE_LIST.get(String.valueOf(i)), "href"));
        }
        return this;
    }

    @Step("Navigate to the first ad")
    public AdvertisementModel goToTheFirstAd(){
        jsClick(LNK_TMP_ADS_FROM_THE_LIST.get("1"));
        return new AdvertisementModel(languagePrefix);
    }

    @Step("Navigate the the Favourites page")
    public FavouritesModel goToTheFavouritesModel(){
        jsClick(LNK_FAVOURITES.get());
        return new FavouritesModel(languagePrefix);
    }

}
