package balls;

import main.java.balls.lib.StdDraw;
import java.awt.Color;

import java.util.Random;

public class Particle {
    private Color color;
    private double rx, ry; // position
    private double vx, vy; // velocity
    private final double radius; // radius
    private final double mass; // mass
    private int count; // number of collisions

    public Particle(double rx, double ry, double vx, double vy, double radius, double mass) {
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
        this.mass = mass;
        this.count = 0;
    }

    public void move(double dt) {
        rx += vx * dt;
        ry += vy * dt;
    }

    public void draw() {
        StdDraw.setPenColor(color); // comment to make black balls
        StdDraw.filledCircle(rx, ry, radius);
    }

    public int getCount() {
        return this.count;
    }

    private void changeColor() {
        Random rand = new Random();
        this.color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    }

    public double timeToHit(Particle that) {
        if (this == that) {
            return Double.POSITIVE_INFINITY;
        }
        double dx = that.rx - this.rx, dy = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        if (dvdr > 0) {
            return Double.POSITIVE_INFINITY;
        }
        double dvdv = dvx * dvx + dvy * dvy;
        double drdr = dx * dx + dy * dy;
        double sigma = this.radius + that.radius;
        double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
        if (d < 0) {
            return Double.POSITIVE_INFINITY;
        }
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    public double timeToHitVerticalWall() {
        if (this.vx > 0) {
            return (BouncingParticles.WIDTH - this.radius - this.rx) / this.vx;
        } else {
            return (this.radius + this.rx) / (-this.vx);
        }
    }

    public double timeToHitHorizontalWall() {
        if (this.vy > 0) {
            return (BouncingParticles.HEIGHT - this.radius - this.ry) / this.vy;
        } else {
            return (this.radius + this.ry) / (-this.vy);
        }
    }

    public void bounceOff(Particle that) {
        double dx = that.rx - this.rx, dy = that.ry - this.ry;
        double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        double dist = this.radius + that.radius;
        double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
        double Jx = J * dx / dist;
        double Jy = J * dy / dist;
        this.vx += Jx / this.mass;
        this.vy += Jy / this.mass;
        that.vx -= Jx / that.mass;
        that.vy -= Jy / that.mass;
        this.count++;
        that.count++;
        this.changeColor();
        that.changeColor();
    }

    public void bounceOffVerticalWall() {
        this.vx = -this.vx;
        this.changeColor();
        this.count++; //пофіксив це після тестів
    }

    public void bounceOffHorizontalWall() {
        this.vy = -this.vy;
        this.changeColor();
        this.count++; //пофіксив це після тестів
    }
}