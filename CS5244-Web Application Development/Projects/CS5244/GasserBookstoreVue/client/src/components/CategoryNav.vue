<template>
  <nav class="category-nav">
    <ul class="category-buttons">
      <router-link
        to="../category/arts"
        tag="li"
        class="button unselected-category-button"
      >
        Arts
      </router-link>
      <li class="button selected-category-button">Computers</li>
      <router-link
        to="../category/entertainment"
        tag="li"
        class="button unselected-category-button"
      >
        Entertainment
      </router-link>
      <router-link
        to="../category/kids"
        tag="li"
        class="button unselected-category-button"
      >
        Kids
      </router-link>
      <router-link
        to="../category/religion"
        tag="li"
        class="button unselected-category-button"
      >
        Religion
      </router-link>
      <router-link
        to="../category/romance"
        tag="li"
        class="button unselected-category-button"
      >
        Romance
      </router-link>
      <router-link
        to="../category/sports"
        tag="li"
        class="button unselected-category-button"
      >
        Sports
      </router-link>
      <router-link
        to="../category/travel"
        tag="li"
        class="button unselected-category-button"
      >
        Travel
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
  background-color: white;
  border-top: 2px solid var(--primary-background-color);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.category-buttons .button {
  margin: 5px;
  box-shadow: none;
}

.button.selected-category-button {
  background-color: rgb(60 121 123 / 22%);
  color: var(--neutral-color);
  font-weight: bold;
}

.button.unselected-category-button,
.button.unselected-category-button:visited {
  background-color: white;
  color: var(--neutral-color);
}

.button.unselected-category-button:hover,
.button.unselected-category-button:active {
  background-color: rgb(60 121 123 / 7%);
}
</style>
