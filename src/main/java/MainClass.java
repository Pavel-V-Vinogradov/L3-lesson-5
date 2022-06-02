import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MainClass {
    private static final int CARS_COUNT = 4;

    public static void main(String[] args) {

        final CyclicBarrier prepareStart = new CyclicBarrier(CARS_COUNT + 1);
        final CountDownLatch waitingFinish = new CountDownLatch(CARS_COUNT);

        final Race race = new Race(new Road(60), new Tunnel(80, 2), new Road(40));
        final Car[] cars = new Car[CARS_COUNT];

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Arrays.setAll(cars, i -> new Car(race, 20 + (int) (Math.random() * 10), prepareStart, waitingFinish));

        Arrays.stream(cars).forEach(car -> new Thread(car).start());
        try {
            prepareStart.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

            waitingFinish.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

            System.out.printf("ПОБЕДИТЕЛЬ >>> %s", race.getWinner());

        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}

