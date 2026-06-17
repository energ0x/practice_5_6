package balls;

import main.java.balls.lib.StdDraw;

import java.util.Random;

public class BouncingBalls {
    public static final int N = 20;
    public static final int RADIUS = 25;
    public static final int MAX_SPEED = 10;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    public static void main(String[] args) {
        Random rand = new Random();
        Ball[] balls = new Ball[N];
        for (int i = 0; i < N; i++) {
            balls[i] = new Ball(rand.nextInt((int) (WIDTH - 2 * RADIUS)) + RADIUS,
                    rand.nextInt((int) (HEIGHT - 2 * RADIUS)) + RADIUS,
                    rand.nextDouble(-0.5, 0.5) * rand.nextInt(MAX_SPEED) + 1,
                    rand.nextDouble(-0.5, 0.5) * rand.nextInt(MAX_SPEED) + 1,
                    RADIUS);
        }
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);

        while (true) {
            StdDraw.clear();
            for (int i = 0; i < N; i++) {
                balls[i].move(0.5);
                balls[i].draw();
            }
            StdDraw.show(1);
        }
    }
}