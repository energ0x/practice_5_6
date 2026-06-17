package balls;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@Tag("fast")
class ParticleTest {

    @Test
    @DisplayName("Перевірка ініціалізації: лічильник зіткнень має бути 0")
    void testInitialCollisionCount() {
        Particle p = new Particle(10, 10, 2, 2, 5, 1);
        assertEquals(0, p.getCount(), "При створенні об'єкта лічильник має дорівнювати 0");
    }

    @ParameterizedTest(name = "Відскок від горизонтальної стіни {0} раз(ів)")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("Лічильник зіткнень правильно збільшується після відскоків")
    void testBounceOffHorizontalWallIncreasesCount(int bounceTimes) {
        Particle p = new Particle(10, 10, 2, 2, 5, 1);
        for (int i = 0; i < bounceTimes; i++) {
            p.bounceOffHorizontalWall();
        }
        assertEquals(bounceTimes, p.getCount(), "Лічильник зіткнень не збігається з кількістю відскоків");
    }


    @ParameterizedTest(name = "Координата X={0}, Швидкість={1} -> Очікуваний час={2}")
    @CsvSource({
            "700, 10, 9.5",
            "10,  2,  392.5",
            "400, 5,  79.0"
    })
    @DisplayName("Розрахунок часу до зіткнення з вертикальною стіною")
    void testTimeToHitVerticalWall(double rx, double vx, double expectedTime) {

        assumeTrue(vx != 0, "Для розрахунку часу до стіни швидкість по осі X не може бути нульовою");
        assumeTrue(rx >= 0 && rx <= BouncingParticles.WIDTH, "Частинка повинна знаходитись в межах екрану");

        Particle p = new Particle(rx, 50, vx, 0, 5, 1);
        assertEquals(expectedTime, p.timeToHitVerticalWall(), 0.001);
    }

    @TestFactory
    @DisplayName("Динамічні тести: Перевірка неможливості зіткнення розбіжних частинок")
    Collection<DynamicTest> dynamicTestsForDivergingParticles() {
        Particle p1 = new Particle(100, 100, -5, 0, 10, 1);
        Particle p2 = new Particle(200, 100, 5, 0, 10, 1);

        Particle p3 = new Particle(100, 100, 0, -5, 10, 1);
        Particle p4 = new Particle(100, 200, 0, 5, 10, 1);

        return Arrays.asList(
                dynamicTest("Розбіжність частинок по осі X (летять в різні боки)", () -> {
                    assertEquals(Double.POSITIVE_INFINITY, p1.timeToHit(p2), "Час зіткнення має бути нескінченністю");
                }),
                dynamicTest("Розбіжність частинок по осі Y (летять в різні боки)", () -> {
                    assertEquals(Double.POSITIVE_INFINITY, p3.timeToHit(p4), "Час зіткнення має бути нескінченністю");
                })
        );
    }
}