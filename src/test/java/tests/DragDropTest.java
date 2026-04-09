package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DragDropPage;

public class DragDropTest extends BaseTest {
	
	@Test(groups = {"smoke", "regression"})
	public void dragAndDropTest() {
		getDriver().get("https://jqueryui.com/droppable/");
		
		DragDropPage page = new DragDropPage(getDriver());
		
		//switch to the iframe first
		page.switchToFrame();
		
		// perform drad and drop
		page.dragAndDrop();
		
		// validate result
		Assert.assertTrue(page.isDropped(), "Drag and drop failed");
	}

}
