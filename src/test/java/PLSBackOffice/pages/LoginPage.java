package PLSBackOffice.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static PLSBackOffice.Utilities.Credentials.userCredentials;


public class LoginPage extends BasePage {
    private final Page page;
    public final Locator COMPID;
    public final Locator username;
    public final Locator password;
    public final Locator rememberDetails;
    public final Locator loginBtn;
    public final Locator forgotPassword;
    public final Locator changePasswordLink;
    public final Locator newPassword;
    public final Locator changePasswordBtn;
    public final Locator backBtn;
    public final Locator errorMessage;
    public final Locator logOutBtn;


    public LoginPage(Page page) {
        super(page);
        this.page = page;
        this.COMPID = page.locator("input#organization");
        this.username = page.locator("input#username");
        this.password = page.locator("input#password");
        this.rememberDetails = page.locator("text=Remember Details");
        this.loginBtn = page.locator("zero-button.action-login");
        this.forgotPassword = page.locator("text=Forgot Password");
        this.changePasswordLink = page.locator("text=Change Password");
        this.newPassword = page.locator("input#new-password");
        this.changePasswordBtn = page.locator("zero-button[type=submit]");
        this.backBtn = page.locator("zero-button[type=button]");
        this.errorMessage = page.locator("div.message");
        this.logOutBtn = page.locator("text=Log out");
    }

    public Locator getProfile(String usernameCompId) {
        return page.locator("text=" + usernameCompId);
    }

    public void login(String user) {
        page.waitForTimeout(2000);
        COMPID.type(userCredentials(user).get("COMPID"));
        username.type(userCredentials(user).get("Username"));
        password.type(userCredentials(user).get("Password"));
        loginBtn.click();
    }

    public void updatePassword(String user, String newPassword) {
        COMPID.type(userCredentials(user).get("COMPID"));
        username.type(userCredentials(user).get("Username"));
        password.type(userCredentials(user).get("Password"));
        this.newPassword.type(newPassword);
        changePasswordBtn.click();
    }

    public void userClickOn(String buttonType) {
        switch (buttonType) {
            case "login":
                loginBtn.click();
                break;
            case "change password":
                changePasswordLink.click();
                break;
            case "Profile":
                getProfile("Client2:HENDR-PILOT").click();
                break;
            case "Log Out":
                logOutBtn.click();
                break;
            case "back":
                backBtn.click();
                break;
        }
    }

}
