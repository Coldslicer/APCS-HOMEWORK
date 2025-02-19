public class Test
{
    public static void main(String[] args) {
        for (int i = 0; i > -80; i--) {
            System.out.printf("input: %f, %f, %f\noutput:%f\n",0.5f,-80f-i,80f,modifyPower(0.5, -80-i, 80));
        }
        for (int i = 0; i < 80; i++) {
            System.out.printf("input: %f, %f, %f\noutput:%f\n",0.5f,80f-i,80f,modifyPower(0.5, 80-i, 80));
        }
    }
    private static double modifyPower(double value, double currentPosition, double raisedPosition) {
		currentPosition = Math.abs(currentPosition);
		raisedPosition = Math.abs(raisedPosition);
		if (currentPosition >= raisedPosition) return 0;
		if (currentPosition >= 9*raisedPosition/10) return 0.3*value;
		if (currentPosition >= 4*raisedPosition/5) return 0.5*value;
		if (currentPosition >= 2*raisedPosition/3) return 0.7*value;
		return value;
	}
}
