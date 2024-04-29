package PLSBackOffice.pages;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;



public class AdminPage  {
    private final Page page;
    public final Locator addUserBtn;
    public final Locator removeUserBtn;
    public final Locator userTitle;
    public final Locator userFirstName;
    public final Locator userLastName;
    public final Locator userEmail;
    public final Locator userProfile;
    public final Locator oneTimePassword;
    public final Locator submitBtn;
    public final Locator closeBtn;
    public final Locator successMessage;
    public final Locator confirmRemoval;
    public final Locator cancelBtn;
    public final Locator status;


    public AdminPage(Page page) {
        this.page = page;
        this.addUserBtn = page.locator("text=Add User >> button");
        this.removeUserBtn = page.locator("text=Remove Users >> button");
        this.userTitle = page.locator("input[name=userTitle]");
        this.userFirstName = page.locator("input[name=firstName]");
        this.userLastName = page.locator("input[name=lastName]");
        this.userEmail = page.locator("input[name=email]");
        this.userProfile = page.locator("div.control > slot > input");
        this.oneTimePassword = page.locator("input[name=oneTimePassword]");
        this.submitBtn = page.locator("text=Submit >> button");
        this.closeBtn = page.locator("text=Close >> button");
        this.successMessage = page.locator("#notify-container > zero-toast > span");
        this.confirmRemoval = page.locator("text=Confirm Removal");
        this.cancelBtn = page.locator("text=Cancel");
        this.status = page.locator("div:nth-child(7) > div:nth-child(6)");
    }

    public Locator getRandomProfile(int value) {
        return page.locator("fast-combobox > fast-option:nth-child(" + value + ")");
    }


    public Locator getSpecificCheckBox(String name) {
        return page.locator("text=" + name + " >> [aria-label=\"Press Space to toggle row selection \\(unchecked\\)\"]");
    }

    public void userClickOn(String buttonType) {
        switch (buttonType) {
            case "Add User":
                addUserBtn.click();
                break;
            case "Remove Users":
                removeUserBtn.click();
                break;
            case "Submit":
                submitBtn.click();
                break;
            case "Confirm Removal":
                confirmRemoval.click();
                break;
        }
    }

    /**
     * @return email of new generated user
     * This method used for generate new user on Admin Page
     */
    public String userGenerate() {
        Faker faker = new Faker();
        String[] title = {"Mr", "Mrs", ""};
        String email = faker.internet().emailAddress();
        int num = ((int) (Math.random() * 3 + 1) - 1);

        userTitle.type(title[num]);
        userFirstName.type(faker.name().firstName());
        userLastName.type(faker.name().lastName());
        userEmail.type(email);
        oneTimePassword.type("Password11*");
        userProfile.click();
        getRandomProfile(num + 1).click();

        return email;
    }

}
