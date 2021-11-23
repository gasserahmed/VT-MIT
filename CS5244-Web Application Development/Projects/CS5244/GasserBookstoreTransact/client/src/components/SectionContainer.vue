<template>
  <div class="section-container">
    <section>
      <div class="section-header" v-if="title">
        <div>
          <h2 class="section-title">{{ title }}</h2>
          <router-link
            v-if="button"
            :to="button.routeTo"
            class="button link-like-button"
            tag="button"
          >
            {{ button.text }}
          </router-link>
        </div>
        <row-separator></row-separator>
        <div
          class="section-description"
          v-if="description"
          v-html="description"
        ></div>
      </div>
      <div class="section-body">
        <slot></slot>
      </div>
    </section>
  </div>
</template>

<script>
import RowSeparator from "@/components/RowSeparator";

export default {
  name: "SectionContainer",
  components: { RowSeparator },
  props: {
    title: String,
    description: String,
    button: Object,
  },
};
</script>

<style scoped>
.section-container {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding: 1em 1em 0.5em 1em;
}

.section-container > section {
  box-shadow: 0 0 5px #ccc;
}

.section-header {
  background: var(--secondary-background-color);
}

.section-header > div {
  display: flex;
  align-items: baseline;
  justify-content: space-between;
}

.section-title,
.section-description {
  color: var(--neutral-color-dark);
  padding: 1em;
}

.section-title {
  padding-left: 0.72em;
}

.section-description {
  font-weight: 100;
  font-size: 1.2em;
}

.section-body {
  background: var(--secondary-background-color);
  display: flex;
  flex-direction: column;
  padding: 1em;
}

@media (max-width: 920px) {
  .section-container {
    padding: 0.5em;
    width: 100% !important;
  }
}
</style>
