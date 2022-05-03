package tqs5.hw1;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("tqs5/hw1")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "tqs5.hw1")
public class RunCucumberTest {

}
