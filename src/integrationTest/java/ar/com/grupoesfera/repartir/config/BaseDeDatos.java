package ar.com.grupoesfera.repartir.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class BaseDeDatos {

    @Autowired
    private DataSource dataSource;

    public void inicializar() {

        Flyway flyway = Flyway
                .configure()
                .dataSource(dataSource)
                .locations("filesystem:src/main/db/migration")
                .createSchemas(true)
                .load();

        flyway.clean();
        flyway.migrate();
    }
}
