/**
 * Created by Fernando Homem da Costa on 3/25/2017.
 * Dinning Philosophers enforcing deadlock
 */
package dinning;
public class Fork {

    private final int id;

    private volatile boolean using; // Not using: False, using: true

    public Fork(int newId){
        id = newId;
        using = false;
        System.out.println("Fork #"+ this.id+ " has benn created." );
    }

    private int getIdFork(){
        return this.id;
    }

    public String getStringFork(){
        return "Fork#" + this.getIdFork();
    }

    private boolean getUsing(){
        return this.using;
    }

    private void changeUsing(boolean newState){
        this.using = newState;
    }


    public boolean pickUp(){
        if(!this.getUsing()) {
            this.changeUsing(!getUsing());
            return true;
        }
        else
            return false;
    }

    public void putDown(String Philosopher){
        System.out.println(Philosopher + " is puttin down the " + getStringFork());
        this.changeUsing(!getUsing());
    }
}
