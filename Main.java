import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);

    System.out.print("Hello! Please provide your name: ");
    String customerName = input.nextLine();

    int money = 0;
    
    while (true) {
      try {
        System.out.print("Please enter your starting money: $");
        money = input.nextInt();
        break;
      } catch (InputMismatchException e) {
        System.out.println("Invalid integer input: '"+e+"'. Please try again.");
        input.nextLine();
      }
    }

    Customer customer = new Customer(customerName, money);
    TakeOutSimulator takeOutSimulator = new TakeOutSimulator(customer, input);
    takeOutSimulator.startTakeOutSimulator();
  }    
}
