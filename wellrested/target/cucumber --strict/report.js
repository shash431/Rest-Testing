$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("fakeApi.feature");
formatter.feature({
  "line": 2,
  "name": "Fake API Test",
  "description": "\r\nA feature file testing the various calls of a fake API.\r\nFake API: https://jsonplaceholder.typicode.com/",
  "id": "fake-api-test",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@run"
    }
  ]
});
formatter.scenario({
  "line": 7,
  "name": "Get the first post",
  "description": "",
  "id": "fake-api-test;get-the-first-post",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 8,
  "name": "I get post 1",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "the response returns a HTTP 200 status code",
  "keyword": "Then "
});
formatter.step({
  "line": 10,
  "name": "response includes the following:",
  "rows": [
    {
      "cells": [
        "userId",
        "1"
      ],
      "line": 11
    },
    {
      "cells": [
        "id",
        "1"
      ],
      "line": 12
    },
    {
      "cells": [
        "title",
        "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"
      ],
      "line": 13
    },
    {
      "cells": [
        "body",
        "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
      ],
      "line": 14
    }
  ],
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 11
    }
  ],
  "location": "FakeApiSteps.java:17"
});
formatter.result({
  "duration": 2308585946,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 28
    }
  ],
  "location": "FakeApiSteps.java:29"
});
formatter.result({
  "duration": 81352490,
  "status": "passed"
});
formatter.match({
  "location": "FakeApiSteps.java:37"
});
formatter.result({
  "duration": 57418149,
  "status": "passed"
});
formatter.scenario({
  "line": 16,
  "name": "Delete the first post",
  "description": "",
  "id": "fake-api-test;delete-the-first-post",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 17,
  "name": "I delete post 1",
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "the response returns a HTTP 200 status code",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 14
    }
  ],
  "location": "FakeApiSteps.java:21"
});
formatter.result({
  "duration": 1173765624,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 28
    }
  ],
  "location": "FakeApiSteps.java:29"
});
formatter.result({
  "duration": 1881880,
  "status": "passed"
});
formatter.scenario({
  "line": 20,
  "name": "Get all posts",
  "description": "",
  "id": "fake-api-test;get-all-posts",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 21,
  "name": "I get all posts",
  "keyword": "When "
});
formatter.step({
  "line": 22,
  "name": "the response returns a HTTP 200 status code",
  "keyword": "Then "
});
formatter.step({
  "line": 23,
  "name": "the response has 100 entries",
  "keyword": "And "
});
formatter.step({
  "line": 24,
  "name": "each entry has the following attributes:",
  "rows": [
    {
      "cells": [
        "userId"
      ],
      "line": 25
    },
    {
      "cells": [
        "id"
      ],
      "line": 26
    },
    {
      "cells": [
        "title"
      ],
      "line": 27
    },
    {
      "cells": [
        "body"
      ],
      "line": 28
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "FakeApiSteps.java:25"
});
formatter.result({
  "duration": 185111841,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 28
    }
  ],
  "location": "FakeApiSteps.java:29"
});
formatter.result({
  "duration": 908639,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "100",
      "offset": 17
    }
  ],
  "location": "FakeApiSteps.java:33"
});
formatter.result({
  "duration": 337291699,
  "status": "passed"
});
formatter.match({
  "location": "FakeApiSteps.java:41"
});
formatter.result({
  "duration": 66982708,
  "status": "passed"
});
formatter.scenario({
  "line": 30,
  "name": "Add a new post",
  "description": "",
  "id": "fake-api-test;add-a-new-post",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 31,
  "name": "I add a post with the following details:",
  "rows": [
    {
      "cells": [
        "userId",
        "4"
      ],
      "line": 32
    },
    {
      "cells": [
        "title",
        "Title of a new post"
      ],
      "line": 33
    },
    {
      "cells": [
        "body",
        "This is the body of the new post"
      ],
      "line": 34
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 35,
  "name": "the response returns a HTTP 201 status code",
  "keyword": "Then "
});
formatter.match({
  "location": "FakeApiSteps.java:45"
});
formatter.result({
  "duration": 963130380,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "201",
      "offset": 28
    }
  ],
  "location": "FakeApiSteps.java:29"
});
formatter.result({
  "duration": 1409750,
  "status": "passed"
});
});