public abstract class Stage {
    protected int length;

    public Stage(int length) {
        if (length <= 0) {
            throw new RuntimeException("Указана недопустимая длина препятствия");
        }
        this.length = length;
    }

    public abstract void go(Car c);
}
