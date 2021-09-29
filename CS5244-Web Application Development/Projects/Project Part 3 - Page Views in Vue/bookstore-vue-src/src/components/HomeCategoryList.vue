<template>
  <ul>
    <router-link to="../category/classics" tag="li">
      <img
        src="@/assets/images/categories/classics.jpg"
        alt="classics category"
      />
      <div>Classics</div>
    </router-link>
    <router-link to="../category/fantasy" tag="li">
      <img
        src="@/assets/images/categories/fantasy.jpg"
        alt="fantasy category"
      />
      <div>Fantasy</div>
    </router-link>
    <router-link to="../category/mystery" tag="li">
      <img
        src="@/assets/images/categories/mystery.jpg"
        alt="mystery category"
      />
      <div>Mystery</div>
    </router-link>
    <router-link to="../category/romance" tag="li">
      <img
        src="@/assets/images/categories/romance.jpg"
        alt="romance category"
      />
      <div>Romance</div>
    </router-link>
  </ul>
</template>

<script>
export default {
  name: "HomeCategoryList",
};
</script>

<style scoped>
ul {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 1em;
}

li {
  text-align: center;
  cursor: pointer;
}

li div {
  padding: 0.5em 0;
  background: rgba(0, 0, 0, 0.5); /* last # is percent opacity */
  color: white;
  transform: translateY(-2.25em);
  margin-bottom: -2em;
}
</style>
