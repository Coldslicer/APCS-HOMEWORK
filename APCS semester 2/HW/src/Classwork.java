import java.util.function.Function;

public class Classwork {
    public static void main(String[] args) {
        System.out.println(
            findRoot(
                (x) -> Math.sin(x/3) + Math.cos(x/2),
                2,
                4
            )
        );
        System.out.println(Math.PI);
    }

    public static final double MARGIN = 0.00000000000001;

    public static double findRoot(Function<Double,Double> f, double low, double high) {
        while (true) {
            double x = (high + low) / 2;
            double y = f.apply(x);
            System.out.printf("(%f, %f)\n", x, y);
            if (y == 0 || high - low < MARGIN) return x;
            if (y > 0) low = x;
            else if (y < 0) high = x;
        }
    }
}
