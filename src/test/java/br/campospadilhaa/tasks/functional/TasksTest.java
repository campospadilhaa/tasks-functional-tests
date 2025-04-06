package br.campospadilhaa.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// teste não está rodando porque não consegui obter o ChromeDriver da versão atual.
// no curso era a versão 80
// neste momento a versão é 134.0.6998.178 e pelo que entendi foi alterado a forma de disponibilização do ChromeDriver
public class TasksTest {

	// definição do passo a passo do teste na navegação
	public WebDriver acessarAplicacao() {

		// link do código com o browser
		WebDriver webDriver = new ChromeDriver();

		webDriver.navigate().to("http://localhost:8001/tasks");
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return webDriver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {

		WebDriver webDriver = acessarAplicacao();

		// através da ação "Inspect" no browser obter o Id do elemento, 
		// clicar em Add Todo, neste caso o botão
		webDriver.findElement(By.id("addTodo")).click();

		// escrever descrição
		webDriver.findElement(By.id("task")).sendKeys("Nova tarefa - Functional Selenium");

		// escrever a data
		webDriver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

		// clicar em salvar
		webDriver.findElement(By.id("saveButton")).click();

		// validar mensagem de sucesso
		String message = webDriver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!", message);

		// fechar o browser
		//webDriver.quit()
	}

	@Test
	public void naoDeveSalvarTarefaSemDescriao() {

		WebDriver webDriver = acessarAplicacao();

		// através da ação "Inspect" no browser obter o Id do elemento, 
		// clicar em Add Todo, neste caso o botão
		webDriver.findElement(By.id("addTodo")).click();

		// escrever descrição - REMOVIDA A ATRIBUIÇÃO DA DESCRIÇÃO, MOTIVO DO TESTE
		//webDriver.findElement(By.id("task")).sendKeys("Nova tarefa - Functional Selenium");

		// escrever a data
		webDriver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

		// clicar em salvar
		webDriver.findElement(By.id("saveButton")).click();

		// validar mensagem de sucesso
		String message = webDriver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the task description", message);

		// fechar o browser
		//webDriver.quit()
	}

	@Test
	public void naoDeveSalvarTarefaSemData() {

		WebDriver webDriver = acessarAplicacao();

		// através da ação "Inspect" no browser obter o Id do elemento, 
		// clicar em Add Todo, neste caso o botão
		webDriver.findElement(By.id("addTodo")).click();

		// escrever descrição
		//webDriver.findElement(By.id("task")).sendKeys("Nova tarefa - Functional Selenium");

		// escrever data - REMOVIDA A ATRIBUIÇÃO DA DESCRIÇÃO, MOTIVO DO TESTE
		//webDriver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

		// clicar em salvar
		webDriver.findElement(By.id("saveButton")).click();

		// validar mensagem de sucesso
		String message = webDriver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the due date", message);

		// fechar o browser
		//webDriver.quit()
	}

	@Test
	public void NaoDeveSalvarTarefaComDataPassada() {

		WebDriver webDriver = acessarAplicacao();

		// através da ação "Inspect" no browser obter o Id do elemento, 
		// clicar em Add Todo, neste caso o botão
		webDriver.findElement(By.id("addTodo")).click();

		// escrever descrição
		//webDriver.findElement(By.id("task")).sendKeys("Nova tarefa - Functional Selenium");

		// escrever data - ATRIBUÍDA UMA DATA PASSADA
		webDriver.findElement(By.id("dueDate")).sendKeys("10/10/2010");

		// clicar em salvar
		webDriver.findElement(By.id("saveButton")).click();

		// validar mensagem de sucesso
		String message = webDriver.findElement(By.id("message")).getText();
		Assert.assertEquals("Due date must not be in past", message);

		// fechar o browser
		//webDriver.quit()
	}
}