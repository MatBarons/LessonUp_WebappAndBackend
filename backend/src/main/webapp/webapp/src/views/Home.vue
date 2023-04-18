<template>
  <main id="homepage">
    <div class="left-side-homepage">
      <h2>Prenota ora le tue prossime lezioni!</h2>
      <div class="sumOfLessons">
        <div class="lesson" v-for="lecture in lectures">
          <CardLesson context="Homepage" :lesson="lecture"/>
        </div>
      </div>
    </div>
    <div class="right-side-homepage">
      <RadioButtons @setButton="setSelectedButton"/>
      <CustomCalendar :chosen-button="selectedButton" @setDate="setSelectedDate"/>
      <div class="sumOfSubjects">
        <div class="subject" v-for="course in courses" >
          <CardSubject :name="course" @setSubject="setSelectedCourse"/>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import CardLesson from "@/components/cards/CardLesson.vue";
import CardSubject from "@/components/cards/CardSubject.vue";
import CustomCalendar from "@/components/homepage_components/CustomCalendar.vue";
import RadioButtons from "@/components/homepage_components/RadioButtons.vue";

import moment from "moment/moment"

import {getActiveCourses} from "@/apiCalls/Subject"
import {getLecturesByDateAndSubject, getLecturesByStudentAndStatusAndDateAndSubject} from "@/apiCalls/Lecture"
import {store} from "@/apiCalls/User"

export default {
  name: "Home",
  components: {RadioButtons, CustomCalendar, CardSubject, CardLesson},
  data(){
    return{
      courses: [],
      lectures : [],
      selectedCourse: "",
      selectedDate: new Date(),
      selectedButton: ""
    }
  },
  methods:{
    format_date(value){
      if (value) {
        return moment(String(value)).format('yyyy-MM-DD')
      }
    },
    getCourses(){
      getActiveCourses().then(response => {
        this.courses = response.data
      })
    },
    getLectures(){
      if(this.selectedButton === "free"){
        getLecturesByDateAndSubject(this.selectedCourse,this.selectedDate).then(response => {
          this.lectures = response.data
        })
      }else{
        getLecturesByStudentAndStatusAndDateAndSubject(store.email,this.selectedButton,this.selectedDate,this.selectedCourse).then(response =>{
          this.lectures = response.data
        })
      }
    },
    setSelectedCourse(course){
      this.selectedCourse = course;
      this.getLectures()
    },
    setSelectedDate(date){
      date = this.format_date(date)
      this.selectedDate = date;
      this.getLectures()
    },
    setSelectedButton(value){
      this.selectedButton = value;
      this.getLectures()
    },
  },
  beforeMount() {
    this.selectedDate = Date.now()
    this.selectedCourse = "francese"
    this.getCourses()
    this.getLectures()
  }
}
</script>

<style lang="scss" scoped>
#homepage{
  padding-top: 1rem;
  padding-left: 1rem;
  display: flex;
  flex-direction: row;
  @media(max-width: 1024px){
    padding-left: 3rem;
  }
  .left-side-homepage{
    width: 50vw;
    display: flex;
    flex-direction: column;
    h2{
      color: var(--md-sys-color-background);
      font-size: 3.2rem;
      padding-left: 5rem;
      padding-bottom: 10px;
    }
    .sumOfLessons{
      display: flex;
      flex-wrap: wrap;
      .lesson{
        flex-basis:33.333333%;
        padding-bottom: 5rem;
        padding-right: 1rem;
      }
    }
  }
  .right-side-homepage{
    width: 35vw;
    display: flex;
    flex-direction: column;
    .sumOfSubjects{
      display: flex;
      flex-wrap: wrap;
      .subject{
        flex-basis: 50%;
        padding-top: 1rem;
      }
    }
  }
}

</style>