<template>
  <div class="home-page">
    <section class="welcome-text flow-content container dark-background">
      <h2>Welcome to Another Bookstore</h2>
      <p>
        Another Bookstore is a client-side application written using Vue
        components.
      </p>
      <p>
        It includes a home page and a category page. Both pages have the same
        header and footer.
      </p>
      <p>
        You can navigate to the category page by choosing any category from the
        dropdown menu in the header. You can navigate to the home page by
        clicking on the logo in the header.
      </p>
      <p>Take some time and explore the code.</p>
    </section>
    <section class="category-images container">
      <home-category-list></home-category-list>
    </section>
  </div>
</template>

<script>
import HomeCategoryList from "@/components/HomeCategoryList";
export default {
  name: "Home",
  components: { HomeCategoryList },
};
</script>

<style scoped>
.home-page {
  display: flex;
}

.welcome-text {
  width: 20em;
}
</style>
