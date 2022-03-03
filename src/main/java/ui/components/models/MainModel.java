package ui.components.models;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static support.web.WebElementHelper.*;
import static support.web.WebElementHelper.jsClick;
import static ui.components.locators.Locators.MainPage.*;

public class MainModel {

    private static final Logger logger = LoggerFactory.getLogger(MainModel.class);
    protected String languagePrefix = "";

    public MainModel(String languagePrefix) {
        this.languagePrefix = languagePrefix;
    }

    @Step("Navigate to the category 'Cars'")
    public CategoryModel goToTheCategoryCars() {
        click(LNK_CATEGORY_CARS.get());
        return new CategoryModel(languagePrefix);
    }

    @Step("Enter search value and navigate to the result page")
    public CategoryModel enterSearchValueAndNavigateToTheResultPage(String searchValue) {
        sendKeys(TXT_SEARCH.get(), searchValue);
        click(BTN_SEARCH.get());
        return new CategoryModel(languagePrefix);
    }

}
