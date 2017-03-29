/**
 * Created by Fernando Homem da Costa on 3/25/2017.
 * Dinning Philosophers Solutuion
 */

package dinning;

public class Fork {

    private final int id;

    private volatile boolean using; // Not using: False, using: true

    public Fork (int newId){
        this.id = newId;
        this.using = false;
        System.out.println("Fork #"+ this.id+ " has benn created." );
    }

    private int getIdFork(){
        return this.id;
    }

    public String getStringFork(){
        return "Fork#" + this.getIdFork();
    }

    public boolean getUsing(){
        return this.using;
    }

    private synchronized void changeUsing(boolean newState){
        this.using = newState;
    }


    public synchronized boolean pickUp(String Philosopher){
        if(!this.getUsing()) {
            System.out.println(Philosopher + " is picking up the " + getStringFork());
            this.changeUsing(true);
            return true;
        }
        else
            System.out.println(Philosopher + " couldn't take the " + getStringFork());
        	return false;
    }

    public synchronized void putDown(String Philosopher){
        System.out.println(Philosopher + " is puttin down the " + getStringFork());
        this.changeUsing(false);
    }
}