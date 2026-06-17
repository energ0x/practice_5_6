package balls;

import main.java.balls.lib.StdDraw;
import java.util.Random;

public class BouncingParticles {
    public static final int N = 20;
    public static final int RADIUS = 25;
    public static final int MAX_SPEED = 10;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int MASS = 1;

    public static void main(String[] args) {
        Random rand = new Random();
        Particle[] particles = new Particle[N];
        CollisionSystem cs = new CollisionSystem(particles);

        for (int i = 0; i < N; i++) {
            particles[i] = new Particle(rand.nextInt((int) (WIDTH - 2 * RADIUS)) + RADIUS,
                    rand.nextInt((int) (HEIGHT - 2 * RADIUS)) + RADIUS,
                    rand.nextDouble(-0.5, 0.5) * rand.nextInt(MAX_SPEED) + 1,
                    rand.nextDouble(-0.5, 0.5) * rand.nextInt(MAX_SPEED) + 1,
                    RADIUS, MASS);
        }
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);

        while(true) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(particles[i].timeToHit(particles[j]) < 0.1) {
                        particles[i].bounceOff(particles[j]);
                    }
                }
                if(particles[i].timeToHitHorizontalWall() < 0.1) {
                    particles[i].bounceOffHorizontalWall();
                }
                if(particles[i].timeToHitVerticalWall() < 0.1) {
                    particles[i].bounceOffVerticalWall();
                }
            }
            StdDraw.clear();

            for(Particle p : particles) {
                p.move(0.5);
                p.draw();
            }
            StdDraw.show(1);
        }
    }
}