package com.backend.vet.fabriziopalenque;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class VerClienteInexistenteTest {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "http://localhost:5173"; 

    @BeforeTest
    public void setDriver() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void verificarClienteInexistente() {
        /********** Preparación de la prueba **********/
        // Navegar a una URL de un cliente que probablemente no exista
        driver.get(baseUrl + "/clients/9999");

        /*********** Lógica de la prueba ***********/
        // Esperar a que aparezca el mensaje de "no encontrado"
        WebElement notFoundMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("not-found-message")));

        /************ Verificación del resultado esperado - Assert ***************/
        Assert.assertTrue(notFoundMessage.isDisplayed(), "No se mostró el mensaje de 'página no encontrada' para un cliente inexistente.");
    }

    @AfterTest
    public void closeDriver() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
}
