<template>
  <form>
    <input type="text" /><br />
    <input type="submit" value="Search" />
  </form>
</template>

<script>
export default {
  name: "HeaderSearchBar",
};
</script>

<style scoped>
input[type="text"] {
  margin: 0.5em 0;
  width: 12em;
}
</style>
