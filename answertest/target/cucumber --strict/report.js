$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("answer.digital.contact.us.page.feature");
formatter.feature({
  "line": 2,
  "name": "Answer Digital Contact Us Page",
  "description": "",
  "id": "answer-digital-contact-us-page",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@run"
    }
  ]
});
formatter.background({
  "line": 4,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 5,
  "name": "I navigate to the Answer Digital Contact Us page",
  "keyword": "Given "
});
formatter.match({
  "location": "ContactUsPageSteps.java:21"
});
formatter.result({
  "duration": 6609335388,
  "status": "passed"
});
formatter.scenario({
  "line": 7,
  "name": "Send a query for an empty form",
  "description": "",
  "id": "answer-digital-contact-us-page;send-a-query-for-an-empty-form",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 8,
  "name": "I send a query for an empty form",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "the following validation messages are returned:",
  "rows": [
    {
      "cells": [
        "Fullname is required"
      ],
      "line": 10
    },
    {
      "cells": [
        "Email is required"
      ],
      "line": 11
    },
    {
      "cells": [
        "Contact is required"
      ],
      "line": 12
    },
    {
      "cells": [
        "Chosen contact is Invalid"
      ],
      "line": 13
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "ContactUsPageSteps.java:25"
});
formatter.result({
  "duration": 937299594,
  "status": "passed"
});
formatter.match({
  "location": "ContactUsPageSteps.java:29"
});
formatter.result({
  "duration": 517722108,
  "status": "passed"
});
formatter.background({
  "line": 4,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 5,
  "name": "I navigate to the Answer Digital Contact Us page",
  "keyword": "Given "
});
formatter.match({
  "location": "ContactUsPageSteps.java:21"
});
formatter.result({
  "duration": 1423967938,
  "status": "passed"
});
formatter.scenario({
  "line": 15,
  "name": "Send a query for a name and an email only on the form",
  "description": "",
  "id": "answer-digital-contact-us-page;send-a-query-for-a-name-and-an-email-only-on-the-form",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 16,
  "name": "I send a query populating only a name and an email address",
  "keyword": "When "
});
formatter.step({
  "line": 17,
  "name": "the following validation messages are returned:",
  "rows": [
    {
      "cells": [
        "Contact is required"
      ],
      "line": 18
    },
    {
      "cells": [
        "Chosen contact is Invalid"
      ],
      "line": 19
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "ContactUsPageSteps.java:34"
});
formatter.result({
  "duration": 1039964475,
  "status": "passed"
});
formatter.match({
  "location": "ContactUsPageSteps.java:29"
});
formatter.result({
  "duration": 143801232,
  "status": "passed"
});
formatter.background({
  "line": 4,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 5,
  "name": "I navigate to the Answer Digital Contact Us page",
  "keyword": "Given "
});
formatter.match({
  "location": "ContactUsPageSteps.java:21"
});
formatter.result({
  "duration": 958005725,
  "status": "passed"
});
formatter.scenario({
  "line": 21,
  "name": "Send a query for a name, an email and a message only on the form",
  "description": "",
  "id": "answer-digital-contact-us-page;send-a-query-for-a-name,-an-email-and-a-message-only-on-the-form",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 22,
  "name": "I send a query populating only a name, an email address and a message",
  "keyword": "When "
});
formatter.step({
  "line": 23,
  "name": "the following validation messages are returned:",
  "rows": [
    {
      "cells": [
        "Contact is required"
      ],
      "line": 24
    },
    {
      "cells": [
        "Chosen contact is Invalid"
      ],
      "line": 25
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "ContactUsPageSteps.java:40"
});
formatter.result({
  "duration": 1196195061,
  "status": "passed"
});
formatter.match({
  "location": "ContactUsPageSteps.java:29"
});
formatter.result({
  "duration": 288744151,
  "status": "passed"
});
formatter.uri("answer.digital.who.are.we.page.feature");
formatter.feature({
  "line": 2,
  "name": "Answer Digital Who Are We Page",
  "description": "",
  "id": "answer-digital-who-are-we-page",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@run"
    }
  ]
});
formatter.scenario({
  "line": 4,
  "name": "The Who Are We page contains the values of the company",
  "description": "",
  "id": "answer-digital-who-are-we-page;the-who-are-we-page-contains-the-values-of-the-company",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I navigate to the Answer Digital Who Are We page",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the following company values are displayed:",
  "rows": [
    {
      "cells": [
        "A CATALYST FOR CHANGE"
      ],
      "line": 7
    },
    {
      "cells": [
        "NURTURATION"
      ],
      "line": 8
    },
    {
      "cells": [
        "PUT LOVE IN THE ROOM"
      ],
      "line": 9
    },
    {
      "cells": [
        "DO THE RIGHT THING IN THE RIGHT WAY"
      ],
      "line": 10
    },
    {
      "cells": [
        "NEVER STAND STILL"
      ],
      "line": 11
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "WhoAreWePageSteps.java:17"
});
formatter.result({
  "duration": 2003410571,
  "status": "passed"
});
formatter.match({
  "location": "WhoAreWePageSteps.java:21"
});
formatter.result({
  "duration": 305795193,
  "status": "passed"
});
formatter.scenario({
  "line": 13,
  "name": "Play the Answer Atmosphere video",
  "description": "",
  "id": "answer-digital-who-are-we-page;play-the-answer-atmosphere-video",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 14,
  "name": "I navigate to the Answer Digital Who Are We page",
  "keyword": "Given "
});
formatter.step({
  "line": 15,
  "name": "I play the Answer Atmosphere video",
  "keyword": "When "
});
formatter.step({
  "line": 16,
  "name": "the Answer Atmosphere video plays",
  "keyword": "Then "
});
formatter.match({
  "location": "WhoAreWePageSteps.java:17"
});
formatter.result({
  "duration": 1781917877,
  "status": "passed"
});
formatter.match({
  "location": "WhoAreWePageSteps.java:26"
});
formatter.result({
  "duration": 794534393,
  "status": "passed"
});
formatter.match({
  "location": "WhoAreWePageSteps.java:30"
});
formatter.result({
  "duration": 790856362,
  "status": "passed"
});
});