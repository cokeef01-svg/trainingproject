package stepdefinitions;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DragDropPage;

public class DragDropSteps extends BaseTest {

    // Page object for drag and drop page
    private DragDropPage dragDropPage;

    @Given("the user is on the drag and drop page")
    public void the_user_is_on_the_drag_and_drop_page() {

        // Navigate to the drag and drop URL from config
        getDriver().get(config.getDragDropUrl());

        // Create page object
        dragDropPage = new DragDropPage(getDriver());
    }

    @When("the user drags the object to the target area")
    public void the_user_drags_the_object_to_the_target_area() {

        // Switch into iframe before performing drag and drop
        dragDropPage.switchToFrame();

        // Perform drag and drop action
        dragDropPage.dragAndDrop();
    }

    @Then("the object should be dropped successfully")
    public void the_object_should_be_dropped_successfully() {

        // Check the drop result text
        Assert.assertTrue(
            dragDropPage.isDropped(),
            "Drag and drop was not successful"
        );
    }
}