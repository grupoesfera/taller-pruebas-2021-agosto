package ar.com.grupoesfera.repartir.repositories;

import ar.com.grupoesfera.repartir.config.BaseDeDatos;
import ar.com.grupoesfera.repartir.model.Grupo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("integrationTest")
class GruposRepositoryTest {

    @Autowired
    GruposRepository repository;

    @Autowired
    BaseDeDatos baseDeDatos;

    @BeforeEach
    public void prepararBaseDeDatos() {

        baseDeDatos.inicializar();
    }

    @Test
    void persistirCuentaCompartida() {

        Grupo grupo = new Grupo();
        grupo.setNombre("Fin de semana");
        grupo.setMiembros(Arrays.asList("utube", "rsantos", "ogomez"));
        grupo.setTotal(BigDecimal.valueOf(30025,2));

        Grupo grupoGuardado = repository.save(grupo);

        Optional<Grupo> resultado = repository.findById(grupoGuardado.getId());
        
        assertThat(resultado.isPresent()).isTrue();
        Grupo grupoRecuperado = resultado.get();
        assertThat(grupoRecuperado.getId()).isNotNull();
        assertThat(grupoRecuperado.getNombre()).isEqualTo("Fin de semana");
        assertThat(grupoRecuperado.getMiembros()).containsExactly("utube","rsantos","ogomez");
        assertThat(grupoRecuperado.getTotal()).isEqualByComparingTo(BigDecimal.valueOf(30025,2));
    }

    @Test
    void actualizarElTotal() {

        Grupo grupo = new Grupo();
        grupo.setNombre("Almuerzo");
        grupo.setMiembros(Arrays.asList("mariano", "juan"));
        final BigDecimal VALOR_ORIGINAL = BigDecimal.valueOf(21000, 2);
        grupo.setTotal(VALOR_ORIGINAL);
        repository.save(grupo);
        final Long ID = grupo.getId();

        final BigDecimal VALOR_ACTUALIZADO = BigDecimal.valueOf(33410, 2);
        grupo.setTotal(VALOR_ACTUALIZADO);
        repository.save(grupo);

        Optional<Grupo> resultado = repository.findById(ID);
        assertThat(resultado.isPresent()).isTrue();
        assertThat(resultado.get().getTotal()).isEqualByComparingTo(VALOR_ACTUALIZADO);
    }


    @Test
    void alCambiaElTotalDeUnaCuentaCompartidaExistenteYGuardarlaElTotalSeActualiza() {

        final Long ID = dadoQueExisteUnaCuentaConTotalDe210();
        cuandoCambioElTotalPor334_10YLoGuardoParaCuentaConId(ID);
        entoncesQuedaActualizadoElTotalPor334_10ParaCuentaConId(ID);
    }

    private Long dadoQueExisteUnaCuentaConTotalDe210() {

        final var ALGUN_NOMBRE = "Almuerzo";
        final var ALGUN_GRUPO_DE_PERSONAS = Arrays.asList("mariano", "juan");
        final var TOTAL_ORIGINAL = BigDecimal.valueOf(21000, 2);

        Grupo grupo = new Grupo();
        grupo.setNombre(ALGUN_NOMBRE);
        grupo.setMiembros(ALGUN_GRUPO_DE_PERSONAS);
        grupo.setTotal(TOTAL_ORIGINAL);
        repository.save(grupo);
        return grupo.getId();
    }

    private void cuandoCambioElTotalPor334_10YLoGuardoParaCuentaConId(final Long ID) {

        final BigDecimal TOTAL_ACTUALIZADO = BigDecimal.valueOf(33410, 2);

        Grupo grupo = repository.findById(ID).get();
        grupo.setTotal(TOTAL_ACTUALIZADO);
        repository.save(grupo);
    }

    private void entoncesQuedaActualizadoElTotalPor334_10ParaCuentaConId(final Long ID) {

        final var TOTAL_ESPERADO = BigDecimal.valueOf(33410, 2);

        Optional<Grupo> resultado = repository.findById(ID);
        assertThat(resultado.isPresent()).isTrue();
        assertThat(resultado.get().getTotal()).isEqualByComparingTo(TOTAL_ESPERADO);
    }
}
