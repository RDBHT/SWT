package de.rdbht.swt.tst.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

  @Test
  void updateQuantityRecomputesTotal() {
    ShoppingCart cart = new ShoppingCart();
    cart.addItem("Coffee", 250, 2);   // 500
    cart.updateQuantity("Coffee", 5);  // 1250
    assertEquals(1250, cart.total());
  }

  @Test
  void updateQuantityRejectsNonPositive() {
    ShoppingCart cart = new ShoppingCart();
    cart.addItem("Coffee", 250, 2);
    assertThrows(IllegalArgumentException.class, () -> cart.updateQuantity("Coffee", 0));
  }

  @Test
  void updateQuantityRejectsUnknownItem() {
    assertThrows(IllegalArgumentException.class,
        () -> new ShoppingCart().updateQuantity("Ghost", 3));
  }

  @Test
  void discountReducesTotal() {
    ShoppingCart cart = new ShoppingCart();
    cart.addItem("Coffee", 250, 2);   // 500
    cart.addItem("Cookie", 150, 1);   // 150  -> gross 650
    cart.applyDiscountPercent(10);
    assertEquals(585, cart.total());   // 650 - 10%
  }

  @Test
  void fullDiscountMakesCartFree() {
    ShoppingCart cart = new ShoppingCart();
    cart.addItem("Coffee", 250, 2);
    cart.applyDiscountPercent(100);
    assertEquals(0, cart.total());
  }

  @Test
  void discountRejectsOutOfRange() {
    assertThrows(IllegalArgumentException.class,
        () -> new ShoppingCart().applyDiscountPercent(101));
  }
}
