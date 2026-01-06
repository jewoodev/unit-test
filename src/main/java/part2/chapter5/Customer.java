package part2.chapter5;

public class Customer {
    private final Store store;

    public Customer(Store store) {
        this.store = store;
    }

    public boolean purchase(Product product, int num) {
        boolean isEnough = this.store.hasEnoughInventory(product, num);

        if (!isEnough) return false;

        this.store.removeInventory(product, num);
        return true;
    }
}
