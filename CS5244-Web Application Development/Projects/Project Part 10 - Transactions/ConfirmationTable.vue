<template>
  <table>
    <tr
      v-for="(item, index) in this.$store.state.orderDetails.lineItems"
      :key="item.productId"
    >
      <td>
        {{ $store.state.orderDetails.books[index].title }}
      </td>
      <td>{{ item.quantity }}</td>
      <td>{{ 1299 | asDollarsAndCents }}</td>
    </tr>
  </table>
</template>

<script>
export default {
  name: "ConfirmationTable"
};
</script>

<style scoped>
table {
  border: 1px black solid;
  width: fit-content;
  margin-top: 1em;
  margin-bottom: 1em;
}

td {
  display: table-cell;
  padding: 0.5em 0.5em;
  background-color: white;
  vertical-align: middle;
}

tr:nth-child(even) > td {
  background-color: lightgray;
}

td:nth-child(1) {
  text-align: left;
}

td:nth-child(2) {
  text-align: center;
}

td:nth-child(3) {
  text-align: right;
}
</style>
