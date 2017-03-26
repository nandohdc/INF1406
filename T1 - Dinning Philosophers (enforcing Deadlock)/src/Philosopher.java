/**
 * Created by nando on 3/25/2017.
 */
public class Philosopher implements Runnable {

    private final int id;
    private final fork leftFork;
    private final fork rightFork;

    public Philosopher(int newId, fork newLeftFork, fork newRightFork){
        this.id = newId;
        this.leftFork = newLeftFork;
        this.rightFork = newRightFork;
        System.out.println("Philosopher #"+ this.id+ ": " + "My number is " + this.id);
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
            if(this.take_fork()){
                this.eat();
            }
            this.put_fork();
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
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean take_fork(){
        if(this.leftFork.pickUp()){
            if(this.rightFork.pickUp()){
                return true;
            }
        }
        return false;
    }

    private void put_fork(){
        this.leftFork.putDown(getNamePhilosopher());
        this.rightFork.putDown(getNamePhilosopher());
    }
}
