<template>
  <header class="container header-with-shadow">
    <section class="bookstore-logo-title flex-centered-container">
      <router-link to="/">
        <img
          src="@/assets/images/site/kitab-lab-logo.png"
          alt="Kitab Lab Logo"
          width="57px"
          height="auto"
        />
      </router-link>
      <span class="logo-title-divider"></span>
      <router-link to="/" tag="h1" class="logo-text">KITAB LAB </router-link>
    </section>
    <section class="search-bar flex-centered-container">
      <form class="flex-centered-container">
        <input
          type="text"
          class="search-bar-text"
          placeholder="Search books"
        /><br />
        <router-link
          :to="'/category/' + defaultCategory.name"
          tag="li"
          class="button icon-inside-button search-bar-button"
        >
          <i class="fas fa-search"></i>
        </router-link>
      </form>
    </section>
    <section class="header-dropdown-and-cart flex-centered-container">
      <header-dropdown-menu></header-dropdown-menu>
      <button class="button icon-only-button">
        <i class="fas fa-shopping-cart icon-only-button-icon">
          <span class="icon-only-button-text">0</span>
        </i>
      </button>
      <button class="button" style="padding: 7px">GA</button>
    </section>
  </header>
</template>

<script>
import HeaderDropdownMenu from "@/components/HeaderDropdown";
import ApiService from "@/services/ApiService";
export default {
  name: "AppHeader",
  components: { HeaderDropdownMenu },
  component: {
    HeaderDropdownMenu,
  },
  data: function () {
    return {
      defaultCategory: Object,
    };
  },
  created: function () {
    console.log("Start fetchDefaultCategory");
    this.fetchDefaultCategory();
    console.log("Finish fetchDefaultCategory");
  },
  methods: {
    fetchDefaultCategory() {
      const vm = this;
      ApiService.fetchDefaultCategory()
        .then((data) => {
          console.log("Data: " + data);
          vm.defaultCategory = data;
        })
        .catch((reason) => {
          console.log("Error: " + reason);
        });
    },
  },
};
</script>

<style scoped>
header {
  position: relative;
  background: var(--secondary-background-color);
  display: flex;
  justify-content: space-between;
  padding: 1em;
  gap: 1em;
  z-index: 1;
}

header .button:not(.icon-only-button) {
  background-color: var(--neutral-color);
}

header .button:not(.icon-only-button):hover,
header .button:not(.icon-only-button):active {
  background-color: var(--neutral-color-dark);
}

header .icon-only-button {
  color: var(--neutral-color);
}

header .icon-only-button:hover,
header .icon-only-button:active {
  color: var(--neutral-color-dark);
}

header a {
  text-decoration: none;
  text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  color: white !important;
}

.search-bar-text {
  padding: 0.5em 1.5em;
  border-radius: 50px;
  border: 0;
  background: var(--primary-background-color);
  width: 350px;
}

.search-bar-text:focus,
.search-bar-text:focus-visible {
  outline: none;
  box-shadow: 0 0 3pt 2pt var(--neutral-color);
}

.search-bar-button {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 0.4em;
  box-shadow: none;
  border-left: 1px solid;
  border-radius: 50%;
}

.logo-title-divider {
  height: 0;
  width: 40px;
  border: 2px solid #b1bccb;
  transform: rotate(-90deg);
}

.logo-text {
  font-family: var(--title-font-family);
  color: var(--neutral-color-dark);
  text-shadow: none;
  cursor: pointer;
}

.header-dropdown-and-cart {
  gap: 0.5em;
}

@media (max-width: 600px) {
  header {
    flex-direction: column;
  }
}
</style>
