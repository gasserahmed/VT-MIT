<template>
  <div class="confirmation-container content-container">
    <router-link
      class="button"
      :to="'/category/' + $store.state.defaultCategoryName"
      tag="button"
    >
      Shop for More Experiments
    </router-link>
  </div>
</template>

<script>
export default {
  name: "Confirmation",
};
</script>

<style scoped>
.confirmation-container {
  padding: 3em;
  display: flex;
  justify-content: center;
}
</style>
