package de.rdbht.swt.tst.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ShoppingCartTest {

  @Test
  void emptyCartHasZeroTotal() {
    assertEquals(0, new ShoppingCart().total());
  }

  @Test
  void addingItemsSumsTheirPrices() {
    ShoppingCart cart = new ShoppingCart();
    cart.addItem("Coffee", 250, 2);   // 2 x 250 = 500 cents
    cart.addItem("Cookie", 150, 1);   // 1 x 150 = 150 cents
    assertEquals(650, cart.total());
  }

  @Test
  void removingItemSubtractsItFromTotal() {
    ShoppingCart cart = new ShoppingCart();
    cart.addItem("Coffee", 250, 2);   // 500
    cart.addItem("Cookie", 150, 1);   // 150
    cart.removeItem("Coffee");
    assertEquals(150, cart.total());
  }
}
