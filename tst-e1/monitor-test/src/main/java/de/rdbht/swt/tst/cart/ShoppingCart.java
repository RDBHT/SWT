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

  public int total() {
    return items.stream().mapToInt(LineItem::subtotal).sum();
  }

  /** Looks up the first line with the given name; basis for later operations. */
  private Optional<LineItem> lineByName(String name) {
    return items.stream().filter(item -> item.name().equals(name)).findFirst();
  }
}
