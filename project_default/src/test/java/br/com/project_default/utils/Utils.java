package br.com.project_default.utils;


import io.cucumber.core.api.Scenario;
import io.qameta.allure.Allure;
import lombok.Cleanup;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Utils {

	private static WebDriver driver;

	public static WebDriver getDriver() {
		if (driver == null) {
			try{

				PageLoadStrategy pageLoadStrategy = PageLoadStrategy.NORMAL;
			
				HashMap<String, Object> chromePreferences = new HashMap<String, Object>();
				chromePreferences.put("profile.default_content_settings.popups", 0);
				chromePreferences.put("download.prompt_for_download", "false");
			
				ChromeOptions options = new ChromeOptions();
				options.setPageLoadStrategy(pageLoadStrategy);
				//options.addArguments("--headless");
				options.addArguments("--ignore-certificate-errors");
				options.addArguments("--no-sandbox");
				options.addArguments("disable-infobars");
				options.addArguments("--disable-extensions");
				options.addArguments("window-size=1920,1080");
				options.setExperimentalOption("prefs", chromePreferences);
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				cap.setCapability(ChromeOptions.CAPABILITY, options);

				System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");

				ChromeDriverService driverService = ChromeDriverService.createDefaultService();
				driver = new ChromeDriver(driverService ,options);
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

			}catch(Exception e){
				Assert.fail(e.toString());
			}
		}
		return driver;
	}

	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public static void esperarPagina(int tempo) throws Exception {
		for (int i = 0; i < tempo; i++) {
			Thread.sleep(1000);
		}
	}

	public static void aguardarVisibilidade(Boolean visibilidade, Integer tempoEspera, String valorXpath)
			throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), tempoEspera);

		if (visibilidade = true) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(valorXpath)));
		} else {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(valorXpath)));
		}

	}

	public static void limparCache() {
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_DELETE);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_SHIFT);
			r.keyRelease(KeyEvent.VK_DELETE);
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void paginaContemTexto(String text) throws Exception {
		esperarPagina(2);
		boolean existe = getDriver().getPageSource().contains(text);
		if (existe == false) {
			System.out.println("Não encontrei a String: " + text);
		}
		assertTrue(existe);

	}

	public static boolean textoExiste(String texto) {
		return getDriver().getPageSource().contains(texto);

	}

	public static void capturarScreenshot() {
		File imagem = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			Allure.addAttachment("Screenshot", Files.newInputStream(Paths.get(imagem.getPath())));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void capturarScreenshot(Scenario scenario) {
		final byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenshot, "image/png");

		File imagem = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(imagem,
					(new File("./target/screnshot", scenario.getName() + " - " + scenario.getStatus() + ".jpg")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String gerarNumerosAleatorios(int qtdDigitos) {
		String numero = Double.toString(Math.random());
		return numero.substring(3, qtdDigitos + 3);
	}

	public static void aceitarAlertaJavascript() {
		Alert alt = getDriver().switchTo().alert();
		alt.accept();
	}

	public static void cancelarAlertaJavascript() {
		Alert alt = getDriver().switchTo().alert();
		alt.dismiss();
	}

	public static void movimentarScroll(String pixels) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.parent.scrollTo(0," + pixels + ");", "0");
	}

	public static boolean elementoExiste(String elemento) {
		boolean existe = false;
		try {
			existe = Utils.getDriver().findElement(By.xpath(elemento)).isEnabled();
		} catch (NoSuchElementException e) {
			existe = false;
		}
		return existe;
	}

	public static void moverMouse() throws Exception {
		Robot robot = new Robot();
		robot.mouseMove(+400, +100);
		robot.mouseMove(-400, -100);
	}

	public static void atualizarPagina() throws Exception {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F5);
		robot.keyRelease(KeyEvent.VK_F5);
		Thread.sleep(10000);
	}

	public static void moverMousePara(String xPath) throws Exception {
		Actions acao = new Actions(getDriver());
		acao.moveToElement(getDriver().findElement(By.xpath(xPath))).build().perform();
	}

	public static WebElement findElement(String xPath) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 20);
		return wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(xPath))));
	}

	public static List<WebElement> findElements(String xPath) {
		return getDriver().findElements(By.xpath(xPath));
	}

	public static void dobleClick(String xPath) {
		(new Actions(getDriver())).doubleClick(findElement(xPath)).build().perform();
	}

	public static void scrollByElement(WebElement elemento) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView();", elemento);
	}

	public static void addEnvironmentAllure(ArrayList<String> infomationAplication) {
		try {
			Capabilities cap = ((RemoteWebDriver) getDriver()).getCapabilities();

			File env = new File("./target/allure-reports/environment.properties");
			BufferedWriter as = new BufferedWriter(new FileWriter(env));
			as.write("BROWSER = "+cap.getBrowserName().toUpperCase()+" - Version: "+ cap.getVersion());
			as.newLine();
			as.write("APLICATION = "+infomationAplication.get(0).toUpperCase() + " - VERSION: " +infomationAplication.get(1));
			as.newLine();
			as.write("ENVIRONMENT = " + infomationAplication.get(2).toUpperCase());
			as.newLine();
			as.write("OPERATIONAL SYSTEM = " + System.getProperty("os.name").toUpperCase());
			as.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void focoEmOutraJanela() {
		for (String winHandle : Utils.getDriver().getWindowHandles()) {
			getDriver().switchTo().window(winHandle);
		}
	}

	public static void janelaPrincipal() {
		String mainWindow = Utils.getDriver().getWindowHandle();
		Utils.getDriver().switchTo().window(mainWindow);

	}

	public static String getUrl() {
		return Utils.getDriver().getCurrentUrl();
	}

	public static void fecharJanelaAtual() {
		Utils.getDriver().close();
	}

	public static int quantidadeDejanelas() {
		return Utils.getDriver().getWindowHandles().size();
	}

	public static void escritorArquivos(String arquivo, String conteudo) throws IOException {

		// Cria arquivo
		File file = new File(arquivo);

		// Prepara para escrever no arquivo
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

		try {

			// Se o arquivo nao existir, ele gera
			if (!file.exists()) {
				file.createNewFile();
			}

			// Escreve e fecha arquivo
			bw.write(conteudo);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// fecha o arquivo
		bw.close();
	}

	
	public static boolean verificarArquivo(String path){
		File file = new File(path);
		if (file.exists()){
			return true;
		}else {
			return false;
		}
	}

	public static String listarArquivo(String path){
		File file = new File(path);
		File afile[] = file.listFiles();
		String retorno = "";
		for (int i = 0; i < afile.length; i++) {
			if(afile[i].toString().endsWith("xlsx")){
				retorno = afile[i].toString();
			}
		}
		return retorno;
	}

	public static ArrayList<String> lerExcel(String path) throws IOException {
			ArrayList<String> list = new ArrayList<String>();
			//Recuperando o Arquivo
			@Cleanup FileInputStream file = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			//Setando a Aba
			XSSFSheet sheet = workbook.getSheetAt(0);
			//Setando as linhas
			List<XSSFRow> rows = (List<XSSFRow>) toList(sheet.iterator());

			rows.forEach(row ->{
				//setando as Células
				List<XSSFCell> cells = (List<XSSFCell>) toList(row.cellIterator());
				list.add(cells.get(0).getStringCellValue());
			});
			return list;
	}

	public static void deleteFile(String file){
		File af = new File(file);
		af.delete();
	}

	private static List<?> toList(Iterator<?> iterator){
		return IteratorUtils.toList(iterator);
	}

	public static boolean elementoExisteCss(String elemento) {
		boolean existe = false;
		try {
			existe = Utils.getDriver().findElement(By.cssSelector(elemento)).isEnabled();
		} catch (NoSuchElementException e) {
			existe = false;
		}
		return existe;
	}


}
