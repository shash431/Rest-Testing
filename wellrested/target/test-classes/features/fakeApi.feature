@run
Feature: Fake API Test

  A feature file testing the various calls of a fake API.
  Fake API: https://jsonplaceholder.typicode.com/

  Scenario: Get the first post
    When I get post 1
    Then the response returns a HTTP 200 status code
    And response includes the following:
      | userId | 1                                                                                                                                                                 |
      | id     | 1                                                                                                                                                                 |
      | title  | sunt aut facere repellat provident occaecati excepturi optio reprehenderit                                                                                        |
      | body   | quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto |

  Scenario: Delete the first post
    When I delete post 1
    Then the response returns a HTTP 200 status code

  Scenario: Get all posts
    When I get all posts
    Then the response returns a HTTP 200 status code
    And the response has 100 entries
    And each entry has the following attributes:
      | userId |
      | id     |
      | title  |
      | body   |

  Scenario: Add a new post
    When I add a post with the following details:
      | userId | 4                                |
      | title  | Title of a new post              |
      | body   | This is the body of the new post |
    Then the response returns a HTTP 201 status code