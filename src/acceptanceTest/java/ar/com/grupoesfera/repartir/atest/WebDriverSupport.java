package ar.com.grupoesfera.repartir.atest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@Profile("acceptanceTest")
public class WebDriverSupport {

    public static final String CHROME_VERSION = "91";

    @PostConstruct
    public void downloadWebDriver() {

        WebDriverManager.chromedriver()
                .browserVersion(CHROME_VERSION)
                .setup();
    }

    @Bean
    public WebDriver buildWebDriver() {

        var chromeDriver = new ChromeDriver();

        return chromeDriver;
    }
}
