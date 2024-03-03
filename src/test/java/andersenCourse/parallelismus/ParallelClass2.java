package andersenCourse.parallelismus;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ParallelClass2 {
    @Test
    static void parallel6() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

    @Test
    static void parallel7() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

    @Test
    static void parallel8() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

    @Test
    static void parallel9() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

    @Test
    static void parallel10() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }
}
