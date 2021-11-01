<template>
  <div class="home-page content-container">
    <section class="welcome-text flow-content container">
      <h2>KITAB LAB</h2>
      <p>Where every book is a new experiment.</p>
      <router-link
        :to="'/category/' + $store.state.defaultCategoryName"
        tag="li"
        class="button shop-button"
      >
        Shop Your Experiment
      </router-link>
    </section>
  </div>
</template>

<script>
export default {
  name: "Home",
};
</script>

<style scoped>
.home-page {
  height: 620px;
  background: no-repeat url("../assets/images/site/background-book.jpeg");
  background-size: cover;
  display: flex;
  box-shadow: inset 0 4px 4px rgba(0, 0, 0, 0.25);
}

.welcome-text {
  width: 30em;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 1rem 2rem;
}

.welcome-text h2 {
  color: var(--neutral-color-dark);
  font-size: 3.5em;
  font-family: "Proza Libre";
  font-weight: 100;
}

.welcome-text p {
  color: var(--neutral-color);
  font-size: 1.5em;
  font-weight: 100;
}

.shop-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 15em;
  margin-top: 2rem;
}

@media (max-width: 785px) {
  .home-page {
    justify-content: center;
  }
  .welcome-text {
    align-items: center;
    text-align: center;
  }
}
</style>
