package andersenCourse.task_2;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Task 2 - Написать программу, которая будет открывать пять различных страниц в новых окнах:
        //    http://www.automationpractice.pl/index.php
        //    https://zoo.waw.pl/
        //    https://www.w3schools.com/
        //    https://www.clickspeedtester.com/click-counter/
        //    https://andersenlab.com/
        //Прописать цикл, который будет переключаться поочередно через все страницы,
        //для каждой страницы выводить в консоль название и ссылку на эту страницу.
        //И будет закрывать ту страницу в названии которой есть слово "Zoo".
        List<String> linksList = Arrays.asList("https://www.automationpractice.pl/index.php",
                "https://zoo.waw.pl/", "https://www.w3schools.com/",
                "https://www.clickspeedtester.com/click-counter/", "https://andersenlab.com/");
        WindowManager.TabsOpener(linksList);
        WindowManager.WindowSwitchAndClose("zoo");
    }
}