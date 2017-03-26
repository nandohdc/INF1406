/**
 * Created by nando on 3/25/2017.
 */

public class main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        final int NO_OF_FORKS = 10;
        final int NO_OF_PHILOSOPHERS = 5;


        fork[] forks = new fork[NO_OF_FORKS];
        Philosopher[] Philosophers = new Philosopher[NO_OF_PHILOSOPHERS];
        Thread[] Threads = new Thread[NO_OF_PHILOSOPHERS];

        for(int i = 0; i < NO_OF_PHILOSOPHERS; i++){
            for(int j = 0 ; j < NO_OF_FORKS; j++) {
                forks[j] = new fork(j);
            }

            Philosophers[i] = new Philosopher(i, forks[i], forks[i+1]);
        }

        for(int k = 0 ; k < NO_OF_PHILOSOPHERS; k++){
            Threads[k] = new Thread(Philosophers[k]);
            Threads[k].start();
        }

    }

}