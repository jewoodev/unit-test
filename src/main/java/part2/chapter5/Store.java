package part2.chapter5;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private final Map<Product, Integer> inventory;

    public Store() {
        this.inventory = new HashMap<>();
    }

    public boolean hasEnoughInventory(Product product, int num) {
        Integer getNum = this.inventory.get(product);

        return getNum != null && getNum >= num;
    }

    public void removeInventory(Product product, int num) {
        this.inventory.put(product, this.inventory.get(product) - num);
    }
}
