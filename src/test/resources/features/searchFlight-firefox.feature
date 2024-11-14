Feature: Searching flight on firefox

  Scenario Outline: Searching flight on firefox
    Given launch browser 'firefox'
    When user navigates to 'https://booking.com/'
    And user clicks on Tab 'Flight'
    And user inputs '<departure>', '<arrival>', '<departureDate>', '<arrivalDate>', '<quantity>'
    And clicks on search button

    Examples: 
      | departure | arrival | departureDate | arrivalDate | quantity | 
      | Bangkok   | turkey  | today + 2     | today + 5   |        2 | 
