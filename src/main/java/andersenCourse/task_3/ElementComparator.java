package andersenCourse.task_3;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class ElementComparator {
    public static void compareElements(WebElement element1, WebElement element2) {
        compareVertical(element1, element2);
        compareHorizontal(element1, element2);
        compareArea(element1, element2);
    }

    private static void compareVertical(WebElement firstElement, WebElement secondElement) {
        Point firstElemPosition = firstElement.getLocation();
        Point secondElemPosition = secondElement.getLocation();
        if (firstElemPosition.getY() < secondElemPosition.getY()) {
            System.out.println("First element is higher than second!");
        } else if (firstElemPosition.getY() > secondElemPosition.getY()) {
            System.out.println("Second element is higher than first!");
        } else {
            System.out.println("Elements on same level!");
        }
    }

    private static void compareHorizontal(WebElement firstElement, WebElement secondElement) {
        Point firstElemPosition = firstElement.getLocation();
        Point secondElemPosition = secondElement.getLocation();
        if (firstElemPosition.getX() < secondElemPosition.getX()) {
            System.out.println("First element is located to the left on the page, than the second!");
        } else if (firstElemPosition.getX() > secondElemPosition.getX()) {
            System.out.println("Second element is located to the left on the page, than the second!");
        } else {
            System.out.println("Elements on the same level!");
        }
    }
    private static void compareArea(WebElement firstElement, WebElement secondElement) {
        Dimension sizeOfFirst = firstElement.getSize();
        Dimension sizeOfSecond = secondElement.getSize();
        int area1 = sizeOfFirst.getHeight() * sizeOfFirst.getWidth();
        int area2 = sizeOfSecond.getHeight() * sizeOfSecond.getWidth();
        if (area1 > area2) {
            System.out.println("First element is larger than the second!");
        } else if (area1 < area2) {
            System.out.println("Second element is larger than the first!");
        } else {
            System.out.println("Elements have same area!");
        }
    }
}
