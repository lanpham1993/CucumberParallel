Feature: Searching flight on Chrome

  Scenario: Searching flight on chrome
    Given launch browser 'chrome'
    When user navigates to 'https://booking.com/'
    And user clicks on Tab 'Flight'
    And user inputs '<departure>', '<arrival>', '<departureDate>', '<arrivalDate>', '<quantity>'
    And clicks on search button

    Examples: 
      | departure | arrival | departureDate | arrivalDate | quantity |
      | Bangkok   | oslo | today + 2     | today + 5   |        2 |