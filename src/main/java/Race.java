import java.util.Arrays;
import java.util.List;

public class Race {
    private final List<Stage> stages;
    private Car winner;

    public List<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stages) {
        this.stages = Arrays.asList(stages);
    }

    public synchronized void setWinner(Car winner) {
        if (this.winner == null) {
            this.winner = winner;
        }
    }

    public Car getWinner() {
        return this.winner;
    }
}
