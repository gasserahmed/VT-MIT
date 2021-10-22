/*

The representation of a cart is an array of ShoppingCartItem.

 */

import { ShoppingCartItem } from "./ShoppingCartItem.js";

class ShoppingCart {
  constructor() {
    this._items = []; /* Array of Shopping Cart Item */
  }

  get numberOfItems() {
    return this._items.reduce((count, item) => {
      return count + item.quantity;
    }, 0);
  }

  get empty() {
    return this._items.length <= 0;
  }

  /**
   * Returns the sum of the item's price multiplied by the quantity for all
   * items in shopping cart list. This is the total cost excluding the surcharge.
   *
   * @return the cost of all items times their quantities
   * @see ShoppingCartItem
   */
  get subtotal() {
    return this._items.reduce((amount, item) => {
      return amount + item.book.price * item.quantity;
    }, 0);
  }

  /**
   * Returns the total cost of the order for the given
   * <code>ShoppingCart</code> instance.
   *
   * @return the cost of all items times their quantities plus surcharge
   */
  get total() {
    return this.subtotal + this.surcharge;
  }

  /**
   * Returns the surcharge to be applied for items in the cart.
   *
   * @returns {number}
   */
  get surcharge() {
    return 500;
  }

  /**
   * Returns the list of <code>ShoppingCartItems</code>.
   *
   * @return the <code>items</code> list
   * @see ShoppingCartItem
   */
  get items() {
    return ShoppingCart.frozenArray(this._items);
  }

  /**
   * Empties this cart, discards all items.
   */
  clear() {
    this._items = [];
  }

  /**
   * Adds a <code>ShoppingCartItem</code> to the <code>ShoppingCart</code>'s
   * <code>items</code> list. If item of the specified <code>book</code>
   * already exists in shopping cart list, the quantity of that item is
   * incremented, and the original price remains unchanged.
   *
   * @see ShoppingCartItem
   */
  addItem(book /*: Object */, quantity = 1) {
    let existingItem = this._items.find(
      item => item.book.bookId == book.bookId
    );
    if (!existingItem) {
      let newItem = new ShoppingCartItem(book);
      newItem.quantity = quantity;
      this._items.push(newItem);
    } else {
      existingItem.quantity++;
    }
  }

  /**
   * Updates the <code>ShoppingCartItem</code> of the specified
   * <code>book</code> to the specified quantity. If '<code>0</code>'
   * is the given quantity, the <code>ShoppingCartItem</code> is removed
   * from the <code>ShoppingCart</code>'s <code>items</code> list.
   *
   * @param book the book for which to update quantity
   * @param quantity the number which the <code>ShoppingCartItem</code> is updated to
   * @see ShoppingCartItem
   */
  update(book /*: Object*/, quantity) {
    if (quantity < 0 || quantity > 99) return;

    let existingItemIndex = this._items.findIndex(
      item => item.book.bookId == book.bookId
    );
    if (existingItemIndex !== -1) {
      if (quantity !== 0) {
        this._items[existingItemIndex].quantity = quantity;
      } else {
        // remove item if quantity == 0
        this._items.splice(existingItemIndex, 1);
      }
    }
  }

  toJSON() {
    return {
      items: ShoppingCart.frozenArray(this._items)
    };
  }

  static frozenArray(inArray) {
    let tempArr = [];
    Object.keys(inArray).forEach(element => {
      tempArr.push(inArray[element]);
    });
    return Object.freeze(tempArr);
  }
}

export { ShoppingCart };
