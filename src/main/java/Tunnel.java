import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private final Semaphore semaphore;

    public Tunnel(int length, int bandwidth) {
        super(length);
        if (bandwidth < 1) {
            throw new RuntimeException("Указано недопустимое значение пропускной способность тунеля");
        }
        semaphore = new Semaphore(bandwidth);
    }

    @Override
    public void go(Car car) {
        try {
            try {
                System.out.println(car + " готовится к этапу(ждет): " + this);
                semaphore.acquire(); // ждём двоих (в тонеле только двое)
                System.out.println(car + " начал этап: " + this);
                Thread.sleep(length / car.getSpeed() * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(car + " закончил этап: " + this);
                semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Тоннель " + length + " метров";
    }
}
