<template>
  <div id="app">
    <app-header></app-header>
    <router-view :key="$route.fullPath"></router-view>
    <app-footer></app-footer>
  </div>
</template>

<script>
import AppHeader from "@/components/AppHeader";
import AppFooter from "@/components/AppFooter";
import { mapActions } from "vuex";

export default {
  name: "App",
  components: {
    AppHeader,
    AppFooter,
  },
  created: function () {
    this.fetchCategories();
  },
  methods: mapActions(["fetchCategories"]),
};
</script>

<style>
#app {
  display: flex;
  flex-direction: column;
  height: 100%;
}
</style>
