<template>
  <div class="header-dropdown">
    <button class="button categories-button">Categories</button>
    <ul>
      <router-link to="../category/classics" tag="li"> Classics </router-link>
      <router-link to="../category/fantasy" tag="li"> Fantasy </router-link>
      <router-link to="../category/mystery" tag="li"> Mystery </router-link>
      <router-link to="../category/romance" tag="li"> Romance </router-link>
    </ul>
  </div>
</template>

<script>
export default {
  name: "HeaderDropdownMenu",
};
</script>

<style scoped>
.header-dropdown {
  position: relative;
}

.categories-button {
  background-color: var(--primary-color-dark);
  margin-top: 0.25em;
}

.header-dropdown ul {
  background-color: white;
  color: black;
  display: none;
}

.header-dropdown li {
  padding: 0.25em 0.5em;
}

.header-dropdown a {
  color: black;
  text-decoration: none;
}

.header-dropdown li:hover {
  background: lightgray;
}

.header-dropdown:hover ul {
  display: block;
  position: absolute;
  min-width: 8em;
  z-index: 1;
  cursor: pointer;
}
</style>
