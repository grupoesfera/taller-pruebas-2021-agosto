package ar.com.grupoesfera.repartir.ui;

import ar.com.grupoesfera.repartir.pages.GruposPage;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class CrearGrupo extends UITest {

    @Test
    public void paraElAfterOfficeConTresPersonas() throws InterruptedException {

        GruposPage gruposPage = new GruposPage(this);
        gruposPage.ir();

        gruposPage.clickEnCrear();
        gruposPage.tipearNombre("After Office");
        gruposPage.tipearMiembro("pablo")
                  .tipearMiembro("maria")
                  .tipearMiembro("roberto");
        gruposPage.clickEnGuardar();

        TimeUnit.MINUTES.sleep(5);
    }

}
