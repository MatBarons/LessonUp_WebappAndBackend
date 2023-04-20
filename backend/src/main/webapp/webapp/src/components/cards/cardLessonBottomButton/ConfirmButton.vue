<template>
  <button class="btn" @click="confirmLecture">
    <i class="material-icons">{{icon}}</i>
    <h4>{{message}}</h4>
  </button>
</template>

<script>
import {changeStatus} from "@/apiCalls/Lecture"
import {parse_string_to_date,parse_string_to_time} from "@/utils/Utils";
export default {
  name: "ConfirmButton",
  props:{
    date: null,
    time: null,
    professor: null,
    subject: null
  },
  data(){
    return{
      message: "Conferma la lezione",
      icon: "add_circle_outline",
      cursor: "pointer",
    }
  },
  methods:{
    confirmLecture(){
      changeStatus("ended", parse_string_to_date(this.date), parse_string_to_time(this.time), this.professor, this.subject)
      .then(response => {
        this.message = "Lezione confermata"
        this.icon = "check"
        this.cursor= "default"
      }).catch(reason => {
        console.log(reason)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.btn {
  background-color: #ffcc00;
  color: black;
  border: none;
  border-radius: 3px;
  padding: 10px 20px;
  cursor: v-bind(cursor);
  transition: background-color 0.3s ease;
  display: flex;
  align-content: center;
  h4{
    padding-left: 0.25rem;
    padding-top: 0.3rem;
  }
  &:hover{
     background-color: lighten(#ffcc00,50);
   }
}
</style>