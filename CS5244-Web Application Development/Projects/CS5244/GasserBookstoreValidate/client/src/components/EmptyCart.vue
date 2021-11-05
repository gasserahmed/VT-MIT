<template>
  <section class="empty-cart-page">
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
</template>

<script>
export default {
  name: "EmptyCart",
};
</script>

<style scoped>
.empty-cart-page {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding: 1em 5em 2.5em 5em;
}

.empty-cart-text {
  padding: 1em;
  background: var(--secondary-background-color);
  text-align: center;
  font-size: 1.5rem;
  font-weight: 500;
  color: var(--neutral-color-dark);
}
.empty-cart-page .cart-buttons {
  justify-content: center;
  padding: 0 2em 2em 2em;
}

.cart-buttons {
  display: flex;
  background-color: var(--secondary-background-color);
  justify-content: space-between;
  padding: 1em;
  align-items: center;
}

.cart-buttons {
  padding: 2em 1em;
}

@media (max-width: 750px) {
  .empty-cart-page {
    padding: 1em 1em 2.5em 1em;
  }

  .cart-buttons {
    flex-direction: column;
    row-gap: 0.5em;
  }
}
</style>
