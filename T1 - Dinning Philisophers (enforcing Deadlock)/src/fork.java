/**
 * Created by nando on 3/26/2017.
 */
public class fork {

    private final int id;

    private static boolean using; // Not using: False, using: true

    public fork (int newId){
        id = newId;
        using = false;
        System.out.println("Fork #"+ this.id+ " has benn created." );
    }

    private int getIdFork(){
        return this.id;
    }

    private String getStringFork(){
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
