package balls;

public class Event implements Comparable<Event> {
    public double time; // time of event
    public Particle a, b; // particles involved in event
    private int countA = 0, countB = 0; // collision counts for a and b

    public Event(double t, Particle a, Particle b) {
        this.time = t;
        this.a = a;
        this.b = b;
        if(a != null) this.countA = a.getCount();
        if(b != null) this.countB = b.getCount();
    }

    public int compareTo(Event that) {
        return (int) (this.time - that.time);
    }

    public boolean isValid() {
        if (countA != 0 && countB != 0) {
            return false;
        }
        return true;
    }
}