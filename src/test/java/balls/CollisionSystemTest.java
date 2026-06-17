package balls;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Tag("slow")
class CollisionSystemTest {

    @Test
    void testSimulationInitialization() {
        Particle[] particles = new Particle[2];
        particles[0] = new Particle(10, 10, 1, 1, 5, 1);
        particles[1] = new Particle(20, 20, -1, -1, 5, 1);
        CollisionSystem cs = new CollisionSystem(particles);

        assertNotNull(cs, "Система має успішно ініціалізуватися масивом частинок");
    }
}