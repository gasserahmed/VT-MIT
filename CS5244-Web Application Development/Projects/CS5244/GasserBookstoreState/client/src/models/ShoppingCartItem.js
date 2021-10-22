class ShoppingCartItem {
  constructor(book) {
    this._book = book;
    this._quantity = 1;
  }

  get book() {
    return this._book;
  }

  get quantity() {
    return this._quantity;
  }

  set quantity(value) {
    this._quantity = value;
  }

  get bookId() {
    return this._book.bookId;
  }

  get price() {
    return this._book.price;
  }

  get total() {
    return this._quantity * this.price;
  }

  increment() {
    this._quantity++;
  }

  decrement() {
    if (this._quantity > 0) {
      this._quantity--;
    }
  }

  toJSON() {
    return {
      book: this._book,
      quantity: this._quantity
    };
  }
}

export { ShoppingCartItem };
