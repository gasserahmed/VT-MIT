<template>
  <div class="cart-table">
    <ul>
      <li class="cart-heading">
        <div class="cart-heading-book">Book</div>
        <div class="cart-heading-price">Price</div>
        <div class="cart-heading-quantity">Quantity</div>
        <div class="cart-heading-subtotal">Total</div>
      </li>
      <div class="row-separator header-row-separator"></div>
      <li v-for="item in $store.state.cart.items" :key="item.book.bookId">
        <div class="cart-book-image">
          <img
            :src="require('@/assets/images/books/' + item.book.bookId + '.jpg')"
            :alt="item.book.title"
            width="100px"
            height="auto"
          />
        </div>
        <div class="cart-book-title">{{ item.book.title }}</div>
        <div class="cart-book-price">
          {{ item.book.price | asDollarsAndCents }}
        </div>
        <div class="cart-book-quantity">
          <button
            class="button icon-only-button dec-button"
            @click="updateCart(item.book, item.quantity - 1)"
          >
            <i class="fas fa-minus-circle"></i>
          </button>
          <span class="row-book-quantity">{{ item.quantity }}</span>
          <button
            class="button icon-only-button inc-button"
            @click="updateCart(item.book, item.quantity + 1)"
          >
            <i class="fas fa-plus-circle"></i>
          </button>
        </div>
        <div class="subtotal">
          {{ (item.quantity * item.book.price) | asDollarsAndCents }}
        </div>
        <div class="row-separator"></div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "CartTable",
  methods: {
    updateCart(book, quantity) {
      this.$store.dispatch("updateCart", { book, quantity });
    },
  },
};
</script>

<style scoped>
.cart-table {
  display: grid;
  grid-template-columns: max-content minmax(10em, 200em) repeat(3, max-content);
  row-gap: 1em;
  background-color: var(--secondary-background-color);
  padding: 1em;
  overflow: scroll;
}

ul,
li {
  display: contents;
}

.header-row-separator {
  margin-top: -1em;
}

.cart-heading {
  color: var(--neutral-color-dark);
}

.cart-heading > * {
  height: 2.5em;
  display: flex;
  align-items: center;
}

.cart-heading-book {
  grid-column: 1 / 3;
  padding: 0 1em;
}

.cart-heading-price {
  grid-column: 3 / 4;
  padding: 0 1em;
}

.cart-heading-quantity {
  grid-column: 4 / 5;
  padding: 0 1em;
}

.cart-heading-subtotal {
  /*grid-column: -2 / -1;*/
  padding: 0 1em;
}

.cart-book-image {
  padding: 0 1em;
}

.cart-book-title {
  padding: 0 1em;
}

.cart-book-price {
  padding: 0 1em;
}

.cart-book-quantity {
  padding: 0 1em;
  display: flex;
  align-items: baseline;
}

.subtotal {
  padding: 0 1em;
}

.cart-book-quantity .icon-only-button {
  height: auto;
  color: var(--neutral-color);
}

.cart-book-quantity .icon-only-button:hover {
  height: auto;
  color: var(--neutral-color-dark);
}

.dec-button {
  color: lightgray !important;
}

.dec-button:hover {
  color: darkgrey !important;
}
</style>
