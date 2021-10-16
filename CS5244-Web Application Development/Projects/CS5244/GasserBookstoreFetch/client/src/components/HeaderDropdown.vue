<template>
  <div class="header-dropdown">
    <button class="button categories-button">
      Categories <i class="fas fa-caret-right"></i>
    </button>
    <ul>
      <router-link
        v-for="category in categories"
        :key="category.categoryId"
        :to="'/category/' + category.name"
        tag="li"
      >
        {{ category.name }}
      </router-link>
    </ul>
  </div>
</template>

<script>
import ApiService from "@/services/ApiService";

export default {
  name: "HeaderDropdownMenu",
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
button {
  background-color: var(--neutral-color) !important;
}

.categories-button:hover,
.categories-button:active,
.header-dropdown:hover .categories-button {
  background-color: var(--neutral-color-dark) !important;
}

.header-dropdown {
  position: relative;
}

.categories-button {
  position: relative;
  z-index: 2;
  display: flex;
  align-items: center;
}

.fa-caret-right {
  margin-left: 5px;
  transition: transform 0.5s ease-in-out;
}

.header-dropdown:hover .fa-caret-right {
  transform: rotate(90deg);
}

.header-dropdown ul {
  background-color: #f1f6f6;
  display: none;
  border-radius: 0 0 10px 10px;
}

.header-dropdown li {
  padding: 0.25em 0.5em;
  color: var(--neutral-color) !important;
  text-decoration: none;
  text-shadow: none;
}

.header-dropdown li:hover {
  background: #d4e2e2;
  font-weight: bold;
}

.header-dropdown li:last-child:hover {
  border-radius: 0 0 10px 10px;
}

.header-dropdown:hover ul {
  display: block;
  position: absolute;
  min-width: 8.15em;
  cursor: pointer;
  margin-top: -12%;
  padding-top: 16%;
  z-index: 1;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
</style>
