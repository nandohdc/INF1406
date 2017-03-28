/**
 * Created by nando on 3/25/2017.
 */
public class Philosopher implements Runnable {

    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;
    private volatile String state;
    private Philosopher neighborLeft;
    private Philosopher neighborRight;

    public Philosopher(int newId, Fork newLeftFork, Fork newRightFork){
        this.id = newId;
        this.leftFork = newLeftFork;
        this.rightFork = newRightFork;
        this.state = "HUNGRY";
        System.out.println("Philosopher #"+ this.id+ ": " + "My number is " + this.id + " and My Forks are " + this.leftFork.getStringFork() + " "+ this.rightFork.getStringFork());
    }
    public void initializingNeighbors(Philosopher Left, Philosopher Right){
        this.neighborLeft = Left;
        this.neighborRight = Right;
    }

    public String getState(){
        return this.state;
    }

    private void setState(String newState){
        this.state = newState;
    }

    private int getIdPhilosopher(){
        return this.id;
    }

    private String getNamePhilosopher(){
        return "Philosopher #" + getIdPhilosopher();
    }

    @Override
    public void run() {

        System.out.println(getNamePhilosopher() + " is Running");
        while(true) {
            this.think();
            this.take_forks();
            eat();
            this.put_forks();
        }
    }

    private void think(){
        System.out.println(getNamePhilosopher() + " is thinking about his life.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eat(){
        System.out.println(getNamePhilosopher() + " is eating.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkForks(Philosopher p){
        if(p.getState().equals("HUNGRY") && !p.neighborLeft.getState().equals("EATING") && !p.neighborRight.getState().equals("EATING")){
            p.leftFork.pickUp(p.getNamePhilosopher());
            p.rightFork.pickUp(p.getNamePhilosopher());
            p.setState("EATING");
        }
    }

    private void take_forks(){
        this.setState("HUNGRY");
        this.checkForks(this);
    }

    private void put_forks(){
        this.setState("THINKING");
        this.checkForks(this.neighborLeft);
        this.checkForks(this.neighborRight);
        this.leftFork.putDown(this.getNamePhilosopher());
        this.rightFork.putDown(this.getNamePhilosopher());
    }
}
