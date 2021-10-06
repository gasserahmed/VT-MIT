<template>
  <div class="header-dropdown">
    <button class="button categories-button">Categories</button>
    <ul>
      <router-link to="../category/arts" tag="li">Arts </router-link>
      <router-link to="../category/computers" tag="li">Computers </router-link>
      <router-link to="../category/entertainment" tag="li"
        >Entertainment
      </router-link>
      <router-link to="../category/kids" tag="li">Kids </router-link>
      <router-link to="../category/religion" tag="li">Religion </router-link>
      <router-link to="../category/romance" tag="li">Romance </router-link>
      <router-link to="../category/sports" tag="li">Sports </router-link>
    </ul>
  </div>
</template>

<script>
export default {
  name: "HeaderDropdownMenu",
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
}

.categories-button::after {
  font-family: FontAwesome;
  font-size: 14px;
  content: "\f0d7";
  margin-left: 5px;
}

.header-dropdown ul {
  background-color: #f1f6f6;
  color: black;
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
