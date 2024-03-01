package andersenCourse.task_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        //Task 3 - 3)Написать метод в параметры которого принимаются два ВебЭлемента.
        //метод выводит в консоль информацию какой из двух элементов располагается  выше на странице,
        //какой из элементов располагается левее на странице,
        //а также какой из элементов занимает большую площадь.
        //Параметры метода могут также включать в себя другие аргументы, если это необходимо.
        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-course-01.andersenlab.com/login");
        WebElement firstElement = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/button"));
        WebElement secondElement = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/a"));
        ElementComparator.compareElements(firstElement, secondElement);
        driver.quit();
    }
}
