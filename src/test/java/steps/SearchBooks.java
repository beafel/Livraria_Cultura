package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchBooks {
 
    String url;
    WebDriver driver;

    private static String nomePasta = new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime());

    //Funcoes ou Metodos de Apoio
    public void print(String nomePrint) throws IOException {
        // Classe TakesScreenshot e Objeto driver (())
        File foto = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //System.out.println("nomePasta:" + nomePasta);
        // Passa o arquivo de foto da memoria para o arquivo
        FileUtils.copyFile(foto, new File("target/" + nomePasta + "/" + nomePrint + ".png"));
    }


    @Before
    public void setUp(){
        url = "https://www3.livrariacultura.com.br";
        System.setProperty("webdriver.chrome.driver","drivers/chrome/chromedriver100.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);

        System.out.println("Preparou o setup");
    }

    @After
    public void tearDown(){
        driver.quit();

        System.out.println("Fechou o browser");
    }

    @Given("^I access Livraria Cultura homepage \"([^\"]*)\"$")
    public void iAccessLivrariaCulturaHomepage(String arg0) throws IOException {
        driver.get(url);
        //driver.findElement(By.id("aviso-cookies-accepted")).click();

        System.out.println("Passo 1 - Homepage Livraria Cultura");
        print("Passo 1 - Exibe Site da Livraria Cultura");
    }

    @When("^I do successfull login$")
    public void i_do_successfull_login() throws IOException {
        //driver.findElement(By.cssSelector("a.close__icon")).click();
        //driver.findElement(By.id("btnCloseStep1")).click();
        //driver.findElement(By.id("classicLoginBtn")).click();
        driver.findElement(By.cssSelector("p.login")).click();
        driver.findElement(By.cssSelector("div.bg-opacity")).click();
        driver.findElement(By.id("loginWithUserAndPasswordBtn")).click();
        driver.findElement(By.id("inputEmail")).sendKeys("qapratica@gmail.com");
        driver.findElement(By.id("inputPassword")).sendKeys("************");
        driver.findElement(By.id("classicLoginBtn")).sendKeys(Keys.ENTER);

        System.out.println("Passo 2 - Processo de Login");
        print("Passo 2 - Processo de Login");
    }

    @Then("^I see my name logged in$")
    public void i_see_my_name_logged_in() throws IOException {
        assertEquals("Olá qapratica", driver.findElement(By.cssSelector("span.welcome")).getText());

        System.out.println("Passo 3 - Login com Sucesso");
        print("Passo 3 - Login com Sucesso");
    }

    @Given("^I am at Livraria Cultura homepage \"([^\"]*)\"$")
    public void iAmAtLivrariaCulturaHomepage(String arg0) throws IOException {
        driver.get(url);
        //driver.findElement(By.id("aviso-cookies-accepted")).click();

        System.out.println("Passo 4 - Livraria Cultura Homepage");
        print("Passo 4 - Livraria Cultura Homepage");
    }

    @When("^I type the \"([^\"]*)\" of the book and click Return$")
    public void iTypeTheOfTheBookAndClickReturn(String title) throws IOException {
        driver.findElement(By.xpath("/html[1]/body[1]/div[7]/header[1]/section[1]/div[1]/div[1]/div[3]/div[2]/fieldset[1]/input[2]")).sendKeys(title);
        driver.findElement(By.xpath("/html[1]/body[1]/div[7]/header[1]/section[1]/div[1]/div[1]/div[3]/div[2]/fieldset[1]/input[2]")).sendKeys(Keys.ENTER);

        System.out.println("Passo 5 - Pesquisa por " + title);
        print("Passo 5 - Pesquisa por " + title);
    }

    @Then("^I check the \"([^\"]*)\" and the \"([^\"]*)\" of the book$")
    public void iCheckTheAndTheOfTheBook(String title, String author) throws IOException {
        assertEquals(title, driver.findElement(By.cssSelector("strong.value")).getText());
        //assertTrue(driver.findElement(By.xpath("//li[contains(text(),'author')]")).getText().contains(author));
        assertTrue(driver.findElement(By.cssSelector("div.prateleiraProduto__autor__nome")).getText().contains(author));

        System.out.println("Passo 6 - Valida o " + title + " e o " + author);
        print("Passo 6 - Valida o " + title + " e o " + author);
    }

    @And("^I click on add to cart button$")
    public void iClickOnAddToCartButton() throws IOException {
        driver.findElement(By.cssSelector("a.prateleiraProduto__comprar__botaoComprar")).click();

        System.out.println("Passo 7 - Clica no botao Comprar");
        print("Passo 7 - Clica no botao Comprar");
    }

    @When("^I click on cart button$")
    public void iClickOnCartButton() throws IOException {
        //driver.findElement(By.cssSelector("button.buy-in-page-button")).click();
        driver.findElement(By.cssSelector("a.btn.btn-default.btn-block.btn-mini-cart")).click();

        System.out.println("Passo 8 - Clica no Carrinho de Compras");
        print("Passo 8 - Clica no Carrinho de Compras");
    }

    @Then("^show the title of the \"([^\"]*)\" and subtotal \"([^\"]*)\"$")
    public void showTheTitleOfTheAndPrice(String book, String value) throws IOException {
        assertEquals(book, driver.findElement(By.cssSelector("p.title-mini")).getText());
        assertEquals(value,driver.findElement(By.cssSelector("#mini-cart-admake-total")).getText());

        System.out.println("Passo 9 - Valida " + book + " e " + value + " no carrinho");
        print("Passo 9 - Valida " + book + " e " + value + " no carrinho");
    }

}