package example;

import org.junit.runner.RunWith;

/**
 * Created by sekarayukarindra.
 */

@RunWith(io.cucumber.junit.Cucumber.class)
@io.cucumber.junit.CucumberOptions(
        features = {
                "classpath:features",
        },
        stepNotifications = true,
        plugin = {
                "pretty",
                "json:target/cucumber-report.json",
        })
public class CucumberTestRunner {
}
