import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Selenium {
	
	
	private WebDriver driver;
	
	@Before
	public void setup() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\tools\\chrome\\chromedriver.exe");
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addExtensions(new File("C:\\tools\\extensions\\extension_2_3_164_0.crx"));
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.get("https://www.scrumguides.org/docs/scrumguide/v1/Scrum-Guide-Portuguese-BR.pdf");
		
		
		
	}
	
	
	public String captura() throws IOException
	{
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String nomeArquivo = "C:/prints/5445-"+System.currentTimeMillis()+".png";
			FileUtils.copyFile(file, new File(nomeArquivo));
			return nomeArquivo;
		}
		catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}


	
	@Test
	public void avancPgPdf() throws InterruptedException, IOException{
		
		// Inicializa a variavel numero de páginas
		
		
		int numPg = 0;
		// Captura o numero de páginas e converte String para int
		while(numPg == 0) 
		{
			//Captura somente o número de páginas da String
			String valor = driver.findElement(By.id("numPages")).getText().substring(3);
			
			//Converte valor capturado para int
			numPg = Integer.parseInt(valor);
		}
		
		WebElement clicar = driver.findElement(By.id("next"));
	
		String PRINT_STR = "";
		// Clica até a ultima página do PDF
		for(int click = 0 ; click < numPg; click++) 
		{
			Thread.sleep(2000);
			
			if(PRINT_STR.isEmpty())
				PRINT_STR += captura();
			else
				PRINT_STR += "|" + captura();
			clicar.click();
			
		}
		
		
		
		
		
	}


	
}
