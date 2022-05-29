public class Road extends Stage {
    public Road(int length) {
        super(length);
    }

    @Override
    public void go(Car car) {
        try {
            System.out.println(car + " начал этап: " + this);
            Thread.sleep(length / car.getSpeed() * 1000L);
            System.out.println(car + " закончил этап: " + this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Дорога " + length + " метров";
    }
}

