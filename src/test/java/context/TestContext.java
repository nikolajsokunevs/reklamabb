package context;

import config.ApplicationProperties;
import config.annotations.Dataset;
import config.webdriver.DriverBase;
import exception.IncorrectTestDataException;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5AnnotationProcessor;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static config.ApplicationProperties.ApplicationProperty.APP_URL;
import static config.ApplicationProperties.getBoolean;
import static config.ApplicationProperties.getString;
import static support.web.WebElementHelper.navigateToPage;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.components.models.MainModel;
import utils.DataProvider;
import java.io.File;
import java.io.IOException;


@ExtendWith(AllureJunit5AnnotationProcessor.class)
@ExtendWith(TestContext.AfterTestExecution.class)
public class TestContext {

    protected WebDriver driver;
    protected DataProvider data;

    @BeforeAll
    public static void instantiateDriverObject() {

    }

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        driver = DriverBase.getDriver();
        setupTestData(testInfo);
    }

    @AfterAll
    public static void closeDriverObjects() {

    }

    @AfterEach
    public void clearCookies() throws Exception {
        DriverBase.clearCookies();
        DriverBase.closeCurrentDriver();
    }

    @Step("Open browser and navigate to application")
    public MainModel open(String language) {
        switch (language){
            case "ru":  navigateToPage(ApplicationProperties.getString(APP_URL)+"/ru");
                break;
            case "lv":  navigateToPage(ApplicationProperties.getString(APP_URL)+"/lv");
                break;
        }
        MainModel mainModel = new MainModel(language);
        return mainModel;
    }

    private void setupTestData(TestInfo testInfo) {
        boolean isAnnotated=testInfo.getTestMethod().get().isAnnotationPresent(Dataset.class);
        if (isAnnotated){
           String datasetName=testInfo.getTestMethod().get().getAnnotation(Dataset.class).value();
           if (new File("./src/test/resources/" + datasetName + ".json").exists()) {
                data = new DataProvider(datasetName);
           }else {
               throw new IncorrectTestDataException(String.format("Dataset with name '%s.json' does not exist in resources", datasetName));
           }
        }
    }

    public static class AfterTestExecution implements AfterTestExecutionCallback {

        @Override
        public void afterTestExecution(ExtensionContext context) throws Exception {
            Boolean isFailed = context.getExecutionException().isPresent();

            if (isFailed) {
                captureScreen(context.getTestMethod().get().getName() + context.getDisplayName());
            }
            if (new File("log.log").exists())
                attachLog();

            if(getBoolean(ApplicationProperties.ApplicationProperty.ENABLE_VIDEO)&&
                    getBoolean(ApplicationProperties.ApplicationProperty.REMOTE_DRIVER)){
                attachVideoLink();
            }
        }

        public String captureScreen(String testName) {
            String path;
            try {
                WebDriver driver = DriverBase.getDriver();
                byte[] source = makeScreenshotOnFailure(driver);
                path = "./target/screenshots/" + testName + System.currentTimeMillis() + ".png";
                FileUtils.writeByteArrayToFile(new File(path), source);
            } catch (IOException e) {
                path = "Failed to capture screenshot: " + e.getMessage();
            }
            return path;
        }

        @Attachment("Screenshot on failure")
        public byte[] makeScreenshotOnFailure(WebDriver driver) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }

        @Attachment("Log")
        public byte[] attachLog() throws IOException {
            byte[] log = FileUtils.readFileToByteArray(new File("log.log"));
            FileUtils.forceDeleteOnExit(new File("log.log"));
            return log;
        }

        @Attachment("Video")
        public byte[] attachVideoLink() throws IOException {
            String gridURL=getString(ApplicationProperties.ApplicationProperty.SELENIUM_GRID_URL);
            String sessionId=((RemoteWebDriver)DriverBase.getDriver()).getSessionId().toString();
            String videoUrl=gridURL+"/video/"+sessionId+".mp4";
            byte[] video = videoUrl.getBytes();
            return video;
        }
    }

}
