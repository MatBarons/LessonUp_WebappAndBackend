<template>
  <main id="homepage">
    <div class="left-side-homepage">
      <h1>Prenota ora le tue prossime lezioni!</h1>
      <div class="sumOfLessons" v-for="lecture in lectures">
        <CardLesson :lesson="lecture" @bookLecture=""/>
      </div>
    </div>
    <div class="right-side-homepage">
      <CustomCalendar @setDate="setSelectedDate"/>
      <div class="sumOfSubjects" v-for="course in courses">
        <CardSubject :name="course" @setSubject="setSelectedCourse"/>
      </div>
    </div>
  </main>
</template>

<script>
import CardLesson from "@/components/cards/CardLesson.vue";
import CardSubject from "@/components/cards/CardSubject.vue";
import CustomCalendar from "@/components/CustomCalendar.vue";
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
    getCourses(){
      getActiveCourses().then(response => {
        this.courses = response
      })
    },
    getLectures(){
      getLecturesByDateAndSubject(this.selectedCourse,this.selectedDate).then(response => {
        this.lectures = response
      })
    },
    setSelectedCourse(course){
      this.selectedCourse = course;
      this.getLectures()
    },
    setSelectedDate(date){
      this.selectedDate = date;
      this.getLectures()
    }
  },
  beforeCreate() {
    this.selectedDate = "2023-04-19"
    this.selectedCourse = "matematica"
    this.getCourses()
    this.getLectures(this.selectedCourse,this.selectedDate)
  },
}
</script>

<style lang="scss" scoped>
#homepage{
  padding-top: 10px;
  padding-left: 10px;
  display: flex;
  flex-direction: row;
  width: 70%;
  @media(max-width: 1024px){
    padding-left: 3rem;
  }
  .left-side-homepage{
    display: flex;
    flex-direction: column;
    h1{
      color: green;
      font-size: 50px;
    }
    .sumOfLessons{
     width: 80vh;
    }
  }
  .right-side-homepage{
    display: flex;
    flex-direction: column;
    padding: 1rem;
    .sumOfSubjects{

      display: flex;
      padding-top: 2rem;
    }
  }


}


</style>