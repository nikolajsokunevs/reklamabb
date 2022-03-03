package smoke;

import config.annotations.Dataset;
import context.TestContext;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Feature("Smoke")
public class SmokeTests extends TestContext {

    @ParameterizedTest(name = "Verify the user can add the item to favorites, language: {0}")
    @ValueSource(strings = {"lv", "ru"})
    @Story("Login")
    @Dataset("Dataset")
    void verifyThatUserCanAddItemToFavourites(String language) {
        open(language)
                .goToTheCategoryCars()
                .goToTheSubCategoryAllCars()
                .addFirstAdToTheFavourites(data)
                .goToTheFavouritesModel()
                .verifyTotalNumberOfRecords(data, 1);
    }

    @ParameterizedTest(name = "Verify the user can add few items to favorites, language: {0}")
    @ValueSource(strings = {"lv", "ru"})
    @Story("Login")
    @Dataset("Dataset")
    void verifyThatUserCanAddFewItemsToFavourites(String language) {
        open(language)
                .goToTheCategoryCars()
                .goToTheSubCategoryAllCars()
                .addAdsToTheFavourites(data, 3)
                .goToTheFavouritesModel()
                .verifyTotalNumberOfRecords(data, 3);
    }

    @ParameterizedTest(name = "Verify the user can add selected item to favorites, language: {0}")
    @ValueSource(strings = {"lv", "ru"})
    @Story("Login")
    @Dataset("Dataset")
    void verifyThatUserCanAddSelectedItemToFavourites(String language) {
        open(language)
                .goToTheCategoryCars()
                .goToTheSubCategoryAllCars()
                .goToTheFirstAd()
                .addItemToTheFavourites(data)
                .goToTheFavouritesMidel()
                .verifyTotalNumberOfRecords(data, 1);
    }

    @ParameterizedTest(name = "Verify the user can add item to favorites from the search screen, language: {0}")
    @ValueSource(strings = {"lv", "ru"})
    @Story("Login")
    @Dataset("Dataset")
    void verifyThatUserCanAddItemToFavouritesFromTheSearchScreen(String language) {
        open(language)
                .enterSearchValueAndNavigateToTheResultPage(data.getData(language, "car"))
                .addFirstAdToTheFavourites(data)
                .goToTheFavouritesModel()
                .verifyTotalNumberOfRecords(data, 1);
    }
}
