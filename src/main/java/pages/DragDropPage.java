package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import pages.BasePage;

public class DragDropPage extends BasePage {

    public DragDropPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By iframe = By.cssSelector(".demo-frame");
    private By draggable = By.id("draggable");
    private By droppable = By.id("droppable");

    // Switch to iframe
    public void switchToFrame() {
        wait.until(d -> driver.findElement(iframe));
        driver.switchTo().frame(driver.findElement(iframe));
    }

    // Drag and drop action
    public void dragAndDrop() {

        Actions actions = new Actions(driver);

        actions.dragAndDrop(
                wait.until(d -> driver.findElement(draggable)),
                wait.until(d -> driver.findElement(droppable))
        ).perform();
    }

    // Get drop text
    public String getDropText() {
        return getText(droppable);
    }
    
    // is item dropped
    public boolean isDropped() {
        return getDropText().contains("Dropped");
    }
}