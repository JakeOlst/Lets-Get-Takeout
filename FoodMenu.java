import java.util.List;
import java.util.ArrayList;

public class FoodMenu {
    private List<Food> menu;

    public FoodMenu() {
        this.menu = new ArrayList<Food>();
        Food burger = new Food("Cheeseburger", "A medium-rare steak burger with sliced cheddar cheese, tomatoes and pickles", 10);
        Food pizza = new Food("Pizza", "A standard cheese pizza with tomato sauce, herbs and thick crust", 8);
        Food salad = new Food("Salad", "A basic salad with lettuce, tomatoes, cucumbers, peppers and a creamy salad dressing", 6);
        this.menu.add(burger);
        this.menu.add(pizza);
        this.menu.add(salad);
    }

    public Food getFood(int index) {
        try {
            Food chosenFood = this.menu.get(index-1);
            return chosenFood;
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Food getLowestCostFood() {
        Food lowestFood = null;
        for (Food menuItem : this.menu) {
            if (lowestFood == null) {
                lowestFood = menuItem;
            } else if (menuItem.getPrice() < lowestFood.getPrice()) {
                lowestFood = menuItem;
            }
        }
        return lowestFood;
    }

    public static void main(String[] args) {
        FoodMenu foodMenu = new FoodMenu();
        System.out.println(foodMenu);
    }

    @Override
    public String toString() {
        String output = "| - - - | Food Menu | - - - |\n";
        for (int i=0; i<menu.size(); i++) {
            output += ((i+1) + ". " + menu.get(i));
            if (i+1 < menu.size()) {
                output += "\n";
            }
        }
        return output;
    }
}
