<template>
  <nav class="category-nav">
    <ul class="category-buttons">
      <li class="button selected-category-button">Classics</li>
      <router-link
        to="category/fantasy"
        tag="li"
        class="button unselected-category-button"
      >
        Fantasy
      </router-link>
      <router-link
        to="category/mystery"
        tag="li"
        class="button unselected-category-button"
      >
        Mystery
      </router-link>
      <router-link
        to="category/romance"
        tag="li"
        class="button unselected-category-button"
      >
        Romance
      </router-link>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "CategoryNav",
};
</script>

<style scoped>
.category-buttons {
  display: flex;
  flex-direction: row;
  text-align: center;
  background-color: var(--neutral-color);
}

.button.selected-category-button {
  background-color: var(--primary-color);
}

.button.unselected-category-button,
.button.unselected-category-button:visited {
  background-color: var(--neutral-color);
}

.button.unselected-category-button:hover,
.button.unselected-category-button:active {
  background-color: var(--primary-color);
}
</style>
