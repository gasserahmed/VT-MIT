<template>
  <div class="page-container">
    <div class="page-body">
      <EmptyCart
        v-if="$store.state.cart.empty"
        :text="'You need an item to checkout.'"
      />
      <section-container
        v-else
        :title="'Here\'s what you\'re getting!'"
        :description="
          'You have ' +
          $store.state.cart.numberOfItems +
          ' ' +
          ($store.state.cart.numberOfItems === 1 ? 'item' : 'items') +
          ' in your cart.'
        "
      >
        <cart-table></cart-table>
        <div class="cart-footer">
          <button
            class="button link-like-button clear-button"
            @click="clearCart"
          >
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
            :to="'/category/' + $store.state.selectedCategoryName"
            class="button secondary-button"
            tag="button"
          >
            Continue Shopping
          </router-link>
          <router-link to="/checkout" class="button" tag="button">
            Proceed to Checkout
          </router-link>
        </div>
      </section-container>
    </div>
  </div>
</template>

<script>
import CartTable from "@/components/CartTable";
import EmptyCart from "@/components/EmptyCart";
import SectionContainer from "@/components/SectionContainer";
export default {
  name: "Cart",
  components: { SectionContainer, EmptyCart, CartTable },
  methods: {
    clearCart() {
      this.$store.dispatch("clearCart");
    },
  },
};
</script>

<style scoped>
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
