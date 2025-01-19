import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    // test instantiating a customer
    Customer john = new Customer("John Smith",100);

    // test instantiating a customer
    Food burger = new Food("Cheeseburger", "A medium-rare steak burger with sliced cheddar cheese, tomatoes and pickles", 10);
    
    // print out Food and Customer objects using toString overridden method
    //System.out.println(john);
    //System.out.println(burger);

    ShoppingBag shoppingBag = new ShoppingBag();

    shoppingBag.addItem(burger);
    shoppingBag.addItem(burger);
    shoppingBag.addItem(burger);

    System.out.println(shoppingBag);
  }    
}
