import java.util.Scanner;

public class TakeOutSimulator {
    private Customer customer;
    private FoodMenu menu;
    private Scanner input;

    public TakeOutSimulator(Customer customer, FoodMenu menu, Scanner input) {
        this.customer = customer;
        this.menu = menu;
        this.input = input;
    }

    private <T> T getResponse(String userInputPrompt, UserInputRetriever<T> userInputRetriever) {
        while (true) {
            System.out.println(userInputPrompt);
            if (input.hasNextInt()) {
                int userInput = input.nextInt();
                try {
                    return userInputRetriever.produceOutput(userInput);
                } catch (IllegalArgumentException e) {
                    System.out.println(userInput + " is not a valid input.");
                }
            } else {
                System.out.println("Input must be an int type.");
                input.next();
            }
        }
    }

}
