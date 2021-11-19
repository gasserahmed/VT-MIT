<template>
  <div class="section-header">
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
    <div class="section-description" v-if="description">{{ description }}</div>
  </div>
</template>

<script>
import RowSeparator from "@/components/RowSeparator";
export default {
  name: "SectionHeader",
  components: { RowSeparator },
  props: {
    title: { type: String, required: true },
    description: String,
    button: Object,
  },
};
</script>

<style scoped>
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
</style>
