/**
 * Created by Fernando Homem da Costa on 3/25/2017.
 * Dinning Philosophers Solutuion
 */

package dinning;

public class Main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        final int NO_OF_FORKS = 5;
        final int NO_OF_PHILOSOPHERS = 5;


        Fork[] forks = new Fork[NO_OF_FORKS];
        Philosopher[] Philosophers = new Philosopher[NO_OF_PHILOSOPHERS];
        Thread[] Threads = new Thread[NO_OF_PHILOSOPHERS];

        for(int j = 0 ; j < NO_OF_FORKS; j++) {
            forks[j] = new Fork(j);
        }

        for(int i = 0 ; i < NO_OF_PHILOSOPHERS ; i++){
            if(i == (NO_OF_PHILOSOPHERS -1)){
                Philosophers[i] = new Philosopher(i, forks[i], forks[0]);
                continue;
            }
            Philosophers[i] = new Philosopher(i, forks[i], forks[i+1]);
        }

        Philosophers[0].initializingNeighbors(Philosophers[4], Philosophers[1]);
        Philosophers[1].initializingNeighbors(Philosophers[0], Philosophers[2]);
        Philosophers[2].initializingNeighbors(Philosophers[1], Philosophers[3]);
        Philosophers[3].initializingNeighbors(Philosophers[2], Philosophers[4]);
        Philosophers[4].initializingNeighbors(Philosophers[3], Philosophers[0]);

        for(int k = 0 ; k < NO_OF_PHILOSOPHERS; k++){
            Threads[k] = new Thread(Philosophers[k]);
            Threads[k].start();
        }

    }
}

