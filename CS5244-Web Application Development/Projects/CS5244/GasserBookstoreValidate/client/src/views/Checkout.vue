<template>
  <div class="checkout-page content-container">
    <!-- TODO: Add an HTML section to display when checking out with an empty cart -->
    <div class="page-title-container">
      <h2 class="page-title">Checkout</h2>
      <div class="row-separator"></div>
    </div>
    <section class="checkout-page-body" v-if="!cart.empty">
      <form @submit.prevent="submitOrder">
        <div>
          <label for="name">Name</label>
          <div>
            <input
              type="text"
              size="20"
              id="name"
              name="name"
              v-model.lazy="$v.name.$model"
            />
            <template v-if="$v.name.$error">
              <span class="error" v-if="!$v.name.required"
                >Name is required</span
              >
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
          </div>
        </div>
        <div>
          <label for="name">Address</label>
          <div>
            <input
              type="text"
              size="20"
              id="address"
              name="address"
              v-model.lazy="$v.address.$model"
            />
            <template v-if="$v.address.$error">
              <span class="error" v-if="!$v.address.required"
                >Address is required</span
              >
              <span class="error" v-else-if="!$v.address.minLength">
                Address must have at least
                {{ $v.address.$params.minLength.min }} letters.
              </span>
              <span class="error" v-else-if="!$v.address.maxLength">
                Address can have at most
                {{ $v.address.$params.maxLength.max }} letters.
              </span>
              <span class="error" v-else> An unexpected error occurred. </span>
            </template>
          </div>
        </div>
        <div>
          <label for="phone">Phone</label>
          <div>
            <input
              class="textField"
              type="text"
              size="20"
              id="phone"
              name="phone"
              v-model.lazy="$v.phone.$model"
            />
            <template v-if="$v.phone.$error">
              <span class="error" v-if="!$v.phone.required"
                >Phone is required</span
              >
              <span class="error" v-else-if="!$v.phone.isValidPhone"
                >Please enter a valid phone number.</span
              >
              <span class="error" v-else> An unexpected error occurred. </span>
            </template>
          </div>
        </div>
        <div>
          <label for="email">Email</label>
          <div>
            <input
              type="text"
              size="20"
              id="email"
              name="email"
              v-model.lazy="$v.email.$model"
            />
            <template v-if="$v.email.$error">
              <span class="error" v-if="!$v.email.required"
                >Email is required</span
              >
              <span class="error" v-else-if="!$v.email.email"
                >Please enter a valid email.</span
              >
              <span class="error" v-else> An unexpected error occurred. </span>
            </template>
          </div>
        </div>
        <div>
          <label for="ccNumber">Credit Card</label>
          <div>
            <input
              type="text"
              size="20"
              id="ccNumber"
              name="ccNumber"
              v-model.lazy="$v.ccNumber.$model"
            />
            <template v-if="$v.ccNumber.$error">
              <span class="error" v-if="!$v.ccNumber.required"
                >Credit card is required</span
              >
              <span class="error" v-else-if="!$v.ccNumber.isValidCreditCard"
                >Please enter a valid credit card.</span
              >
              <span class="error" v-else> An unexpected error occurred. </span>
            </template>
          </div>
        </div>
        <div>
          <label>Exp. Date</label>
          <div class="expiration-date-input-container">
            <select v-model="ccExpiryMonth">
              <option
                v-for="(month, index) in months"
                :key="index"
                :value="index + 1"
              >
                {{ month }} ({{ index + 1 }})
              </option>
            </select>
            <select>
              <option v-for="(year, index) in 15" :key="index">
                {{ yearFrom(index) }}
              </option>
            </select>
          </div>
        </div>
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

      Your credit card will be charged
      {{ (cart.subtotal + cart.surcharge) | asDollarsAndCents }}
      <br />({{ cart.subtotal | asDollarsAndCents }} +
      {{ cart.surcharge | asDollarsAndCents }}
      shipping)
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
    <!--    <div-->
    <!--      style="-->
    <!--        border: 1px solid black;-->
    <!--        padding: 1em;-->
    <!--        margin-left: 1em;-->
    <!--        text-align: left;-->
    <!--      "-->
    <!--    >-->
    <!--      <tree-view-->
    <!--        :data="$v"-->
    <!--        :options="{ rootObjectKey: '$v', maxDepth: 1 }"-->
    <!--      ></tree-view>-->
    <!--    </div>-->
  </div>
</template>

<script>
import {
  required,
  // email,
  minLength,
  maxLength,
} from "vuelidate/lib/validators";

import isMobilePhone from "validator/lib/isMobilePhone";
import email from "vuelidate/lib/validators/email";
import isCreditCard from "validator/lib/isCreditCard";

const isValidPhone = (value) => isMobilePhone(value, "en-US");
const isValidCreditCard = (value) => isCreditCard(value);

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
    address: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(45),
    },
    phone: {
      required,
      isValidPhone,
    },
    email: {
      required,
      email,
    },
    ccNumber: {
      required,
      isValidCreditCard,
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
      this.$v.$touch(); // Ensures validators always run
      if (this.$v.$invalid) {
        this.checkoutStatus = "ERROR";
      } else {
        this.checkoutStatus = "PENDING";
        setTimeout(() => {
          this.checkoutStatus = "OK";
          setTimeout(() => {
            this.$router.push({ name: "confirmation" });
          }, 1000);
        }, 1000);
      }
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
  display: flex;
  flex-direction: column;
  /*margin: 0 auto;*/
  padding: 1em 5em 2.5em 5em;
}

.page-title-container {
  background: var(--secondary-background-color);
}

.page-title {
  color: var(--neutral-color-dark);
  padding: 1em;
  padding-left: 0.72em;
}

.checkout-page-body {
  display: flex;
  padding: 1em;
  justify-content: space-between;
  background-color: var(--secondary-background-color);
}

form {
  display: flex;
  flex-direction: column;
}

form > div {
  display: grid;
  grid-template-columns: 1fr 3fr;
  margin-bottom: 0.5em;
}

form > div > div {
  /*background-color: var(--primary-background-color);*/
  margin-left: 0.5em;
  display: flex;
  flex-direction: column;
}

form > div > div > input,
form > div > div > select {
  background-color: var(--primary-background-color);
  border: none;
}

form > div > div > .error {
  margin-top: 0.2em;
}

.checkoutStatusBox {
  margin: 1em;
  padding: 1em;
  text-align: center;
}

.expiration-date-input-container {
  display: grid;
  grid-template-columns: repeat(2, auto);
  grid-column-gap: 0.3em;
}
</style>
