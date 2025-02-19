import java.util.Scanner;

/*
 * Sharvil Phadke
 * Sun Oct 20 2024
 * Working: yes
 * 
 * HW question:
 * does a for loop need to have int i = 0?
 * where are some situations where you may need a different first statement
 * which statements in the for loop are optional? Why?
 * 
 */
public class P5_Phadke_Sharvil_LoanTable {
    public static void main(String[] args) {
        P5_Phadke_Sharvil_LoanTable t = new P5_Phadke_Sharvil_LoanTable();
        t.getInput();
        t.printOutput();
    }
    private double p;
    private double lowRate;
    private double highRate;
    private int years;
    
    public void getInput() {
        Scanner s = new Scanner(System.in);
        System.out.print("Principal: $");
        p = s.nextDouble();
        s.nextLine();
        System.out.print("Low rate: ");
        lowRate = s.nextDouble();
        s.nextLine();
        System.out.print("High rate: ");
        highRate = s.nextDouble();
        s.nextLine();
        System.out.print("Years: ");
        years = s.nextInt();
        s.nextLine();
    }
    /*
    (p*k*c)/(c-1)
    k = rate/12.0
    n = years*12
    c = Math.pow(1+k,n);
     */
    public void printOutput() {
        System.out.printf("%-26s%-26s\n","Annual Interest Rate","Monthly Payment");
        for (double r = lowRate; r <= highRate; r += 0.25) {
            int n = years*12;
            double k = r/1200.0;
            double c = Math.pow(1+k,n);
            System.out.printf("%-26.2f%-26.2f\n",r,(p*k*c)/(c-1));
        }
        for(;;)return;
    }
}
