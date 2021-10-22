<template>
  <li class="book-box">
    <div class="book-image">
      <router-link to="#">
        <img
          :src="require('@/assets/images/books/' + book.bookId + '.jpg')"
          :alt="book.title"
        />
      </router-link>
    </div>
    <div class="book-title">{{ book.title }}</div>
    <div class="book-author">by {{ book.author }}</div>
    <div class="book-price">${{ (book.price / 100).toFixed(2) }}</div>
    <button class="button add-to-cart-button" @click="addToCart(book)">
      Add to Cart
    </button>
    <button class="button read-now-button" v-if="book.isPublic">
      <i class="fab fa-readme"></i>
    </button>
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
  methods: {
    addToCart(book) {
      this.$store.dispatch("addToCart", book);
    },
  },
};
</script>
<style scoped>
.book-box {
  width: 380px;
  height: 230px;
  display: grid;
  grid-template-columns: 1fr minmax(8em, 12em);
  grid-template-rows: min-content min-content min-content 1fr min-content;
  justify-content: space-between;
  background-color: var(--card-background-color);
  padding: 1em;
  row-gap: 0.25em;
  column-gap: 1em;
  border-radius: 25px;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.book-title {
  font-weight: bold;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.book-author {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
}

.book-price {
  font-weight: bold;
  font-size: 1.5rem;
  color: var(--neutral-color-dark);
}

.book-image {
  grid-column: 1 / 2;
  grid-row: 1 / -1;
}

.book-image img {
  height: 200px;
  width: 100%;
  min-height: 100%;
  border-radius: 25px;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.read-now-button {
  grid-column: 1 / 2;
  grid-row: 1 / -1;
  width: 35px;
  height: 35px;
  justify-self: end;
  margin-top: -0.5em;
  margin-right: -0.5em;
  font-size: 1.1rem;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0;
  box-shadow: 0px 4px 4px rgb(0 0 0 / 75%);
}

.add-to-cart-button {
  grid-row: -2 / -1;
  justify-self: end;
  font-size: 0.8rem;
  text-transform: uppercase;
}

.add-to-cart-button::before {
  font-family: FontAwesome;
  content: "\f217";
  margin-right: 5px;
}
</style>
