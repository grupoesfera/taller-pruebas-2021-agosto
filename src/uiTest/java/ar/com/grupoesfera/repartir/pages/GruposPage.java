package ar.com.grupoesfera.repartir.pages;

import ar.com.grupoesfera.repartir.ui.UITest;
import org.openqa.selenium.By;

public class GruposPage extends UITest.PageObject {

    public GruposPage(UITest test) {

        super(test);
    }

    public GruposPage ir() {

        this.driver().navigate().to(url("/"));
        var iniciarButton = this.driver().findElement(By.id("iniciarBienvenidaButton"));
        iniciarButton.click();

        return this;
    }

    public GruposPage clickEnCrear() {

        this.driver().findElement(By.ById.id("crearGruposButton")).click();

        return this;
    }

    public GruposPage tipearNombre(String nombre) {

        this.driver().findElement(By.ById.id("nombreGrupoNuevoInput")).sendKeys("After Office");
        return this;
    }

    public GruposPage tipearMiembro(String miembro) {

        this.driver().findElement(By.ById.id("miembrosGrupoNuevoInput")).sendKeys(miembro + "\n");
        return this;
    }

    public GruposPage clickEnGuardar() {

        this.driver().findElement(By.ById.id("guardarGrupoNuevoButton")).click();
        return this;
    }
}
