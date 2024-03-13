package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


// which test runner you want to run - depends on assertion
//USING TESTNg to only run feature files
//to run feature file, you need to depend on TestNG / junit 
//cucumber canmot run on its own - it can provide what testng provides
//output will come in encoded format - monochrome code which will be in readable format
@CucumberOptions(features="src/test/java/cucumber",glue="rahulshettyacademy.StepDefinitions", monochrome=true, tags = "@ErrorValidation" ,plugin= {"html:target/cucumber.html"})
public class TestNG_TestRunner extends AbstractTestNGCucumberTests{ //this class will get capabilities that parent class offers- parent class has lot of wrappers

}
