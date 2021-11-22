<template>
  <div id="confirmationView">
    <ul>
      <li>Confirmation #: 123456789</li>
      <li>Time: 2020-08-05-18:33:56</li>
    </ul>
    <confirmation-table> </confirmation-table>
    <ul>
      <li>{{ $store.state.orderDetails.customer.customerName }}</li>
      <li>{{ $store.state.orderDetails.customer.address }}</li>
      <li>{{ $store.state.orderDetails.customer.email }}</li>
      <li>703-555-1212</li>
      <li>**** **** **** 1111 (08-2020)</li>
    </ul>
    <div id="customerInfo"></div>
  </div>
</template>

<script>
import ConfirmationTable from "@/components/ConfirmationTable";
export default {
  name: "Confirmation",
  components: { ConfirmationTable }
};
</script>

<style scoped>
#confirmationView {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin: 1em auto;
}
ul {
  text-align: left;
}
ul > li {
  margin: 0.25em;
}
</style>
