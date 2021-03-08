package com.ecse428.project.acceptance.steps.scenarioSteps.loginWithCredentials;

import com.ecse428.project.acceptance.CucumberConfig;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmptyEmailOrPasswordSteps extends CucumberConfig {
  @When("I enter an empty <cred>")
  public void i_enter_an_empty_cred(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
  }

  @Then("the system will return the error <cred>{string}")
  public void the_system_will_return_the_error_cred(String string) {
    // Write code here that turns the phrase above into concrete actions
  }
}
