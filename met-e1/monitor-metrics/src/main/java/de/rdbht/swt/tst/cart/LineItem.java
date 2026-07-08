package de.rdbht.swt.tst.cart;

/** One position in the cart. */
record LineItem(String name, int unitPriceCents, int quantity) {
  int subtotal() {
    return unitPriceCents * quantity;
  }
}
