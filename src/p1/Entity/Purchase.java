package p1.Entity;

import p1.utils.PriceFormatter;

public class Purchase {
    private Product product;
    private int quantity;

    public Purchase(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return product.getName() + " x " + quantity + " | " + PriceFormatter.format(product.getPrice() * quantity);
    }
}
