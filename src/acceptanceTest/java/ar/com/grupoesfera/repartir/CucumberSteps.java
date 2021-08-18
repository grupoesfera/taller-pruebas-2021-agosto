package ar.com.grupoesfera.repartir;

import ar.com.grupoesfera.repartir.atest.BaseDeDatosFixture;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("acceptanceTest")
public abstract class CucumberSteps {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    WebDriver driver;

    @Autowired
    BaseDeDatosFixture baseDeDatos;

    String url(String path) {

        return "http://localhost:" + randomServerPort + path;
    }

}
