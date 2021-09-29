<template>
  <li class="book-box">
    <div class="book-image">
      <img
        :src="require('@/assets/images/books/' + book.imageFile)"
        :alt="book.title"
      />
    </div>
    <div class="book-title">{{ book.title }}</div>
    <div class="book-author">{{ book.author }}</div>
    <div class="book-price">${{ (book.price / 100).toFixed(2) }}</div>
    <button class="button">Add to Cart</button>
    <button class="button">Read Now</button>
  </li>
</template>

<script>
export default {
  name: "categoryBookListItem",
  props: {
    book: {
      type: Object,
      required: true,
    },
  },
};
</script>
<style scoped>
.book-box {
  display: flex;
  flex-direction: column;
  background-color: var(--card-background-color);
  padding: 1em;
  gap: 0.25em;
}

.book-title {
  font-weight: bold;
}

.book-author {
  font-style: italic;
}
</style>
