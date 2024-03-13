@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on E-Commerce Page
    When Logged in with password <password> and username <name>
    Then "Incorrect email or password." message is displayed.

    Examples: 
      | password  | name 
      | Iamking@0 | rahulshetty@gmail.com
