package br.campospadilhaa.tasks.prod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HealthCheckIT {

	@Test
	public void healthCheck() throws MalformedURLException {

		DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
		WebDriver webDriver = new RemoteWebDriver(new URL("http://192.168.29.238:4444/wd/hub"), desiredCapabilities);

		try {
			webDriver.navigate().to("http://192.168.29.238:9999/tasks");

			webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// obtendo da tela o valor do id 'version'
			String version = webDriver.findElement(By.id("version")).getText();

			// verificando se no valor retornado contem a citação 'build', sinal que o projeto está rodando
			Assert.assertTrue(version.startsWith("build"));
			
		} finally {
			// fechando a tela do navegador
			webDriver.quit();
		}
	}
}