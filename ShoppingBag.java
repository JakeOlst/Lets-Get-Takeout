import java.util.HashMap;
import java.util.Map;

public class ShoppingBag<T extends PricedItem<Integer>> {
    private Map<T, Integer> shoppingBag;

    public ShoppingBag() {
        this.shoppingBag = new HashMap<>();
    }

    public void addItem(T item) {
        if (this.shoppingBag.get(item) != null) {
            int pos = this.shoppingBag.get(item);
            pos++;
            this.shoppingBag.put(item, pos);
        } else {
            this.shoppingBag.put(item, 1);
        }
    }

    public int getTotalPrice() {
        int total = 0;
        for (T item : this.shoppingBag.keySet()) {
            total += item.getPrice() * this.shoppingBag.get(item);
        }
        return total;
    }

    @Override
    public String toString() {
        String itemList = "";
        for (T item : this.shoppingBag.keySet()) {
            itemList += ("- " + this.shoppingBag.get(item) + "x " + item + "\n");
        }
        return "Items in Shopping Bag:\n" + itemList + "Total Price: $" + this.getTotalPrice() + ".";
    }
}
