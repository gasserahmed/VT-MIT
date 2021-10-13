<template>
  <nav class="category-nav">
    <ul class="category-buttons">
      <template v-for="category in categories">
        <router-link
          v-if="category.name === $route.params.name"
          :key="category.categoryId"
          :to="'/category/' + category.name"
          tag="li"
          class="button selected-category-button"
        >
          {{ category.name }}
        </router-link>
        <router-link
          v-else
          :key="category.categoryId"
          :to="'/category/' + category.name"
          tag="li"
          class="button unselected-category-button"
        >
          {{ category.name }}
        </router-link>
      </template>
    </ul>
  </nav>
</template>

<script>
import ApiService from "@/services/ApiService";
export default {
  name: "CategoryNav",
  data: function () {
    return {
      categories: [],
    };
  },
  created: function () {
    console.log("Start fetchCategories");
    this.fetchCategories();
    console.log("Finish fetchCategories");
  },
  methods: {
    fetchCategories() {
      const vm = this;
      ApiService.fetchCategories()
        .then((data) => {
          console.log("Data: " + data);
          vm.categories = data;
        })
        .catch((reason) => {
          console.log("Error: " + reason);
        });
    },
  },
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

@media (max-width: 855px) {
  .category-buttons {
    display: block;
  }
}
</style>
