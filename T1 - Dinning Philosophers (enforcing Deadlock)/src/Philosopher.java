/**
 * Created by nando on 3/25/2017.
 */
public class Philosopher implements Runnable {

    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;

    public Philosopher(int newId, Fork newLeftFork, Fork newRightFork){
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
            if(this.take_left_fork() && this.take_right_fork()){
                this.eat();
                this.put_left_fork();
                this.put_right_fork();
            }
        }
    }

    private void think(){

        System.out.println(getNamePhilosopher() + " is thinking about his life.");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eat(){

        System.out.println(getNamePhilosopher() + " is eating.");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean take_left_fork(){
        if(this.leftFork.pickUp()){
            System.out.println(this.getNamePhilosopher() + " took " + this.leftFork.getStringFork());
            return true;
        } else{
            System.out.println(this.getNamePhilosopher() + " couldn't take the " + this.leftFork.getStringFork());
            return false;
        }
    }

    private boolean take_right_fork(){
        if(this.rightFork.pickUp()){
            System.out.println(this.getNamePhilosopher() + " took " + this.leftFork.getStringFork());
            return true;
        } else{
            System.out.println(this.getNamePhilosopher() + " couldn't take the " + this.rightFork.getStringFork());
            return false;
        }
    }


    private void put_left_fork(){
        this.leftFork.putDown(getNamePhilosopher());
    }

    private void put_right_fork(){
        this.rightFork.putDown(getNamePhilosopher());
    }

}
