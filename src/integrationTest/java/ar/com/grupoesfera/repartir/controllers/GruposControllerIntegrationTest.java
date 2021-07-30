package ar.com.grupoesfera.repartir.controllers;

import ar.com.grupoesfera.repartir.model.Grupo;
import ar.com.grupoesfera.repartir.services.GruposService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integrationTest")
public class GruposControllerIntegrationTest {

    @MockBean
    GruposService grupos;

    @LocalServerPort
    int randomServerPort;

    @BeforeEach
    void setHost() {

    }

    @Test
    void listar() {

        final List<Grupo> GRUPOS_EXISTENTES = Collections.emptyList();
        when(grupos.listarGrupos()).thenReturn(GRUPOS_EXISTENTES);

        with()
                .accept(ContentType.JSON)
                .port(randomServerPort)
                .contentType(ContentType.JSON)
            .when()
                .get("/grupos")
            .then()
                .statusCode(200);
    }

}
