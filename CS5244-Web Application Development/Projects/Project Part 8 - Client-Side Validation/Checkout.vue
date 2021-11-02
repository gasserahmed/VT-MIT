<template>
  <div class="checkout-page">
    <!-- TODO: Add an HTML section to display when checking out with an empty cart -->

    <section class="checkout-page-body" v-if="!cart.empty">
      <form @submit.prevent="submitOrder">
        <div>
          <label for="name">Name</label>
          <input
            type="text"
            size="20"
            id="name"
            name="name"
            v-model.lazy="$v.name.$model"
          />
        </div>
        <template v-if="$v.name.$error">
          <span class="error" v-if="!$v.name.required">Name is required</span>
          <span class="error" v-else-if="!$v.name.minLength">
            Name must have at least
            {{ $v.name.$params.minLength.min }} letters.
          </span>
          <span class="error" v-else-if="!$v.name.maxLength">
            Name can have at most
            {{ $v.name.$params.maxLength.max }} letters.
          </span>
          <span class="error" v-else> An unexpected error occurred. </span>
        </template>
        <!-- TODO: Add address input and validation messages -->

        <div>
          <label for="phone">Phone</label>
          <input
            class="textField"
            type="text"
            size="20"
            id="phone"
            name="phone"
          />
        </div>
        <!-- TODO: Add phone validation message(s) -->

        <div>
          <label for="email">Email</label>
          <input type="text" size="20" id="email" name="email" />
        </div>
        <!-- TODO: Add email validation message(s) -->

        <div>
          <label for="ccNumber">Credit card</label>
          <input type="text" size="20" id="ccNumber" name="ccNumber" />
        </div>
        <!-- TODO: Add credit card validation message(s) -->

        <div>
          <label>Exp Month</label>
          <select v-model="ccExpiryMonth">
            <option
              v-for="(month, index) in months"
              :key="index"
              :value="index + 1"
            >
              {{ month }} ({{ index + 1 }})
            </option>
          </select>
        </div>

        <div>
          <label>Exp Year</label>
          <select>
            <!-- TODO: Complete this select tag for 'ccExpiryYear'. -->
          </select>
        </div>
        <!-- TODO (style): Use a single label for both month and date and put the on the same line. -->
        <!-- TODO (style): For example: Exp Date {Month} {Year}, with space between month/year selectors. -->

        <input
          type="submit"
          name="submit"
          class="button"
          :disabled="checkoutStatus == 'PENDING'"
          value="Complete Purchase"
        />
        <!-- TODO (style): The submit button should not take up the entire width of the form. -->
        <!-- TODO (style): The submit button should be styled consistent with your own site. -->
      </form>
      <!-- TODO (style): Fix error message placement so they nearer to the correct fields. -->
      <!-- TODO (style): HINT: Use another <div> and label, input, and error, and use flexbox to style. -->

      <div
        style="
          border: 1px solid black;
          padding: 1em;
          margin-left: 1em;
          text-align: left;
        "
      >
        <tree-view
          :data="$v"
          :options="{ rootObjectKey: '$v', maxDepth: 1 }"
        ></tree-view>
      </div>

      <!-- TODO: Display the cart total, subtotal and surcharge. -->

      <section v-show="checkoutStatus != ''" class="checkoutStatusBox">
        <div v-if="checkoutStatus == 'ERROR'">
          Error: Please fix the problems above and try again.
        </div>

        <div v-else-if="checkoutStatus == 'PENDING'">Processing...</div>

        <div v-else-if="checkoutStatus == 'OK'">Order placed...</div>

        <div v-else>
          <!-- "checkoutStatus == 'SERVER_ERROR'" -->
          An unexpected error occurred, please try again.
        </div>
      </section>
    </section>
  </div>
</template>

<script>
import {
  required,
  // email,
  minLength,
  maxLength,
} from "vuelidate/lib/validators";

// import isCreditCard from "validator/lib/isCreditCard";
// import isMobilePhone from "validator/lib/isMobilePhone";

// const phone = value => isMobilePhone(value, "en-US");
// const creditCard = value => isCreditCard(value);

export default {
  data() {
    return {
      name: "",
      address: "",
      phone: "",
      email: "",
      ccNumber: "",
      ccExpiryMonth: new Date().getMonth() + 1,
      ccExpiryYear: new Date().getFullYear(),
      checkoutStatus: "",
    };
  },
  validations: {
    name: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(45),
    },
    // TODO: Add more validations for the other fields that need validation.
  },

  computed: {
    cart() {
      return this.$store.state.cart;
    },
    months() {
      return [
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December",
      ];
    },
  },

  methods: {
    submitOrder() {
      console.log("Submit order");
    },

    /* NOTE: For example yearFrom(0) == 2021 */
    yearFrom(index) {
      return new Date().getFullYear() + index;
    },
  },
};
</script>

<style scoped>
/* TODO: Adapt these styles to your page */
.checkout-page {
  background: rgba(105, 100, 100, 0.5);
  color: #7aed32;
}
.checkout-page-body {
  display: flex;
  padding: 1em;
}

form {
  display: flex;
  flex-direction: column;
}

form > div {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 0.5em;
}

form > div > input,
form > div > select {
  background-color: #666666;
  margin-left: 0.5em;
}

form > .error {
  color: red;
  text-align: right;
}

.checkoutStatusBox {
  margin: 1em;
  padding: 1em;
  text-align: center;
}
</style>
