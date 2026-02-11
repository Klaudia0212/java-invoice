package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products = new ArrayList<Product>();

    public void addProduct(Product product) {
        if (product == null){
            throw new IllegalArgumentException();
        }
        products.add(product);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity <= 0){
            throw new IllegalArgumentException();
        }

        for (int i = 1; i <= quantity; i++) {
            products.add(product);
        }
    }

    public BigDecimal getSubtotal() {
        BigDecimal sum = BigDecimal.ZERO;

        for (Product product : products) {
            BigDecimal price = product.getPrice();
            sum = sum.add(price);
        } return sum;
    }

    public BigDecimal getTax() {
        BigDecimal taxSum = BigDecimal.ZERO;

        for (Product product : products){
            BigDecimal taxPercent = product.getTaxPercent();
            BigDecimal price = product.getPrice();
            BigDecimal taxValue = price.multiply(taxPercent);

            taxSum = taxSum.add(taxValue);

        } return taxSum;
    }

    public BigDecimal getTotal() {
        BigDecimal sum = BigDecimal.ZERO;

        for (Product product : products) {
            BigDecimal price = product.getPriceWithTax();
            sum = sum.add(price);
        } return sum;
    }
}
