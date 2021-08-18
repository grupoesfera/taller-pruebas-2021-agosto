package ar.com.grupoesfera.repartir.atest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
@Profile("acceptanceTest")
public class WebDriverSupport {

    public static final String CHROME_VERSION = "91";

    private WebDriver driver;

    @PostConstruct
    public void start() {

        WebDriverManager.chromedriver()
                .browserVersion(CHROME_VERSION)
                .setup();

        driver = new ChromeDriver();
    }

    @Bean
    public WebDriver getWebDriver() {

        return driver;
    }

    @PreDestroy
    public void stop() {

        driver.quit();
    }
}
