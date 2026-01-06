package part2.chapter5.domain;

public class Customer {
    private final Store store;
    private String emailAddress;

    public Customer(Store store, String emailAddress) {
        this.store = store;
        this.emailAddress = emailAddress;
    }

    public boolean purchase(Product product, int num) {
        boolean isEnough = this.store.hasEnoughInventory(product, num);

        if (!isEnough) return false;

        this.store.removeInventory(product, num);
        return true;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
