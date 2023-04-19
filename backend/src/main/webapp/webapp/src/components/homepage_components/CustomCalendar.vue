<template>
  <DatePicker :min-date="minDate" :max-date="maxDate" columns=2 color="teal" :disabled-dates="disabledDates" v-model="date" @click="getSelectedDate"/>
</template>

<script >
import {defineComponent} from "vue";
import {DatePicker} from "v-calendar";
import 'v-calendar/style.css';
export default defineComponent({
  components: {DatePicker},
  emits: ['setDate'],
  props:{
    chosenButton:{
      type: String,
      required: true
    }
  },

  data(){
    return{
      date: Date.now(),
      minDate: new Date(2020, 1,1),
      maxDate: new Date(2023, 12, 31),
      disabledDates: [],

    }
  },
  methods:{
    getSelectedDate(){
      this.$emit('setDate',this.date)
    },
    updateDisabledDates(){
      let date = new Date();
      if(this.chosenButton === "free" || this.chosenButton === "booked"){
        this.disabledDates = [this.minDate,new Date(date.getFullYear(),date.getMonth(),date.getDay()-1)]
      }else{
        this.disabledDates = [new Date(date.getFullYear(),date.getMonth(),date.getDay()+1),this.maxDate]
      }
    }
  },
  beforeMount() {
    this.updateDisabledDates()
  }
})
</script>

<style scoped>

</style>