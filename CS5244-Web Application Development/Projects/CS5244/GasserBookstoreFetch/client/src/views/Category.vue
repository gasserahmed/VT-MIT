<template>
  <div>
    <category-nav></category-nav>
    <category-book-list :books="books"> </category-book-list>
  </div>
</template>

<script>
import CategoryNav from "@/components/CategoryNav";
import CategoryBookList from "@/components/CategoryBookList";
import ApiService from "@/services/ApiService";

export default {
  name: "category",
  components: {
    CategoryNav,
    CategoryBookList,
  },
  data: function () {
    return {
      books: [],
    };
  },
  created: function () {
    console.log("Start fetchBooksByCategoryId");
    this.fetchSelectedCategoryBooks(this.$route.params.name);
    console.log("Finish fetchBooksByCategoryId");
  },
  methods: {
    fetchSelectedCategoryBooks(selectedCategory) {
      const vm = this;
      ApiService.fetchSelectedCategoryBooks(selectedCategory)
        .then((data) => {
          console.log("Data: " + data);
          vm.books = data;
        })
        .catch((reason) => {
          console.log("Error: " + reason);
        });
    },
  },
};
</script>

<style scoped></style>
