package ar.com.grupoesfera.repartir;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.assertj.core.api.Assertions.*;

public class GruposSteps extends CucumberSteps  {

    @Given("no existe ningún grupo")
    public void noExisteNingunGrupo() {

        baseDeDatos.estaVacia();
    }

    @And("el usuario inicia la aplicación")
    public void elUsuarioIniciaLaAplicacion() {

        driver.navigate().to(url("/"));
        var iniciarButton = driver.findElement(By.id("iniciarBienvenidaButton"));
        iniciarButton.click();
    }

    @When("el usuario selecciona crear grupo")
    public void elUsuarioSeleccionaCrearGrupo() {

        var crearGruposButton = driver.findElement(By.id("crearGruposButton"));
        crearGruposButton.click();
    }

    @And("completa con el nombre {string}")
    public void completaConElNombre(String nombre) {

        driver.findElement(By.id("nombreGrupoNuevoInput")).sendKeys(nombre);
    }

    @And("indica que los miembros son {string}, {string} y {string}")
    public void indicaQueLosMiembroSon(String miembro1, String miembro2, String miembro3) {

        var miembrosInput = driver.findElement(By.id("miembrosGrupoNuevoInput"));
        miembrosInput.sendKeys(miembro1);
        miembrosInput.sendKeys(Keys.ENTER);
        miembrosInput.sendKeys(miembro2);
        miembrosInput.sendKeys(Keys.ENTER);
        miembrosInput.sendKeys(miembro3);
        miembrosInput.sendKeys(Keys.ENTER);
    }

    @And("indica que los miembros son:")
    public void indicaQueLosMiembroSon(List<String> miembros) {

        var miembrosInput = driver.findElement(By.id("miembrosGrupoNuevoInput"));

        for (var miembro : miembros) {
            miembrosInput.sendKeys(miembro);
            miembrosInput.sendKeys(Keys.ENTER);
        }
    }

    @And("guarda el grupo")
    public void guardaElGrupo() {

        driver.findElement(By.id("guardarGrupoNuevoButton")).click();
    }

    @Then("se muestra {int}° el grupo {string} con total {string}")
    public void seMuestraElNuevoGrupo(int posicion, String nombre, String total) {

        var wait = new WebDriverWait(driver, 2);
        wait.until(visibilityOfElementLocated(By.id("mensajesToast")));

        var grupoTR = driver.findElements(By.cssSelector("app-grupos table tr"));
        assertThat(grupoTR).hasSizeGreaterThan(posicion);

        var campoTDs = grupoTR.get(posicion).findElements(By.tagName("td"));
        assertThat(campoTDs.get(0).getText()).isNotEmpty();
        assertThat(campoTDs.get(1).getText()).isEqualTo(nombre);
        assertThat(campoTDs.get(2).getText()).isEqualTo(total);
    }

    @Given("existe un grupo")
    public void existeUnGrupo() {

        baseDeDatos.existeUnUnicoGrupo();
    }

    @Given("existe el grupo #{int} {string} sin gastos")
    public void existeElGrupoSinGastos(int idGrupo, String nombre) {

        baseDeDatos.tieneElGrupoSinGastos(idGrupo, nombre);
    }

    @When("el usuario selecciona agregar gasto al grupo #{int}")
    public void elUsuarioSeleccionaAgregarGastoAlGrupo(int idGrupo) {

        var agregarGastoButton = driver.findElement(By.id("agregarGastoGruposButton-" + idGrupo));
        agregarGastoButton.click();
    }

    @And("completa con el monto de $ {string}")
    public void completaConElMontoDe(String monto) {

        var montoInput = driver.findElement(By.id("montoGastoNuevoInput"));
        montoInput.clear();
        montoInput.sendKeys(monto);
    }

    @And("guarda el gasto")
    public void guardaElGasto() {

        var agregarGastoButton = driver.findElement(By.id("guardarGastoNuevoButton"));
        agregarGastoButton.click();
    }

    @Then("ve la confirmación {string}")
    public void veLaConfirmación(String mensaje) {

        var wait = new WebDriverWait(driver, 2);
        var mensajesToast = wait.until(visibilityOfElementLocated(By.id("mensajesToast")));
        assertThat(mensajesToast.getText())
                .contains("Éxito")
                .contains(mensaje);
    }

    @And("se actualiza el total del grupo #{int} a {string}")
    public void seActualizaElTotalDelGrupoA$(int idGrupo, String monto) {

        var grupoTR = driver.findElements(By.cssSelector("app-grupos table tr"));

        var campoTDs = grupoTR.get(1).findElements(By.tagName("td"));
        assertThat(campoTDs.get(0).getText()).isNotEmpty();
        assertThat(campoTDs.get(2).getText()).isEqualTo(monto);
    }
}
