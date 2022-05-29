import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int riderNumber;
    private final Race race;
    private final int speed;
    private final CyclicBarrier prepareStart;
    private final CountDownLatch waitingFinish;
    private final String name;

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier prepareStart, CountDownLatch waitingFinish) {
        this.race = race;
        this.speed = speed;
        this.prepareStart = prepareStart;
        this.waitingFinish = waitingFinish;
        riderNumber++;
        this.name = "Участник #" + riderNumber;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            prepareStart.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        waitingFinish.countDown();
        race.setWinner(this);
    }
}
