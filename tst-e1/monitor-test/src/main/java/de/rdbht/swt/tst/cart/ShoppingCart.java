package de.rdbht.swt.tst.cart;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple shopping cart. Prices are handled in integer cents to avoid floating
 * point money bugs; only the final discount step rounds to the nearest cent.
 */
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

  /** Total in cents after the configured discount. */
  public int total() {
    return applyDiscount(gross());
  }

  private int gross() {
    return items.stream().mapToInt(LineItem::subtotal).sum();
  }

  private int applyDiscount(int amount) {
    return (int) Math.round(amount * (100 - discountPercent) / 100.0);
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
