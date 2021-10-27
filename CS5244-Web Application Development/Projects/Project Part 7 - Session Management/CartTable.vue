<template>
  <div class="cart-table">
    <ul>
      <li class="table-heading">
        <div class="heading-book">Book</div>
        <div class="heading-price">Price / Quantity</div>
        <div class="heading-subtotal">Amount</div>
      </li>

      <template v-for="item in $store.state.cart.items">
        <li :key="item.book.bookId">
          <div class="cart-book-image">
            <img
              :src="
                require('@/assets/images/books/' + bookImageFileName(item.book))
              "
              :alt="item.book.title"
              width="100px"
              height="auto"
            />
          </div>
          <div class="cart-book-title">{{ item.book.title }}</div>
          <div class="cart-book-price">
            ${{ (item.book.price / 100).toFixed(2) }}
          </div>
          <div class="cart-book-quantity">
            <span class="quantity">{{ item.quantity }}</span
            >&nbsp;
            <button
              class="icon-button inc-button"
              @click="updateCart(item.book, item.quantity + 1)"
            >
              <i class="fas fa-plus-circle"></i>
            </button>
            <button
              class="icon-button dec-button"
              @click="updateCart(item.book, item.quantity - 1)"
            >
              <i class="fas fa-minus-circle"></i>
            </button>
          </div>
          <div class="cart-book-subtotal">$3.98</div>
        </li>
        <li class="line-sep" :key="item.book.bookId"></li>
      </template>

      <li>
        <div class="cart-book-image">
          <div class="rect narrow-rect"></div>
        </div>
        <div class="cart-book-title">Book Title</div>
        <div class="cart-book-price">$2.99</div>
        <div class="cart-book-quantity">
          <span class="quantity">2</span>&nbsp;
          <button
            class="icon-button inc-button"
            @click="updateCart(item.book, item.quantity + 1)"
          >
            <i class="fas fa-plus-circle"></i>
          </button>
          <button
            class="icon-button dec-button"
            @click="updateCart(item.book, item.quantity - 1)"
          >
            <i class="fas fa-minus-circle"></i>
          </button>
        </div>
        <div class="cart-book-subtotal">$3.98</div>
      </li>
      <li class="line-sep"></li>
      <li>
        <div class="cart-book-image">
          <img src="@/assets/images/books/dune.jpeg" alt="Dune" />
        </div>
        <div class="cart-book-title">Book Title</div>
        <div class="cart-book-price">$2.99</div>
        <div class="cart-book-quantity">
          <button
            class="icon-button dec-arrow-button"
            @click="updateCart(item.book, item.quantity - 1)"
          >
            <i class="fas fa-chevron-left"></i>
          </button>
          <span class="quantity">&nbsp;&nbsp;2&nbsp;&nbsp;</span>
          <button
            class="icon-button inc-arrow-button"
            @click="updateCart(item.book, item.quantity + 1)"
          >
            <i class="fas fa-chevron-right"></i>
          </button>
        </div>
        <div class="cart-book-subtotal">$3.98</div>
      </li>
      <li class="line-sep"></li>
      <li>
        <div class="cart-book-image">
          <div class="rect square"></div>
        </div>
        <div class="cart-book-title">
          A Very Long Book Title That Goes On and On As Though the Author Were
          Very Impressed with Themself
        </div>
        <div class="cart-book-price">$43.50</div>
        <div class="cart-book-quantity">
          <input type="number" value="12" min="0" max="20" />
        </div>
        <div class="cart-book-subtotal">$522.00</div>
      </li>
      <li class="line-sep"></li>
      <li>
        <div class="cart-book-image">
          <div class="rect wide-rect"></div>
        </div>
        <div class="cart-book-title">Book Title</div>
        <div class="cart-book-price">$2.99</div>
        <div class="cart-book-quantity">
          <select id="quantity">
            <option value="0">Qty: 0</option>
            <option value="1">Qty: 1</option>
            <option value="2">Qty: 2</option>
            <option value="3">Qty: 3</option>
            <option value="4">Qty: 4</option>
            <option value="5">Qty: 5</option>
            <option value="6">Qty: 6</option>
            <option value="7">Qty: 7</option>
            <option value="8">Qty: 8</option>
            <option value="9">Qty: 9</option>
            <option value="10">Qty: 10</option>
          </select>
        </div>
        <div class="cart-book-subtotal">$3.98</div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "CartTable",
  methods: {
    bookImageFileName: function (book) {
      let name = book.title.toLowerCase();
      name = name.replace(/ /g, "-");
      name = name.replace(/'/g, "");
      console.log("Book image file name: " + name);
      return `${name}.jpeg`;
    },
    addToCart(book) {
      this.$store.dispatch("addToCart", book);
    },
    updateCart(book, quantity) {
      this.$store.dispatch("updateCart", { book, quantity });
    },
  },
};
</script>

<style scoped>
.cart-table {
  display: grid;
  grid-template-columns: max-content minmax(10em, 20em) repeat(3, max-content);
  row-gap: 1em;
  width: fit-content;
  margin: 0 auto;
  background-color: aliceblue;
}

ul {
  display: contents;
}

ul > li {
  display: contents;
}

.table-heading {
  background-color: #333;
  color: white;
}

.table-heading > * {
  background-color: #333;
  padding: 0.5em;
}

.heading-book {
  grid-column: 1 / 3;
}

.heading-price {
  grid-column: 3 / 5;
  text-align: right;
}

.heading-subtotal {
  text-align: right;
  grid-column: -2 / -1;
}

.cart-book-image {
  padding: 0 1em;
}

.cart-book-image > * {
  margin-left: auto;
  margin-right: 0;
}

img {
  display: block;
  width: 100px;
  height: auto;
}

.rect {
  background-color: var(--primary-color-dark);
}

.narrow-rect {
  width: 75px;
  height: 100px;
}

.square {
  width: 100px;
  height: 100px;
}

.wide-rect {
  width: 125px;
  height: 100px;
}

.cart-book-price {
  padding-left: 1em;
  text-align: right;
}

.cart-book-quantity {
  padding-left: 1em;
  padding-right: 1em;
}

.cart-book-subtotal {
  text-align: right;
  padding-left: 1em;
  padding-right: 1em;
}

/* Row separators in the table */

.line-sep {
  display: block;
  height: 2px;
  background-color: gray;
  grid-column: 1 / -1;
}

/* Increment/decrement buttons */

.icon-button {
  border: none;
  cursor: pointer;
}

.inc-button {
  font-size: 1.125rem;
  color: var(--primary-color);
  margin-right: 0.25em;
}

.inc-button:hover {
  color: var(--primary-color-dark);
}

.dec-button {
  font-size: 1.125rem;
  color: #ccc;
}

.dec-button:hover {
  color: #aaa;
}

/* Chevron buttons */

.dec-arrow-button,
.inc-arrow-button {
  font-size: 1rem;
  color: var(--primary-color);
}

.dec-arrow-button:hover,
.inc-arrow-button:hover {
  color: var(--primary-color-dark);
}

input[type="number"] {
  width: 4em;
}

select {
  background-color: var(--primary-color);
  color: white;
  border: 2px solid var(--primary-color-dark);
  border-radius: 3px;
}
</style>
