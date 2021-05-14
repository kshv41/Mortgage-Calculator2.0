import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int principal = (int) readValue("Principal: ", 1000, 100_000_000);
        float interestRateAnnual = (float)readValue("Annual Interest Rate: ", 1, 30);
        byte period = (byte) readValue("Period in years: ", 1, 50);

        double mortgage = calculateMortgage(principal, interestRateAnnual, period);

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String formattedMortgage = currency.format(mortgage);

        System.out.println("Mortgage: " + formattedMortgage);

    }
    public static double readValue(String prompt, double min, double max ){
        double value = 0;
        Scanner scanner = new Scanner(System.in);
        while(true) {
        System.out.print(prompt);
        value = scanner.nextDouble();
        if(value>=min && value <= max) break;
        System.out.println("Please enter a value between "+ min+ " and "+ max);
        }
        return value;
    }


    public static double calculateMortgage(
            int principal,
            float interestRateAnnual,
            byte period){
        final float CONVERSION_FACTOR = 1200;
        final int MONTHS_IN_YEAR = 12;

        float interestRateMonthly = interestRateAnnual/ CONVERSION_FACTOR;
        int numberOfPayments = period* MONTHS_IN_YEAR;

        double term = Math.pow((1+ interestRateMonthly), numberOfPayments);
        double mortgage = principal * interestRateMonthly* term/(term -1);

        return mortgage;
    }
}
