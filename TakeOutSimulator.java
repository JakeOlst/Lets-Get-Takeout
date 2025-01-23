import java.util.Scanner;

public class TakeOutSimulator {
    private Customer customer;
    private FoodMenu menu;
    private Scanner input;

    public TakeOutSimulator(Customer customer, Scanner input) {
        this.customer = customer;
        this.menu = new FoodMenu();
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
                throw new IllegalArgumentException(
                        "Invalid Selection: " + promptChoice + ". Please enter a valid menu item number.");
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
                throw new IllegalArgumentException("Invalid Selection: " + promptChoice
                        + ". Enter 1 to continue shopping or Enter 0 to checkout.");
            }
        };

        return getResponse(userPrompt, userInputRetriever);
    }

    public void checkoutCustomer(ShoppingBag<Food> shoppingBag) {
        System.out.println("Processing payment...");
        this.customer.setMoney(this.customer.getMoney() - shoppingBag.getTotalPrice());
        System.out.println("Your remaining balance is: " + this.customer.getMoney());
        System.out.println("Thank you, and enjoy your food!");
    }

    public void takeOutPrompt() {
        int customerMoneyLeft = this.customer.getMoney();
        ShoppingBag<Food> shoppingBag = new ShoppingBag<Food>();
        while (true) {
            System.out.println("Your remaining money: "+customerMoneyLeft);
            Food selectedFood = getMenuSelection();
            if (customerMoneyLeft >= selectedFood.getPrice()) {
                customerMoneyLeft -= selectedFood.getPrice();
                shoppingBag.addItem(selectedFood);
            } else {
                System.out.println("You don't have enough money to buy the "+selectedFood.getName()+". You should either pick another item or check out.");
            }
            System.out.println("Are you still ordering food?");
            boolean stillOrderingFood = isStillOrderingFood();
            if (!stillOrderingFood) {
                checkoutCustomer(shoppingBag);
                break;
            }
        }
    }

    public void startTakeOutSimulator() {
        System.out.println("Welcome to my restaurant!");
        while (shouldSimulate()) {
            System.out.println("Welcome, "+this.customer.getName());
            takeOutPrompt();
        }
        System.out.println("Thank you for visiting!");
    }

}
