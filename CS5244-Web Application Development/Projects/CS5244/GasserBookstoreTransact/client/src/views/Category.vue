<template>
  <div class="page-container">
    <category-nav></category-nav>
    <div v-if="$store.state.loadingStatus" class="loading-spinner-container">
      <vue-spinner line-bg-color="#D4D5D8" line-fg-color="#2B5658" />
    </div>
    <category-book-list v-else></category-book-list>
  </div>
</template>

<script>
import CategoryNav from "@/components/CategoryNav";
import CategoryBookList from "@/components/CategoryBookList";
import Spinner from "vue-simple-spinner";

export default {
  name: "category",
  components: {
    CategoryNav,
    CategoryBookList,
    vueSpinner: Spinner,
  },
  created: function () {
    const self = this;
    this.$store.dispatch("selectCategory", this.$route.params.name);
    this.$store.dispatch("fetchSelectedCategoryBooks").catch(function () {
      self.$router.push("/404"); // '/404' triggers NotFound
    });
  },
};
</script>

<style scoped></style>
