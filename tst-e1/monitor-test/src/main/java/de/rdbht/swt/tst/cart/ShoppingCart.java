package de.rdbht.swt.tst.cart;

import java.util.ArrayList;
import java.util.List;

/** A simple shopping cart. Prices are handled in integer cents. */
public class ShoppingCart {

  private final List<LineItem> items = new ArrayList<>();
  private int discountPercent = 0;

  public void addItem(String name, int unitPriceCents, int quantity) {
    items.add(new LineItem(name, unitPriceCents, quantity));
  }

  public void removeItem(String name) {
    items.removeIf(item -> item.name().equals(name));
  }

  public void updateQuantity(String name, int quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("quantity must be positive, was " + quantity);
    }
    int i = indexOf(name);
    LineItem it = items.get(i);
    items.set(i, new LineItem(it.name(), it.unitPriceCents(), quantity));
  }

  public void applyDiscountPercent(int percent) {
    if (percent < 0 || percent > 100) {
      throw new IllegalArgumentException("percent must be 0..100, was " + percent);
    }
    this.discountPercent = percent;
  }

  public int total() {
    int gross = items.stream().mapToInt(LineItem::subtotal).sum();
    return (int) Math.round(gross * (100 - discountPercent) / 100.0);
  }

  private int indexOf(String name) {
    for (int i = 0; i < items.size(); i++) {
      if (items.get(i).name().equals(name)) {
        return i;
      }
    }
    throw new IllegalArgumentException("no such item: " + name);
  }
}
