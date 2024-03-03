package andersenCourse.priority;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class Priority1 {
    @Test(dependsOnMethods = "b")
    public void a() {
        assertTrue(true);
    }

    @Test(dependsOnMethods = "c")
    public void b() {
        assertTrue(true);
    }

    @Test(dependsOnMethods = "d")
    public void c() {
        assertTrue(true);
    }

    @Test(dependsOnMethods = "e")
    public void d() {
        assertTrue(true);
    }

    @Test(dependsOnMethods = "f")
    public void e() {
        assertTrue(true);
    }

    @Test(dependsOnMethods = "g")
    public void f() {
        assertTrue(true);
    }

    @Test
    public void g() {
        assertTrue(true);
    }
}
