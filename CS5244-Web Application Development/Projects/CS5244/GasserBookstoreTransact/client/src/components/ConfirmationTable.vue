<template>
  <!--  <table>-->
  <!--    <tr-->
  <!--      v-for="(item, index) in this.$store.state.orderDetails.lineItems"-->
  <!--      :key="item.productId"-->
  <!--    >-->
  <!--      <td>-->
  <!--        {{ $store.state.orderDetails.books[index].title }}-->
  <!--      </td>-->
  <!--      <td>{{ item.quantity }}</td>-->
  <!--      <td>{{ 1299 | asDollarsAndCents }}</td>-->
  <!--    </tr>-->
  <!--  </table>-->
  <div class="confirmation-table">
    <ul>
      <li class="confirmation-heading">
        <div class="confirmation-heading-book">Book</div>
        <div class="confirmation-heading-price">Price</div>
        <div class="confirmation-heading-quantity">Quantity</div>
        <div class="confirmation-heading-subtotal">Total</div>
      </li>
      <row-separator></row-separator>
      <li
        v-for="(item, index) in this.$store.state.orderDetails.lineItems"
        :key="item.productId"
      >
        <div class="confirmation-book-image">
          <img
            :src="require('@/assets/images/books/' + item.bookId + '.jpg')"
            :alt="$store.state.orderDetails.books[index].title"
            width="100px"
            height="auto"
          />
        </div>
        <div class="confirmation-book-title">
          {{ $store.state.orderDetails.books[index].title }}
        </div>
        <div class="confirmation-book-price">
          <span class="column-label">Price:</span>
          {{ $store.state.orderDetails.books[index].price | asDollarsAndCents }}
        </div>
        <div class="confirmation-book-quantity">
          <span class="column-label">Quantity:</span>
          <span class="row-book-quantity">{{ item.quantity }}</span>
        </div>
        <div class="subtotal">
          <span class="column-label">Total:</span>
          {{
            (item.quantity * $store.state.orderDetails.books[index].price)
              | asDollarsAndCents
          }}
        </div>
        <row-separator></row-separator>
      </li>
    </ul>
  </div>
</template>

<script>
import RowSeparator from "@/components/RowSeparator";

export default {
  name: "ConfirmationTable",
  components: { RowSeparator },
};
</script>

<style scoped>
.confirmation-table {
  display: grid;
  grid-template-columns: max-content minmax(10em, 200em) repeat(3, max-content);
  row-gap: 1em;
  background-color: var(--secondary-background-color);
}

.confirmation-table .row-separator:first-of-type {
  margin-top: -1em !important;
}

ul,
li {
  display: contents;
}

.confirmation-heading {
  color: var(--neutral-color-dark);
}

.confirmation-heading > * {
  height: 2.5em;
  display: flex;
  align-items: center;
}

.confirmation-heading-book {
  grid-column: 1 / 3;
  padding: 0 1em;
}

.confirmation-heading-price {
  grid-column: 3 / 4;
  padding: 0 1em;
}

.confirmation-heading-quantity {
  grid-column: 4 / 5;
  padding: 0 1em;
}

.confirmation-heading-subtotal {
  padding: 0 1em;
}

.confirmation-book-image {
  padding: 0 1em;
}

.confirmation-book-title {
  padding: 0 1em;
}

.confirmation-book-price {
  padding: 0 1em;
}

.confirmation-book-quantity {
  padding: 0 1em;
  justify-self: center;
}

.subtotal {
  padding: 0 1em;
}

.column-label {
  display: none;
  color: var(--neutral-color-dark);
}

@media (max-width: 750px) {
  .confirmation-heading {
    display: none;
  }

  .column-label {
    display: inline;
  }

  .confirmation-table {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 0.5em;
  }

  .confirmation-book-title {
    text-align: center;
  }
}
</style>
