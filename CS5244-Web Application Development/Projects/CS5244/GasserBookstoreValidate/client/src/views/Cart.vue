<template>
  <div class="cart-page content-container">
    <EmptyCart
      v-if="$store.state.cart.empty"
      :text="'You need an item to checkout.'"
    />
    <section v-else class="non-empty-cart-page">
      <div class="page-title-container">
        <h2 class="page-title">Here's What You're Getting!</h2>
        <div class="row-separator"></div>
        <div class="page-description">
          You have {{ $store.state.cart.numberOfItems }}
          <span v-if="$store.state.cart.numberOfItems === 1">item</span>
          <span v-else>items</span>
          in your cart.
        </div>
      </div>
      <cart-table></cart-table>
      <div class="cart-footer">
        <button class="button link-like-button clear-button" @click="clearCart">
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
import EmptyCart from "@/components/EmptyCart";
export default {
  name: "Cart",
  components: { EmptyCart, CartTable },
  methods: {
    clearCart() {
      this.$store.dispatch("clearCart");
    },
  },
};
</script>

<style scoped>
.non-empty-cart-page {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding: 1em 5em 2.5em 5em;
}

.page-title-container {
  background: var(--secondary-background-color);
}

.page-title,
.page-description {
  color: var(--neutral-color-dark);
  padding: 1em;
}

.page-title {
  padding-left: 0.72em;
}

.page-description {
  font-weight: 100;
  font-size: 1.2em;
}

.cart-buttons,
.cart-footer {
  display: flex;
  background-color: var(--secondary-background-color);
  justify-content: space-between;
  padding: 1em;
  align-items: center;
}

.cart-buttons {
  padding: 2em 1em;
}

.clear-button {
  align-self: start;
}

.cart-footer {
  margin-bottom: 2px;
  padding-right: 1.5em;
  padding-top: 0;
}

.cart-total {
  display: flex;
  flex-direction: column;
  font-weight: 500;
  font-size: 1.2rem;
  color: var(--neutral-color-dark);
  padding-right: 5px;
  text-align: right;
}

@media (max-width: 750px) {
  .page-title-container {
    text-align: center;
  }

  .non-empty-cart-page {
    padding: 1em 1em 2.5em 1em;
  }

  .cart-buttons,
  .cart-footer {
    flex-direction: column;
    row-gap: 0.5em;
  }

  .clear-button {
    align-self: center;
  }
}
</style>
