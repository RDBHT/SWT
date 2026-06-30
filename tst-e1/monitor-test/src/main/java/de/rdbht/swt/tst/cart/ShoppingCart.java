package de.rdbht.swt.tst.cart;

import java.util.ArrayList;
import java.util.List;

/** A simple shopping cart. Prices are handled in integer cents. */
public class ShoppingCart {

  private final List<String> names = new ArrayList<>();
  private final List<Integer> unitPrices = new ArrayList<>();
  private final List<Integer> quantities = new ArrayList<>();

  public void addItem(String name, int unitPriceCents, int quantity) {
    names.add(name);
    unitPrices.add(unitPriceCents);
    quantities.add(quantity);
  }

  public int total() {
    int sum = 0;
    for (int i = 0; i < names.size(); i++) {
      sum += unitPrices.get(i) * quantities.get(i);
    }
    return sum;
  }
}
