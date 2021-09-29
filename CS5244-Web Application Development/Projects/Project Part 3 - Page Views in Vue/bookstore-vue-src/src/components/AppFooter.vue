<template>
  <footer class="container">
    <section class="links">
      <router-link to="/">about</router-link>
      <router-link to="/">contact</router-link>
      <router-link to="/">directions</router-link>
    </section>
    <section class="social-media-icons">
      <router-link to="/" class="button">Facebook</router-link>
      <router-link to="/" class="button">Twitter</router-link>
    </section>
  </footer>
</template>

<script>
export default {
  name: "AppFooter",
};
</script>

<style scoped>
footer {
  background: var(--secondary-background-color);
  display: flex;
  gap: 1em;
}

.links,
.social-media-icons {
  display: flex;
  flex-direction: column;
}

.social-media-icons {
  text-align: center;
  gap: 0.25em;
}
</style>
