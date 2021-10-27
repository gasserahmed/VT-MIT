import Vue from "vue";
import Vuex from "vuex";
import ApiService from "@/services/ApiService";
import { ShoppingCart } from "@/models/ShoppingCart";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    categories: [],
    selectedCategoryName: "",
    selectedCategoryBooks: [],
    cart: new ShoppingCart(),
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
    },
    UPDATE_CART(state, { book, quantity }) {
      state.cart.update(book, quantity);
    },
    CLEAR_CART(state) {
      state.cart.clear();
    },
  },
  actions: {
    fetchCategories(context) {
      ApiService.fetchCategories()
        .then((categories) => {
          console.log("Categories: ", categories);
          context.commit("SET_CATEGORIES", categories);
        })
        .catch((reason) => {
          console.log("Error: " + reason);
        });
    },
    selectCategory(context, selectedCategoryName) {
      context.commit("SELECT_CATEGORY", selectedCategoryName);
    },
    fetchSelectedCategoryBooks(context) {
      ApiService.fetchSelectedCategoryBooks(this.state.selectedCategoryName)
        .then((books) => {
          console.log("Books: ", books);
          context.commit("SET_SELECTED_CATEGORY_BOOKS", books);
        })
        .catch((reason) => {
          console.log("Error: " + reason);
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
  },
});
