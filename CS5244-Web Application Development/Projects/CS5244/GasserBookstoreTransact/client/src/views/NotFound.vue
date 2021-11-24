<template>
  <div class="page-container">
    <div class="page-body">
      <warning-container
        :warning-image="{
          src: 'page-not-found.png',
          height: '50px',
        }"
        warning-title="Page not found!"
        warning-description="The page you were looking for was not found."
        :warning-button-route="'/home'"
        warning-button-text="Home Page"
      />
    </div>
  </div>
</template>

<script>
import WarningContainer from "@/components/WarningContainer";
export default {
  name: "NotFound",
  components: { WarningContainer },
};
</script>

<style scoped></style>
