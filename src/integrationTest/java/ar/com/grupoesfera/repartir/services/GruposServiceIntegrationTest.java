package ar.com.grupoesfera.repartir.services;

import ar.com.grupoesfera.repartir.itest.BaseDeDatosFixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("integrationTest")
class GruposServiceIntegrationTest {

    @Autowired
    BaseDeDatosFixture baseDeDatos;


}
