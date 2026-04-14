Feature: Drag and drop functionality

  @smoke
  Scenario: User can drag and drop the object successfully
    Given the user is on the drag and drop page
    When the user drags the object to the target area
    Then the object should be dropped successfully