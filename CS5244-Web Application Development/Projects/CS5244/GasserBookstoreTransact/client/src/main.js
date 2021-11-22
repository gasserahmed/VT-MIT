import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import "@/assets/css/global.css";
import store, { CART_STORAGE_KEY, ORDER_DETAILS_STORAGE_KEY } from "./store";
import Vuelidate from "vuelidate";

Vue.config.productionTip = false;

// From https://flaviocopes.com/how-to-format-number-as-currency-javascript/
const PriceFormatter = new Intl.NumberFormat("en-US", {
  style: "currency",
  currency: "USD",
  minimumFractionDigits: 2,
});

Vue.filter("asDollarsAndCents", function (cents) {
  return PriceFormatter.format(cents / 100.0);
});

Vue.filter("formatCcLastFour", function (ccNumber) {
  return "**" + ccNumber.substring(ccNumber.length - 4);
});

Vue.filter("formatCcExpDate", function (ccExpDate) {
  const date = new Date(ccExpDate);
  return date.getMonth() + 1 + "/" + date.getFullYear();
});

Vue.use(Vuelidate);

new Vue({
  router,
  store,
  render: function (h) {
    return h(App);
  },
  created() {
    const cartString = localStorage.getItem(CART_STORAGE_KEY);
    const orderDetailsString = localStorage.getItem(ORDER_DETAILS_STORAGE_KEY);
    if (cartString) {
      const shoppingCart = JSON.parse(cartString);
      this.$store.commit("SET_CART", shoppingCart);
    }

    if (orderDetailsString) {
      const orderDetails = JSON.parse(orderDetailsString);
      this.$store.commit("SET_ORDER_DETAILS", orderDetails);
    }
  },
}).$mount("#app");
