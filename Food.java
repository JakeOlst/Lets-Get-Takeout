public class Food implements PricedItem<Integer> {
    private String name;
    private String description;
    private int price;

    public Food(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public static void main(String[] args) {
        // Testing:
        
        Food burger = new Food("Cheeseburger", "A medium-rare steak burger with sliced cheddar cheese, tomatoes and pickles", 10); // test instantiating a Food object
        System.out.println("Name: "+burger.getName()+" Description: "+burger.getDescription()+" Price: $"+burger.getPrice()); // test getting name, description and price
        
        Food ultimateBurger = burger;
        // test setters
        ultimateBurger.setName("Ultimate Cheeseburger");
        ultimateBurger.setDescription(burger.getDescription()+". Ultimate Edition");
        ultimateBurger.setPrice(burger.getPrice()+2);

        System.out.println(ultimateBurger); // test overridden toString method 
    }

    @Override
    public String toString() {
        return (this.name+": "+this.description+". Price: $"+this.price+"!");
    }
}
