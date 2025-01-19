public class Customer {
    private String name;
    private int money;

    public Customer(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    
    public static void main(String[] args) {
        // Testing:
        
        Customer john = new Customer("John Smith", 100); // instantiates a Customer
        System.out.println(john.getName()); // test calling name
        System.out.println(john.getMoney()); // test calling money
        System.out.println(john); // test overridden toString method
    }

    @Override
    public String toString() {
        return ("Customer "+this.name+" has $"+this.money+".");
    }
}