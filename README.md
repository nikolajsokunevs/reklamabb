Selenium / Allure / JUnit5 / Selenoid  [![CircleCI](https://circleci.com/gh/nikolajsokunevs/reklamabb.svg?style=shield)](https://app.circleci.com/pipelines/github/nikolajsokunevs/reklamabb)


### Scope:
    1. Test execution on remote machine(VM DigitalOcean+Docker+Selenoid). 
    2. Screenshot creation for failed tests
    3. Video recording
    4. Allure as report(Screenshots/Videos/Logs mounted for each TC)
    5. CircleCi as CI
    6. DataDriver approuch/Test parameterization(Each test can be run in multiple languages)
    7. Parallel test execution
    8. Logging
    
### Configuration:

Configuration defined in ApplicationProperties.java

Examples:
You can specify browser by using one of the following switches:

-Dbrowser=FIREFOX
-Dbrowser=CHROME

Also you can specify where to run tests, on lochal machine / HUB:

-Dselenium.remoteDriver=true
-Dselenium.seleniumGridURL=http://208.68.36.115:4444  

### How to run test?
        mvn clean install test -DthreadCount=5 - execute all tests(Selenoid is used by default, )
        mvn allure:serve - generate allure report and start jetty webserver for quick access
        
### Examples:
[screenshot1]: https://github.com/nikolajsokunevs/reklamabb/raw/main/images/pipeline.png
[screenshot2]: https://github.com/nikolajsokunevs/reklamabb/raw/main/images/reports.png
[screenshot3]: https://github.com/nikolajsokunevs/reklamabb/raw/main/images/selenoid.png


