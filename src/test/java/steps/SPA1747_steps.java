package steps;

import cucumber.api.java.en.Then;
import org.junit.Assert;
import pages.Homepage;
import utilities.BrowserUtils;
import utilities.DBUtils;

public class SPA1747_steps {

    Homepage homeP = new Homepage();

    @Then("verify {string} is displayed in home page")
    public void verify_is_displayed_in_home_page(String location) {
        BrowserUtils.waitFor(1);
        String ilText = homeP.location.getText();

        // Verify location in UI
        Assert.assertEquals("User in different page", location, ilText);

        DBUtils.createConnection();
        String query = "SELECT location FROM campus WHERE location = 'IL';";
        String actualLocation = DBUtils.getColumnData(query,"location").get(0).toString();

        // Verify location in DB
        Assert.assertEquals("User in different page", location, actualLocation);

        DBUtils.destroy();
    }
}