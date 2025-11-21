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

public class ListarClientesTest {
    
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
    public void verificarListadoDeClientes() {
        /********** Preparación de la prueba **********/
        driver.get(baseUrl + "/clients");

        /*********** Lógica de la prueba ***********/
        // 1. Esperar a que la tabla de clientes sea visible
        WebElement clientsTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("clients-table")));

        /************ Verificación del resultado esperado - Assert ***************/
        Assert.assertTrue(clientsTable.isDisplayed(), "La tabla de clientes no se muestra.");
    }

    @AfterTest
    public void closeDriver() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
}
