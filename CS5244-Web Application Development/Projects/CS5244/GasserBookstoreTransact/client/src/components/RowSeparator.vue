<template>
  <div class="row-separator"></div>
</template>

<script>
export default {
  name: "RowSeparator",
};
</script>

<style scoped>
.row-separator {
  grid-column: 1 / -1;
  background-color: var(--primary-background-color);
  height: 2px;
}

@media (max-width: 750px) {
  .row-separator {
    width: 100%;
  }
}
</style>
