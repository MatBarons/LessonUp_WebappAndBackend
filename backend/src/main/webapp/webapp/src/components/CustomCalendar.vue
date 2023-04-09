<template>
  <DatePicker columns="2" color="teal"/>
</template>

<script >
import {defineComponent} from "vue";
import {DatePicker} from "v-calendar";
import 'v-calendar/style.css';

export default defineComponent({
  components: {DatePicker}
})
</script>

<style scoped>

</style>