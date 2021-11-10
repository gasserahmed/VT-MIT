<template>
  <div class="content-container">
    <EmptyCart v-if="cart.empty" />
    <div class="checkout-page" v-else>
      <section class="checkout-page-body" v-if="!cart.empty">
        <section class="checkout-container">
          <h2 class="checkout-title">Checkout</h2>
          <div class="row-separator"></div>
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
              <label for="name">Address</label>
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
                  v-model.lazy="$v.ccNumber.$model"
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
        </section>
        <section class="checkout-summary-container">
          <div class="checkout-summary-header">
            <h2 class="checkout-title">
              {{ $store.state.cart.numberOfItems }}
              <span v-if="$store.state.cart.numberOfItems === 1">item</span>
              <span v-else>items</span>
            </h2>
            <router-link
              class="button link-like-button edit-cart-button"
              to="/cart"
              tag="button"
            >
              Edit Cart
            </router-link>
          </div>
          <div class="row-separator"></div>
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
              <div class="row-separator"></div>
              <div class="bold-text">
                <span>Total</span>
                <span>{{
                  (cart.subtotal + cart.surcharge) | asDollarsAndCents
                }}</span>
              </div>
            </div>
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
        </section>
      </section>
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
    </div>
  </div>
</template>

<script>
import {
  required,
  // email,
  minLength,
  maxLength,
} from "vuelidate/lib/validators";

import EmptyCart from "@/components/EmptyCart";
import isMobilePhone from "validator/lib/isMobilePhone";
import email from "vuelidate/lib/validators/email";
import isCreditCard from "validator/lib/isCreditCard";

const isValidPhone = (value) => isMobilePhone(value, "en-US");
const isValidCreditCard = (value) => isCreditCard(value);

export default {
  components: { EmptyCart },
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
.checkout-page {
  display: flex;
  flex-direction: column;
  padding: 1em 5em 2.5em 5em;
}

.checkout-page-body {
  display: flex;
  padding: 1em;
  justify-content: space-around;
  gap: 1.5em;
}

.checkout-container {
  background: var(--secondary-background-color);
  width: 70%;
  box-shadow: 0 0 5px #ccc;
}

.checkout-summary-container {
  background: var(--secondary-background-color);
  box-shadow: 0 0 5px #ccc;
  width: 30%;
  height: 100%;
}

.checkout-summary-header {
  display: flex;
  align-items: baseline;
  justify-content: space-between;
}

.checkout-summary {
  display: flex;
  flex-direction: column;
  gap: 1em;
  padding: 1em;
  justify-content: space-between;
}

.checkout-title {
  color: var(--neutral-color-dark);
  padding: 1em 1em 1em 0.72em;
}

form {
  display: flex;
  flex-direction: column;
  padding: 1em 2em;
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

.checkout-summary > .button {
  align-self: center;
  margin-top: 1em;
}

.checkoutStatusBox {
  margin: 1em;
  padding: 1em;
  text-align: center;
}

@media (min-width: 841px) and (max-width: 960px) {
  .checkout-summary-header {
    flex-direction: column;
    align-items: center;
  }
  .checkout-summary-header .checkout-title {
    padding-bottom: 0;
    padding-top: 0.5em;
  }
}

@media (max-width: 840px) {
  .checkout-page-body {
    flex-direction: column;
  }

  .checkout-page,
  .checkout-page-body,
  .checkout-container,
  .checkout-summary-container {
    width: 100%;
    padding: 0.5em;
  }

  form {
    padding: 1em;
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
