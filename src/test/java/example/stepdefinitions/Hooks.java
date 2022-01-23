package example.stepdefinitions;

import example.TestInstruments;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by sekarayukarindra.
 */
public class Hooks extends TestInstruments implements En {

    private final static Logger LOGGER = LogManager.getLogger(Hooks.class);

    public Hooks() {

        Before(0, () -> {
            init();
            Runtime.getRuntime().addShutdownHook(new Thread(this::tearDown));
        });

        Before(1, (Scenario scenario) -> {
            LOGGER.info("Running scenario : " + scenario.getName());
            LOGGER.info("----------------Start of Scenario----------------");
        });

        After(0, (Scenario scenario) -> {
            LOGGER.info("Scenario '"+scenario.getName()+"' with status "+scenario.getStatus().toString().toLowerCase());
            LOGGER.info("-----------------End of Scenario-----------------");
        });

        After(1, this::afterScenario);
    }
}
