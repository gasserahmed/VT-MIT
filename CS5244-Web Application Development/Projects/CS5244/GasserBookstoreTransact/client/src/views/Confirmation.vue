<template>
  <div class="page-container">
    <div
      class="page-body"
      :class="{ 'attached-sections': $store.state.orderDetails !== null }"
    >
      <warning-container
        v-if="!$store.state.orderDetails"
        :warning-image="{ src: 'empty-order-details.png' }"
        warning-title="You haven't placed an order yet!"
        warning-description="Once you have placed your order, you can find your order confirmation here."
        :warning-button-route="'/category/' + $store.state.selectedCategoryName"
        warning-button-text="Continue Shopping"
      ></warning-container>
      <template v-else>
        <section-container title="Thank you for your order!">
          <section class="confirmation-section">
            <span
              >Confirmation number:
              <strong>
                {{ $store.state.orderDetails.order.confirmationNumber }}
              </strong>
            </span>
            <span
              >Date:
              <strong>
                {{
                  $store.state.orderDetails.order.dateCreated
                    | formatDisplayDate
                }}
              </strong>
            </span>
            <span>
              Once your order is processed, we'll send a receipt and any related
              instructions to
              <strong>{{ $store.state.orderDetails.customer.email }}</strong
              >.
            </span>
          </section>
        </section-container>
        <section-container title="Invoice">
          <section class="invoice-section">
            <section class="shipping-section">
              <span><strong>Ship To</strong></span>
              <span>
                {{ $store.state.orderDetails.customer.customerName }}
              </span>
              <span> {{ $store.state.orderDetails.customer.address }}</span>
              <span>
                {{
                  $store.state.orderDetails.customer.phone | formatPhoneNumber
                }}</span
              >
              <span> {{ $store.state.orderDetails.customer.email }}</span>
            </section>
            <section class="payment-method-section">
              <span><strong>Payment Method</strong></span>
              <span>{{ $store.state.orderDetails.customer.customerName }}</span>
              <span>
                <i class="far fa-credit-card"></i>
                {{
                  $store.state.orderDetails.customer.ccNumber | formatCcLastFour
                }}
              </span>
              <span>
                {{
                  $store.state.orderDetails.customer.ccExpDate | formatCcExpDate
                }}
              </span>
            </section>
            <section class="order-summary-section">
              <span><strong>Order Summary</strong></span>
              <div>
                <span>Subtotal:</span>
                <span>
                  {{
                    ($store.state.orderDetails.order.amount -
                      $store.state.cart.surcharge)
                      | asDollarsAndCents
                  }}
                </span>
              </div>
              <div>
                <span>Shipping:</span>
                <span>
                  {{ $store.state.cart.surcharge | asDollarsAndCents }}
                </span>
              </div>
              <row-separator></row-separator>
              <div>
                <span>Total:</span>
                <span>
                  {{
                    $store.state.orderDetails.order.amount | asDollarsAndCents
                  }}
                </span>
              </div>
            </section>
          </section>
        </section-container>
        <section-container title="Order details">
          <confirmation-table></confirmation-table>
        </section-container>
        <section-container>
          <section class="footer-section">
            <router-link
              class="button"
              :to="'/category/' + $store.state.selectedCategoryName"
              tag="button"
            >
              Shop for More Experiments
            </router-link>
          </section>
        </section-container>
      </template>
    </div>
  </div>
</template>

<script>
import ConfirmationTable from "@/components/ConfirmationTable";
import SectionContainer from "@/components/SectionContainer";
import RowSeparator from "@/components/RowSeparator";
import WarningContainer from "@/components/WarningContainer";

export default {
  name: "Confirmation",
  components: {
    WarningContainer,
    RowSeparator,
    SectionContainer,
    ConfirmationTable,
  },
};
</script>

<style scoped>
.confirmation-section {
  display: flex;
  flex-direction: column;
  gap: 0.5em;
}

.invoice-section {
  display: flex;
  justify-content: space-around;
}

.invoice-section > section {
  display: flex;
  flex-direction: column;
  width: 25%;
}

.order-summary-section > div {
  display: flex;
  justify-content: space-between;
}

.footer-section {
  display: flex;
  justify-content: center;
}

@media (max-width: 750px) {
  .invoice-section {
    flex-direction: column;
    gap: 1em;
  }

  .invoice-section > section {
    width: 100%;
  }
}
</style>
