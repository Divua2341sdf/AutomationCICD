
@tag
Feature: Purchase order from E-Commerce website
  I want to use this template for my feature file

	Background:
	Given I landed on E-Commerce Page.
	

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with password <password> and username <name>
    When I add the product <productName> to cart
    And checkout <productName> and submit the order
    Then "THANK YOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | password    | name                  | productName
      | Iamking@000 | rahulshetty@gmail.com | ZARA COAT 3

