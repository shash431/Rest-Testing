@run
Feature: Answer Digital Contact Us Page

  Background:
    Given I navigate to the Answer Digital Contact Us page

  Scenario: Send a query for an empty form
    When I send a query for an empty form
    Then the following validation messages are returned:
      | Fullname is required      |
      | Email is required         |
      | Contact is required       |
      | Chosen contact is Invalid |

  Scenario: Send a query for a name and an email only on the form
    When I send a query populating only a name and an email address
    Then the following validation messages are returned:
      | Contact is required       |
      | Chosen contact is Invalid |

  Scenario: Send a query for a name, an email and a message only on the form
    When I send a query populating only a name, an email address and a message
    Then the following validation messages are returned:
      | Contact is required       |
      | Chosen contact is Invalid |