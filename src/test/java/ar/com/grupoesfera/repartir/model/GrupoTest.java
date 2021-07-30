package ar.com.grupoesfera.repartir.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class GrupoTest {

    @Test
    void crearGrupoSinParametros() {

        Grupo grupo = new Grupo();

        assertThat(grupo.getId()).isNull();
        assertThat(grupo.getNombre()).isNull();
        assertThat(grupo.getMiembros()).isNull();
        assertThat(grupo.getTotal()).isNull();
    }

    @Test
    void crearGrupoConId() {

        final Long ID = 153L;
        Grupo grupo = new Grupo(ID);

        assertThat(grupo.getId()).isEqualTo(ID);
        assertThat(grupo.getNombre()).isNull();
        assertThat(grupo.getMiembros()).isNull();
        assertThat(grupo.getTotal()).isNull();
    }

    @Test
    void noEsValidoCuandoElGrupoTieneSoloUnMiembro() {

        Grupo grupo = new Grupo();
        grupo.setMiembros(Arrays.asList("martin"));

        boolean esValida = grupo.esValido();

        assertThat(esValida).isFalse();
    }

    @Test
    void esValidoCuandoElGrupoTieneDosMiembros() {

        Grupo grupo = new Grupo();
        grupo.setMiembros(Arrays.asList("mtolosa", "sdiaz"));

        boolean esValida = grupo.esValido();

        assertThat(esValida).isTrue();
    }

    @Test
    void noEsValidoCuandoLosMiembrosSonNulos() {

        Grupo grupo = new Grupo();

        grupo.setMiembros(null);

        assertThat(grupo.esValido()).isFalse();
    }
}
