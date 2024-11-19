package guru.springframework.lombokbestpractices;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LombokBestPracticesApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test2() {
        assertEquals(1, 1);
    }

    @Test
    void test3() {
        assertEquals(1, 1);
    }
}
