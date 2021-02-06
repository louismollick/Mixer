package com.ecse428.project.unit.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IModifierServiceUnitTest {
  // TODO: basically the same thing with .getModifiers from IModifierService & IUserServices
  //
  // Example from: https://www.baeldung.com/spring-boot-testing
  //
  // @Before
  // public void setUp() {
  //   Employee alex = new Employee("alex");

  //   Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
  // }

  // @Test
  // public void whenValidName_thenEmployeeShouldBeFound() {
  //   String name = "alex";
  //   Employee found = employeeService.getEmployeeByName(name);

  //   assertThat(found.getName()).isEqualTo(name);
  // }

  @Test
  public void sanityCheck(){
    assertTrue(true);
  }
}
