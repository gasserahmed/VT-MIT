<template>
  <ul>
    <category-book-list-item
      v-for="book in books"
      :key="book.bookId"
      :book="book"
    ></category-book-list-item>
  </ul>
</template>

<script>
import CategoryBookListItem from "./CategoryBookListItem";

export default {
  name: "CategoryBookList",
  props: {
    books: {
      type: Array,
      required: true,
    },
  },
  components: {
    CategoryBookListItem,
  },
};
</script>

<style scoped>
ul {
  display: flex;
  flex-wrap: wrap;
  padding: 1em;
  gap: 1em;
  justify-content: center;
}
</style>
