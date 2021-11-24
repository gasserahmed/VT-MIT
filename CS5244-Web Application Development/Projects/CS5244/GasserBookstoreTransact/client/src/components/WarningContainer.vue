<template>
  <section-container>
    <section>
      <img
        v-if="warningImage"
        :src="require('@/assets/images/site/' + warningImage.src)"
        :style="
          'height:' + warningImage.height + ';width:' + warningImage.width
        "
        alt="Warning image"
      />
      <span v-if="warningTitle" class="warning-title">{{ warningTitle }}</span>
      <span v-if="warningDescription">{{ warningDescription }}</span>
      <router-link
        v-if="warningButtonRoute"
        :to="warningButtonRoute"
        class="button"
        tag="button"
      >
        {{ warningButtonText }}
      </router-link>
    </section>
  </section-container>
</template>

<script>
import SectionContainer from "@/components/SectionContainer";
export default {
  name: "WarningContainer",
  components: { SectionContainer },
  props: {
    warningImage: {
      src: String,
      width: String,
      height: String,
    },
    warningTitle: String,
    warningDescription: String,
    warningButtonRoute: String,
    warningButtonText: String,
  },
};
</script>

<style scoped>
.section-container {
  width: 70%;
}

section {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  gap: 1em;
  padding: 1em;
}

.warning-title {
  background: var(--secondary-background-color);
  text-align: center;
  font-size: 1.5rem;
  font-weight: 500;
  color: var(--neutral-color-dark);
}

img {
  width: 100px;
  height: 100px;
}
</style>
