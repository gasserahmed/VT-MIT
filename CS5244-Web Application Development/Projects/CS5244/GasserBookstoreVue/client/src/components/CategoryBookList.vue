<template>
  <ul>
    <category-book-list-item :book="books[0]"></category-book-list-item>
    <category-book-list-item :book="books[1]"></category-book-list-item>
    <category-book-list-item :book="books[2]"></category-book-list-item>
    <category-book-list-item :book="books[3]"></category-book-list-item>
    <category-book-list-item :book="books[4]"></category-book-list-item>
    <category-book-list-item :book="books[5]"></category-book-list-item>
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
}
</style>
