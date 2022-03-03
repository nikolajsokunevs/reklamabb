package ui.components.locators;

import org.openqa.selenium.By;

import java.util.function.Function;

import static java.lang.String.format;

public interface Locators {

    enum MainPage implements Locators {

        LNK_CATEGORY_CARS(By::cssSelector, "ul.categories li:nth-child(2) span a"),
        TXT_SEARCH(By::cssSelector, "#search_value"),
        BTN_SEARCH(By::cssSelector, "input.submit"),
        ;

        private String id;
        private Function<String, By> function;

        MainPage(Function<String, By> function, String id) {
            this.function = function;
            this.id = id;
        }

        public By get() {
            return function.apply(id);
        }
    }

    enum CategoryPage implements  Locators {
        LBL_FILTERS_DIV(By::cssSelector, "#filters"),
        LNK_SUBCATEGORY_ALL_ADS(By::cssSelector, "li.checkAll a"),
        LNK_TMP_ADS_FROM_THE_LIST(By::xpath, "//table/tbody/tr[%s]/td[1]/a"),
        LNK_TMP_ADS_FROM_THE_LIST_ADD_TO_FAV(By::xpath, "//tbody/tr[%s]/td/div/div/a[@class='fav-add']"),
        LNK_FAVOURITES(By::cssSelector, "#favorites-link"),
        LBL_BANNER(By::cssSelector, "body>ins.adsbygoogle"),
        ;

        private String id;
        private Function<String, By> function;

        CategoryPage(Function<String, By> function, String id) {
            this.function = function;
            this.id = id;
        }

        public By get() {
            return function.apply(id);
        }

        public By get(String value) {
            return function.apply(format(id, value));
        }
    }

    enum AdvertisementPage implements Locators {

        BTN_BACK_TO_LIST(By::cssSelector, "#adNav li.backToList"),
        LNK_ADD_TO_FAV(By::cssSelector, "#favs-link"),
        LNK_FAVOURITES(By::cssSelector, "#favorites-link"),
        ;

        private String id;
        private Function<String, By> function;

        AdvertisementPage(Function<String, By> function, String id) {
            this.function = function;
            this.id = id;
        }

        public By get() {
            return function.apply(id);
        }
    }

    enum FavoritesPage implements Locators {

        LBL_YOUR_FAVOURITES(By::cssSelector, "td.left_column div.four a"),
        LNK_TMP_ADS_FROM_THE_LIST(By::xpath, "//table/tbody/tr[%s]/td[1]/a"),
        ;

        private String id;
        private Function<String, By> function;

        FavoritesPage(Function<String, By> function, String id) {
            this.function = function;
            this.id = id;
        }

        public By get() {
            return function.apply(id);
        }

        public By get(String value) {
            return function.apply(format(id, value));
        }
    }
}
