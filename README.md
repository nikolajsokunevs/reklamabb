Selenium / Allure / JUnit5 / Selenoid  [![CircleCI](https://circleci.com/gh/nokunev/craigslist-ui-tests.svg?style=shield)](https://app.circleci.com/pipelines/github/nokunev/craigslist-ui-tests)


### Scope:
    1. Test execution on remote machine(VM DigitalOcean+Docker+Selenoid). 
    2. Screenshot creation for failed tests
    3. Video recording
    4. Allure as report(Screenshots/Videos/Logs mounted for each TC)
    5. CircleCi as CI
    6. DataDriver approuch/Test parameterization(Each test can be run in multiple languages)
    7. Parallel test execution
    8. Logging
    9. Cucumber(Cucumber is added only for the separate branch 'Cucumber')
    
### Configuration:

Configuration defined in ApplicationProperties.java

Examples:
You can specify browser by using one of the following switches:

-Dbrowser=FIREFOX
-Dbrowser=CHROME

Also you can specify where to run tests, on lochal machine / HUB:

-Dselenium.remoteDriver=true
-Dselenium.seleniumGridURL=http://207.154.192.171:4444  

### How to run test?
        mvn clean install test -DthreadCount=5 - execute all tests(Selenoid is used by default, )
        mvn allure:serve - generate allure report and start jetty webserver for quick access

