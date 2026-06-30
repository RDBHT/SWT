package de.rdbht.swt.tst.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** A simple shopping cart. Prices are handled in integer cents. */
public class ShoppingCart {

  private final List<LineItem> items = new ArrayList<>();

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
    for (int i = 0; i < items.size(); i++) {
      LineItem it = items.get(i);
      if (it.name().equals(name)) {
        items.set(i, new LineItem(it.name(), it.unitPriceCents(), quantity));
        return;
      }
    }
    throw new IllegalArgumentException("no such item: " + name);
  }

  public int total() {
    return items.stream().mapToInt(LineItem::subtotal).sum();
  }

  private Optional<LineItem> lineByName(String name) {
    return items.stream().filter(item -> item.name().equals(name)).findFirst();
  }
}
