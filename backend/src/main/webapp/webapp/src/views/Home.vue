<template>
  <main id="homepage">
    <div class="left-side-homepage">
      <h2>Prenota ora le tue prossime lezioni!</h2>
      <div class="sumOfLessons">
        <div class="lesson" v-for="lecture in lectures">
          <CardLesson :lesson="lecture"/>
        </div>
      </div>

    </div>
    <div class="right-side-homepage">
      <CustomCalendar @setDate="setSelectedDate"/>
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
import CustomCalendar from "@/components/CustomCalendar.vue";
import moment from "moment/moment"
import {getActiveCourses} from "@/model/Subject"
import {getLecturesByDateAndSubject} from "@/model/Lecture"

export default {
  name: "Home",
  components: {CustomCalendar, CardSubject, CardLesson},
  data(){
    return{
      courses: [],
      lectures : [],
      selectedCourse: "",
      selectedDate: ""
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
      getLecturesByDateAndSubject(this.selectedCourse,this.selectedDate).then(response => {
        this.lectures = response.data
      })
    },
    setSelectedCourse(course){
      this.selectedCourse = course;
      this.getLectures()
    },
    setSelectedDate(date){
      console.log("SUPER PROVA");
      date = this.format_date(date)
      this.selectedDate = date;
      this.getLectures()
    }
  },
  mounted() {
    this.selectedDate = "2023-04-19"
    this.selectedCourse = "francese"
    this.getCourses()
    this.getLectures()
  },
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
    width: 110vh;
    display: flex;
    flex-direction: column;
    h2{
      color: black;
      font-size: 50px;
    }
    .sumOfLessons{
      display: flex;
      flex-wrap: wrap;
      .lesson{
        flex-basis:25%;
        padding-bottom: 5rem;
        padding-right: 1rem;
      }
    }
  }
  .right-side-homepage{
    width: 65vh;
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