<template>
  <footer class="container">
    <section class="copyright">
      <i class="far fa-copyright"></i>
      <span>2021</span>
      <img
        src="@/assets/images/site/kitab-lab-logo-white.png"
        alt="Kitab Lab Logo"
        width="57px"
        height="auto"
      />
    </section>
    <section class="links">
      <router-link to="/">About</router-link>
      <router-link to="/">Directions</router-link>
      <router-link to="/">Contact</router-link>
    </section>
    <section class="social-media-icons">
      <router-link to="/" class="icon-only-button-icon"
        ><i class="fab fa-facebook"></i
      ></router-link>
      <router-link to="/" class="icon-only-button-icon"
        ><i class="fab fa-twitter"></i
      ></router-link>
      <router-link to="/" class="icon-only-button-icon"
        ><i class="fab fa-instagram"></i
      ></router-link>
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
  background: var(--tertiary-background-color);
  display: flex;
  justify-content: space-between;
  padding: 1.5rem !important;
}

footer .links a:not(:first-child) {
  padding-left: 0.5em;
  border-left: 1px solid white;
}

footer a:hover {
  color: #f1d3a4 !important;
}

.copyright,
.links,
.social-media-icons,
footer a {
  display: flex;
  flex-direction: row;
  align-items: center;
  color: white !important;
  gap: 0.5em;
  text-decoration: none;
}

.links a:hover {
  text-decoration: underline;
}

.social-media-icons {
  text-align: center;
  cursor: pointer;
  font-size: 0.7em;
  gap: 1.5em;
}

@media (max-width: 500px) {
  footer {
    flex-direction: column;
    align-items: center;
    gap: 0.5em;
  }
}
</style>
