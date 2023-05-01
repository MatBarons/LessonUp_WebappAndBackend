<template>
  <button class="btn" @click="cancelBookedLecture">
    <i class="material-icons">{{icon}}</i>
    <h4>{{message}}</h4>
  </button>
</template>

<script>
import {changeStatusAndStudent} from "@/apiCalls/Lecture";
import {parse_string_to_date, parse_string_to_time} from "@/utils/Utils";

export default {
  name: "DeleteButton",
  props:{
    lesson:{
      type: Object,
      required: true
    }
  },
  data(){
    return{
      message: "Annulla la prenotazione",
      icon: "delete",
      cursor: "pointer",
      color: "red"
    }
  },
  methods:{
    cancelBookedLecture(){
      const lecture = {
        date: parse_string_to_date(this.lesson.date),
        time: parse_string_to_time(this.lesson.time),
        professor: this.lesson.email,
        subject: this.lesson.subject,
        status: "free",
        student: null
      }
      changeStatusAndStudent(lecture)
          .then(response => {
            this.message = "Prenotazione rimossa"
            this.icon = "check"
            this.cursor= "default"
            this.color = "#ffcc00"
          }).catch(reason => {
        console.log(reason)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.btn {
  background-color: v-bind(color);
  color: black;
  border: none;
  border-radius: 3px;
  padding: 10px 20px;
  cursor: v-bind(cursor);
  transition: background-color 0.3s ease;
  display: flex;
  align-content: center;
  h4{
    font-size: 13px;
    padding-left: 0.25rem;
  }
  &:hover{
    background-color: lighten(red,10);
  }
}
</style>