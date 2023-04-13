<template>
  <DatePicker columns="2" color="teal" v-model="date" />
</template>

<script >
import {defineComponent} from "vue";
import {DatePicker} from "v-calendar";
import 'v-calendar/style.css';
export default defineComponent({
  components: {DatePicker},
  emits: [],
  data(){
    return{
      date: ""
    }
  },
  methods:{
    getSelectedDate(){
      this.$emit('setDate',this.date)
    }
  }
})
</script>

<style scoped>

</style>