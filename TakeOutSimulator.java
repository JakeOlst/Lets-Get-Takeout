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

    public boolean shouldSimulate() {
        String userPrompt = "Enter 1 to proceed with simulation or Enter 0 to stop simulation: ";
        UserInputRetriever<Boolean> userInputRetriever = (promptChoice) -> {
            if (promptChoice == 1) {
                Food lowestPricedFood = menu.getLowestCostFood();
                if (lowestPricedFood != null && customer.getMoney() >= lowestPricedFood.getPrice()) {
                    return true;
                } else {
                    System.out.println("You don't have enough money to continue shopping!");
                    System.out.println("Ending Simulation...");
                    return false;
                }
            } else if (promptChoice == 0) {
                return false;
            } else {
                throw new IllegalArgumentException("Invalid Selection: " + promptChoice + ". Please enter 0 or 1.");
            }
        };

        return getResponse(userPrompt, userInputRetriever);
    }

    public Food getMenuSelection() {
        String userPrompt = "Choose a menu item: ";
        UserInputRetriever<Food> userInputRetriever = (promptChoice) -> {
            Food foodItem = menu.getFood(promptChoice);
            if (foodItem != null) {
                return foodItem;
            } else {
                throw new IllegalArgumentException("Invalid Selection: " + promptChoice + ". Please enter a valid menu item number.");
            }
        };

        return getResponse(userPrompt, userInputRetriever);
    }

    public boolean isStillOrderingFood() {
        String userPrompt = "Enter 1 to continue shopping or Enter 0 to checkout: ";
        UserInputRetriever<Boolean> userInputRetriever = (promptChoice) -> {
            if (promptChoice == 1) {
                return true;
            } else if (promptChoice == 0) {
                return false;
            } else {
                throw new IllegalArgumentException("Invalid Selection: " + promptChoice + ". Enter 1 to continue shopping or Enter 0 to checkout.");
            }
        };

        return getResponse(userPrompt, userInputRetriever);
    }

}
