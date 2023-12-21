package PLSBackOffice.utilities.DriveUtilities;


import PLSBackOffice.utilities.ConfigReader;
import com.microsoft.playwright.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import static PLSBackOffice.utilities.ConfigReader.readPropertyInt;


public class PlaywrightDriver {

    private static ThreadLocal<Playwright> playwrightThread = new ThreadLocal<>();
    private static ThreadLocal<BrowserType> browserTypeThread = new ThreadLocal<>();
    private static ThreadLocal<Browser> browserThread = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> browserContextThread = new ThreadLocal<>();
    private static ThreadLocal<Page> pageThread = new ThreadLocal<>();
    private static Page page;
    private static Browser browser;
    private static BrowserType browserType;
    private static BrowserContext context;
    private static String browserName = "";
    private static Path downloadPath = null;
    private static int viewWidth = 0;

    static {
        browserName = System.getProperty("browser") == null ? ConfigReader.readProperty("browser") : System.getProperty("browser");
        viewWidth = System.getProperty("set-viewport-width") == null ? readPropertyInt("viewport-width") : Integer.parseInt(System.getProperty("set-viewport-width"));
        downloadPath = Paths.get("target/downloads");
    }

    public static synchronized Page getPage() {
        if (playwrightThread.get() == null) {
            Playwright playwright = createPlaywright();
            playwrightThread.set(playwright);
            Page page = createPage(playwright);
            pageThread.set(page);
        }
        return pageThread.get();
    }

    private static Playwright createPlaywright() {
        return Playwright.create();
    }

    private static synchronized Page createPage(Playwright playwright) {
        switch (browserName.toLowerCase()) {
            case "firefox":
                try {
                    playwright = Playwright.create();
                    browserType = playwright.firefox();
                    browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "firefox-headless":
                try {
                    playwright = Playwright.create();
                    browserType = playwright.firefox();
                    browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "chrome":
                try {
                    playwright = Playwright.create();
                    browserType = playwright.chromium();
                    browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "chrome-headless":
                try {
                    playwright = Playwright.create();
                    browserType = playwright.chromium();
                    browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "edge":
                try {
                    playwright = Playwright.create();
                    browserType = playwright.chromium();
                    browser = browserType.launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "edge-headleess":
                try {
                    playwright = Playwright.create();
                    browserType = playwright.chromium();
                    browser = browserType.launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        context = browser.newContext(new Browser.NewContextOptions().
                setIgnoreHTTPSErrors(true).
                setViewportSize(viewWidth, readPropertyInt("viewport-size-height")));

        browserTypeThread.set(browserType);
        browserThread.set(browser);
        browserContextThread.set(context);
        page = context.newPage();
        return page;

    }

    public static synchronized void closePage() {
        Playwright playwright = playwrightThread.get();
        Page page = pageThread.get();
        if (playwright != null) {
            browser.close();
            browserThread.remove();
            page.close();
            pageThread.remove();
            playwright.close();
            playwrightThread.remove();
        }


    }
}