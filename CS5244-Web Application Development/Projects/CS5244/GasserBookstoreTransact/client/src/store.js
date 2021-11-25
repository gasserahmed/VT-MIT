import Vue from "vue";
import Vuex from "vuex";
import ApiService from "@/services/ApiService";
import { ShoppingCart } from "@/models/ShoppingCart";

Vue.use(Vuex);

export const CART_STORAGE_KEY = "cart";
export const ORDER_DETAILS_STORAGE_KEY = "order_details";

export default new Vuex.Store({
  state: {
    categories: [],
    selectedCategoryName: "Arts",
    selectedCategoryBooks: [],
    cart: new ShoppingCart(),
    loadingStatus: false,
    orderDetails: null,
  },
  mutations: {
    SET_CATEGORIES(state, newCategories) {
      state.categories = newCategories;
    },
    SELECT_CATEGORY(state, selectedCategoryName) {
      state.selectedCategoryName = selectedCategoryName;
    },
    SET_SELECTED_CATEGORY_BOOKS(state, selectedCategoryBooks) {
      state.selectedCategoryBooks = selectedCategoryBooks;
    },
    ADD_TO_CART(state, book) {
      state.cart.addItem(book, 1);
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
    },
    UPDATE_CART(state, { book, quantity }) {
      state.cart.update(book, quantity);
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
    },
    CLEAR_CART(state) {
      state.cart.clear();
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
    },
    SET_CART(state, shoppingCart) {
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(shoppingCart));
      let newCart = new ShoppingCart();
      shoppingCart.items.forEach((item) => {
        newCart.addItem(item.book, item.quantity);
      });
      state.cart = newCart;
    },
    SET_LOADING_STATUS(state, loadingStatus) {
      state.loadingStatus = loadingStatus;
    },
    CLEAR_ORDER_DETAILS(state) {
      state.orderDetails = null;
      sessionStorage.setItem(
        ORDER_DETAILS_STORAGE_KEY,
        JSON.stringify(this.state.orderDetails)
      );
    },
    SET_ORDER_DETAILS(state, orderDetails) {
      state.orderDetails = orderDetails;
      sessionStorage.setItem(
        ORDER_DETAILS_STORAGE_KEY,
        JSON.stringify(this.state.orderDetails)
      );
    },
  },
  actions: {
    fetchCategories(context) {
      context.commit("SET_LOADING_STATUS", true);
      ApiService.fetchCategories()
        .then((categories) => {
          context.commit("SET_CATEGORIES", categories);
          context.commit("SET_LOADING_STATUS", false);
        })
        .catch((reason) => {
          console.log("Error: " + reason);
          context.commit("SET_LOADING_STATUS", false);
        });
    },
    selectCategory(context, selectedCategoryName) {
      context.commit("SELECT_CATEGORY", selectedCategoryName);
    },
    fetchSelectedCategoryBooks(context) {
      context.commit("SET_LOADING_STATUS", true);
      ApiService.fetchSelectedCategoryBooks(this.state.selectedCategoryName)
        .then((books) => {
          context.commit("SET_SELECTED_CATEGORY_BOOKS", books);
          context.commit("SET_LOADING_STATUS", false);
        })
        .catch((reason) => {
          console.log("Error: " + reason);
          context.commit("SET_LOADING_STATUS", false);
        });
    },
    addToCart(context, book) {
      context.commit("ADD_TO_CART", book);
    },
    updateCart(context, { book, quantity }) {
      context.commit("UPDATE_CART", { book, quantity });
    },
    clearCart(context) {
      context.commit("CLEAR_CART");
    },
    placeOrder({ commit, state }, customerForm) {
      return ApiService.placeOrder({
        cart: state.cart,
        customerForm: customerForm,
      }).then((orderDetails) => {
        commit("CLEAR_CART");
        commit("SET_ORDER_DETAILS", orderDetails);
      });
    },
  },
});
