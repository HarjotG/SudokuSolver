import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Web {
    private WebDriver driver;

    /**
     * Constructor for the Web class. Opens the webpage https://www.websudoku.com/
     */
    public Web() {
        System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.navigate().to("https://www.websudoku.com/");
        WebElement frame =
            driver.findElement(By.cssSelector("html > frameset:nth-child(2) > frame:nth-child(1)"));
        driver.switchTo().frame(frame);
    }

    /**
     * Gets the sudoku puzzle from the webpage
     *
     * @return An array representing the sudoku puzzle
     */
    public int[][] getSudoku() {
        int[][] sudoku = new int[Sudoku.SIZE][Sudoku.SIZE];
        for (int i = 0; i < Sudoku.SIZE; i++) {
            for (int j = 0; j < Sudoku.SIZE; j++) {
                String value = driver.findElement(By.id("f" + j + i)).getAttribute("value");
                if (!value.isEmpty()) {
                    sudoku[i][j] = Integer.parseInt(value);
                } else {
                    sudoku[i][j] = 0;
                }
            }
        }
        return sudoku;
    }

    /**
     * Inputs the sudoku puzzle to the webpage
     *
     * @param sudoku The sudoku puzzle to input
     */
    public void solve(int[][] sudoku) {
        Actions action = new Actions(driver);

        for (int i = 0; i < Sudoku.SIZE; i++) {
            for (int j = 0; j < Sudoku.SIZE; j++) {
                WebElement element = driver.findElement(By.id("f" + j + i));

                action.click(element).perform();
                action.sendKeys(Integer.toString(sudoku[i][j])).perform();
            }
        }
    }

}
