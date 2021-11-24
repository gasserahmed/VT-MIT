<template>
  <div class="page-container">
    <div class="page-body">
      <warning-container
        v-if="cart.empty"
        :warning-image="{ src: 'empty-shopping-cart.png' }"
        warning-title="Your cart is currently empty!"
        warning-description="Once you have added items to your cart, you can check out from here."
        :warning-button-route="'/category/' + $store.state.selectedCategoryName"
        warning-button-text="Shop Your Experiment"
      />
      <div v-else>
        <section-container title="Checkout">
          <form id="checkout-form" @submit.prevent="submitOrder">
            <div>
              <label for="name">Name</label>
              <div>
                <input
                  type="text"
                  size="20"
                  id="name"
                  name="name"
                  placeholder="Full Name"
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
                  <span class="error" v-else>
                    An unexpected error occurred.
                  </span>
                </template>
              </div>
            </div>
            <div>
              <label for="address">Address</label>
              <div>
                <input
                  type="text"
                  size="20"
                  id="address"
                  name="address"
                  placeholder="Street, City, State, Zip Code"
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
                  <span class="error" v-else>
                    An unexpected error occurred.
                  </span>
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
                  placeholder="Phone Number"
                  v-model.lazy="$v.phone.$model"
                  v-model="phone"
                />
                <template v-if="$v.phone.$error">
                  <span class="error" v-if="!$v.phone.required"
                    >Phone is required</span
                  >
                  <span class="error" v-else-if="!$v.phone.isValidPhone"
                    >Please enter a valid phone number.</span
                  >
                  <span class="error" v-else>
                    An unexpected error occurred.
                  </span>
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
                  placeholder="Email"
                  v-model.lazy="$v.email.$model"
                />
                <template v-if="$v.email.$error">
                  <span class="error" v-if="!$v.email.required"
                    >Email is required</span
                  >
                  <span class="error" v-else-if="!$v.email.email"
                    >Please enter a valid email.</span
                  >
                  <span class="error" v-else>
                    An unexpected error occurred.
                  </span>
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
                  placeholder="xxxx-xxxx-xxxx-xxxx"
                  inputmode="numeric"
                  v-model.lazy="$v.ccNumber.$model"
                  v-model="ccNumber"
                />
                <template v-if="$v.ccNumber.$error">
                  <span class="error" v-if="!$v.ccNumber.required"
                    >Credit card is required</span
                  >
                  <span class="error" v-else-if="!$v.ccNumber.isValidCreditCard"
                    >Please enter a valid credit card.</span
                  >
                  <span class="error" v-else>
                    An unexpected error occurred.
                  </span>
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
          </form>
        </section-container>
        <section-container
          :title="
            $store.state.cart.numberOfItems +
            ' ' +
            ($store.state.cart.numberOfItems === 1 ? 'item' : 'items')
          "
          :button="{ text: 'Edit cart', routeTo: '/cart' }"
        >
          <div class="checkout-summary" v-if="!cart.empty">
            <div>
              <div class="bold-text">
                <span>Subtotal</span>
                <span>{{ cart.subtotal | asDollarsAndCents }}</span>
              </div>
              <div>
                <span>Shipping</span>
                <span>{{ cart.surcharge | asDollarsAndCents }}</span>
              </div>
              <row-separator></row-separator>
              <div class="bold-text">
                <span>Total</span>
                <span>{{
                  (cart.subtotal + cart.surcharge) | asDollarsAndCents
                }}</span>
              </div>
            </div>
            <div
              class="button-wrapper"
              :class="{ disabled: checkoutStatus == 'PENDING' }"
            >
              <button
                type="submit"
                name="submit"
                class="button"
                :disabled="checkoutStatus == 'PENDING'"
                form="checkout-form"
              >
                Complete Purchase
              </button>
            </div>
            <section v-show="checkoutStatus != ''" class="checkoutStatusBox">
              <div v-if="checkoutStatus == 'ERROR'">
                Error: Please fix the problems and try again.
              </div>

              <div
                v-else-if="checkoutStatus == 'PENDING'"
                class="loading-spinner-container"
              >
                <vue-spinner
                  line-bg-color="#D4D5D8"
                  line-fg-color="#2B5658"
                  message="Processing..."
                />
              </div>

              <div v-else-if="checkoutStatus == 'OK'">Order placed...</div>

              <div v-else>
                <!-- "checkoutStatus == 'SERVER_ERROR'" -->
                An unexpected error occurred, please try again.
              </div>
            </section>
          </div>
        </section-container>
      </div>
    </div>
  </div>
</template>

<script>
import { required, minLength, maxLength } from "vuelidate/lib/validators";

import isMobilePhone from "validator/lib/isMobilePhone";
import email from "vuelidate/lib/validators/email";
import isCreditCard from "validator/lib/isCreditCard";
import Spinner from "vue-simple-spinner";
import RowSeparator from "@/components/RowSeparator";
import SectionContainer from "@/components/SectionContainer";
import WarningContainer from "@/components/WarningContainer";

const isValidPhone = (value) => isMobilePhone(value, "en-US");
const isValidCreditCard = (value) => isCreditCard(value);

export default {
  components: {
    WarningContainer,
    SectionContainer,
    vueSpinner: Spinner,
    RowSeparator,
  },
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
      this.$v.$touch(); // Ensures validators always run
      if (this.$v.$invalid) {
        this.checkoutStatus = "ERROR";
      } else {
        this.checkoutStatus = "PENDING";
        setTimeout(() => {
          this.$store
            .dispatch("placeOrder", {
              name: this.name,
              address: this.address,
              phone: this.phone.replace(/\D/g, ""), // Remove all non-numeric characters before submitting
              email: this.email,
              ccNumber: this.ccNumber.replace(/\D/g, ""),
              ccExpiryMonth: this.ccExpiryMonth,
              ccExpiryYear: this.ccExpiryYear,
            })
            .then(() => {
              this.checkoutStatus = "OK";
              this.$router.push({ name: "confirmation" });
            })
            .catch((reason) => {
              this.checkoutStatus = "SERVER_ERROR";
              console.log("Error placing order", reason);
            });
        }, 2000);
      }
    },

    /* NOTE: For example yearFrom(0) == 2021 */
    yearFrom(index) {
      return new Date().getFullYear() + index;
    },
  },
  watch: {
    // auto-dash credit card number input
    ccNumber() {
      let realNumber = this.ccNumber.replace(/-/gi, "");

      // Generate dashed number
      let dashedNumber = realNumber.match(/.{1,4}/g);

      // Replace the dashed number with the real one
      this.ccNumber = dashedNumber.join("-");
    },
    phone() {
      this.phone = this.$options.filters.formatPhoneNumber(this.phone);
    },
  },
};
</script>

<style scoped>
.section-container:nth-child(1) {
  width: 70%;
}

.section-container:nth-child(2) {
  width: 30%;
}

.checkout-summary {
  display: flex;
  flex-direction: column;
  gap: 1em;
  justify-content: space-between;
}

form {
  display: flex;
  flex-direction: column;
  padding: 0 2em;
  max-width: 30em;
}

form > div {
  display: grid;
  grid-template-columns: 1fr 4fr;
  margin-bottom: 0.5em;
  align-items: baseline;
}

form > div > div {
  margin-left: 0.5em;
  display: flex;
  flex-direction: column;
}

form > div > div > input,
form > div > div > select {
  background-color: var(--primary-background-color);
  border: none;
  padding: 6px 12px;
  color: var(--neutral-color-dark);
  text-overflow: ellipsis;
}

form > div > div > .error {
  margin-top: 0.2em;
}

.expiration-date-input-container {
  display: grid;
  grid-template-columns: repeat(2, auto);
  grid-column-gap: 0.3em;
}

.checkout-summary > div {
  display: flex;
  flex-direction: column;
  gap: 0.5em;
}

.checkout-summary > div > div {
  display: flex;
  justify-content: space-between;
}

.checkout-summary .button {
  align-self: center;
  margin-top: 1em;
}

.checkoutStatusBox {
  margin: 1em;
  padding: 1em;
  text-align: center;
}

.loading-spinner-container {
  padding: 0 !important;
}

@media (max-width: 920px) {
  form {
    padding: 0 1em;
  }

  form > div {
    display: flex;
    flex-direction: column;
  }

  form > div > div {
    margin-left: 0;
  }
}
</style>
