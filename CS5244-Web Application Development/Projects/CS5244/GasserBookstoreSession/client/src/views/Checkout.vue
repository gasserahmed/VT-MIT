<template>
  <div class="checkout-container content-container">
    <router-link class="button" to="/cart" tag="button">
      Back to Cart
    </router-link>
  </div>
</template>

<script>
export default {
  name: "Checkout",
};
</script>

<style scoped>
.checkout-container {
  padding: 3em;
  display: flex;
  justify-content: center;
}
</style>
