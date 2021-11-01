<template>
  <div class="cart-page content-container">
    <section
      v-if="$store.state.cart.numberOfItems === 0"
      class="empty-cart-page"
    >
      <span class="empty-cart-text">Your cart is currently empty!</span>
      <div class="cart-buttons">
        <router-link
          :to="
            '/category/' +
            ($store.state.selectedCategoryName === ''
              ? $store.state.defaultCategoryName
              : $store.state.selectedCategoryName)
          "
          class="button secondary-button"
          tag="button"
        >
          Continue Shopping
        </router-link>
      </div>
    </section>
    <section v-else class="non-empty-cart-page">
      <div class="cart-page-title-container">
        <h2 class="cart-title">Here's What You're Getting!</h2>
        <div class="row-separator"></div>
        <div class="cart-description">
          You have {{ $store.state.cart.numberOfItems }}
          <span v-if="$store.state.cart.numberOfItems == 1">item</span>
          <span v-else>items</span>
          in your cart.
        </div>
      </div>
      <cart-table></cart-table>
      <div class="cart-footer">
        <button class="button clear-button" @click="clearCart">
          Clear the Cart
        </button>
        <div class="cart-total">
          <span class="subtotal"
            >Subtotal:
            {{ $store.state.cart.subtotal | asDollarsAndCents }}</span
          >
        </div>
      </div>
      <div class="cart-buttons">
        <router-link
          :to="
            '/category/' +
            ($store.state.selectedCategoryName === ''
              ? $store.state.defaultCategoryName
              : $store.state.selectedCategoryName)
          "
          class="button secondary-button"
          tag="button"
        >
          Continue Shopping
        </router-link>
        <router-link to="/checkout" class="button" tag="button">
          Proceed to Checkout
        </router-link>
      </div>
    </section>
  </div>
</template>

<script>
import CartTable from "@/components/CartTable";
export default {
  name: "Cart",
  components: { CartTable },
  methods: {
    clearCart() {
      this.$store.dispatch("clearCart");
    },
  },
};
</script>

<style scoped>
.non-empty-cart-page,
.empty-cart-page {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding: 1em 5em 2.5em 5em;
}

.cart-page-title-container {
  background: var(--secondary-background-color);
}

.cart-title,
.cart-description {
  color: var(--neutral-color-dark);
  padding: 1em;
}

.cart-description {
  font-weight: 100;
  font-size: 1.2em;
}

.cart-buttons,
.cart-footer {
  display: flex;
  background-color: var(--secondary-background-color);
  justify-content: space-between;
  padding: 1em;
}

.cart-buttons {
  padding: 2em 1em;
}

.clear-button {
  align-self: start;
  color: dodgerblue;
  background: transparent;
  box-shadow: none;
}

.clear-button:hover {
  background: transparent;
  box-shadow: none;
  color: darkblue;
  text-decoration: underline;
}

.cart-footer {
  margin-bottom: 2px;
  padding-right: 1.5em;
}

.cart-total {
  display: flex;
  flex-direction: column;
  font-weight: bold;
  font-size: 1.2rem;
  color: var(--neutral-color-dark);
  padding-right: 5px;
  text-align: right;
}

.empty-cart-text {
  padding: 1em;
  background: var(--secondary-background-color);
  text-align: center;
  font-size: 1.5rem;
  font-weight: bold;
  color: var(--neutral-color-dark);
}
.empty-cart-page .cart-buttons {
  justify-content: center;
  padding: 0 2em 2em 2em;
}
</style>
