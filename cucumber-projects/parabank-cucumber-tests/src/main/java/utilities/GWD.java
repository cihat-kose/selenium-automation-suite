package utilities;
import com.selenium.support.DriverFactory;
import org.openqa.selenium.WebDriver;
public final class GWD {
    private static final ThreadLocal<WebDriver> DRIVERS = new ThreadLocal<>();
    private static final ThreadLocal<String> BROWSERS = new ThreadLocal<>();
    private GWD() { }
    public static WebDriver getDriver() {
        if (DRIVERS.get() == null) DRIVERS.set(BROWSERS.get() == null ? DriverFactory.create() : DriverFactory.create(BROWSERS.get()));
        return DRIVERS.get();
    }
    public static WebDriver currentDriver() { return DRIVERS.get(); }
    public static void quitDriver() {
        try { if (DRIVERS.get() != null) DRIVERS.get().quit(); }
        finally { DRIVERS.remove(); BROWSERS.remove(); }
    }
    public static void threadBrowserSet(String browser) { BROWSERS.set(browser); }
}
