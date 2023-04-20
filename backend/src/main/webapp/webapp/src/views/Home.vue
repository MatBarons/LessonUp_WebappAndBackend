<template>
  <main id="homepage">
    <div class="left-side-homepage">
      <h2>Prenota ora le tue prossime lezioni!</h2>
      <div class="sumOfLessons">
        <div class="lesson" v-for="lecture in lectures">
          <CardLesson :context="selectedButton" :lesson="lecture"/>
        </div>
      </div>
    </div>
    <div class="right-side-homepage">
      <RadioButtons @setButton="setSelectedButton"/>
      <CustomCalendar :chosen-button="selectedButton" @setDate="setSelectedDate"/>
      <div class="sumOfSubjects" v-if="this.selectedButton === 'free'">
        <div class="subject" v-for="course in courses" >
          <CardSubject :name="course" @setSubject="setSelectedCourse"/>
        </div>
      </div>
      <div v-else>Non ne hai bisogno</div>
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
import {getLecturesByDateAndSubject, getLecturesByStudentAndStatus} from "@/apiCalls/Lecture"
import {store} from "@/apiCalls/User"
import {format_date} from "@/utils/Utils";

export default {
  name: "Home",
  components: {RadioButtons, CustomCalendar, CardSubject, CardLesson},
  data(){
    return{
      courses: [],
      lectures : [],
      selectedCourse: "",
      selectedDate: "",
      selectedButton: "free"
    }
  },
  methods:{
    format_date(value){
      return format_date(value)
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
        getLecturesByStudentAndStatus(store.email,this.selectedButton).then(response =>{
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
      if(value !== this.selectedButton){
        this.selectedButton = value;
        this.getLectures()
      }
    },
  },
  created() {
    this.selectedDate = this.format_date(new Date(Date.now()))
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
      width: 90.5%;
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