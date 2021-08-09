package ar.com.grupoesfera.repartir.itest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public abstract class Fixture {

    public static GrupoBuilder unGrupo() {

        return new GrupoBuilder();
    }

    public static BigDecimal $(int pesos, int centavos) {

        return BigDecimal.valueOf(pesos * 100 + centavos, 2);
    }

    public static List<String> son(String... miembros) {

        return Arrays.asList(miembros);
    }
}
