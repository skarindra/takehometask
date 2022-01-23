# N26 Challenge

The framework :
- Appium (I use v1.22.2)
- Java 8
- Cucumber (feature files are located inside src/test/resources/features/)
- JUnit

Pre-requisite :
- Node & NPM
- Appium
- JDK 8
- Genymotion / Android Device

How to run :
- First, change DEVICE_NAME and UDID inside TestInstruments.java - setCapabilities() - Line 45 and 46 to your preferred device (I'm using Genymotion to run this)
- Then run this command : ./gradlew clean test

Report :
inside target/cucumber-html-reports/overview-features.html

Logs :
- target/automation.log : internal automation log (can be set by calling Logger from page steps class)
- appium.log : detailed appium log
