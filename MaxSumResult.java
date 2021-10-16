package cs146F21.hatch.project2;

/**
 * A class to show the maximum contiguous sum, starting index, and ending index of an array
 */
public class MaxSumResult {
    private final int sum;
    private final int arrive;
    private final int depart;

    public MaxSumResult(int sum, int arrive, int depart) {
        this.sum = sum;
        this.arrive = arrive;
        if(sum < 0) {
            this.depart = -1;
        } else {
            this.depart = depart;
        }
    }

    public int getSum() {
        return sum;
    }

    public int getArrive() {
        return arrive;
    }

    public int getDepart() {
        return depart;
    }

    @Override
    public String toString() {
        return "MaxSumResult{" +
                "sum=" + sum +
                ", arrive=" + arrive +
                ", depart=" + depart +
                '}';
    }
}
