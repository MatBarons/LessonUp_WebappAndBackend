<template>
  <main id="courses">
    <h1>Lista di Corsi</h1>
    <div class="insert-new-course">
      <div class="insert-name">
        <label for="name">Nome</label>
        <input v-model="name" name="name" required/>
      </div>
      <div class="insert-activity">
        <label for="activity">Attività</label>
        <select v-model="activity" name="activity" required>
          <option value="true">Attiva</option>
          <option value="false">Inattiva</option>
        </select>
      </div>
      <button @click="insertCourse">Aggiungi</button>
    </div>
    <div class="response-insertion">{{responseText}}</div>
    <div class="courses-variables">
      <h3>Nome</h3>
      <h3>Attività</h3>
    </div>
    <div class="all-courses" v-for="course in courses">
      <RowCourse :course="course" @deleteCourse="toggleCourse"/>
      <hr>
    </div>
  </main>
</template>

<script>
import RowCourse from "@/components/admin/rows/RowCourse.vue";
import {getAllCourses, insertCourse, toggleCourse} from "@/apiCalls/Subject";

export default {
  name: "Courses",
  components: {RowCourse},
  data(){
    return{
      courses: [],
      name: "",
      activity: "",
      responseText: "",
    }
  },
  methods:{
    getCourses(){
      getAllCourses().then(response =>{
        this.courses = response.data
      })
    },
    insertCourse(){
      insertCourse(this.name,this.activity).then(response =>{
        this.responseText = "Corso inserito correttamente"
      })
    },
    toggleCourse(course){
      toggleCourse(course.name,course.activity).then(response =>{

      })
    }
  },
  beforeMount() {
    this.getCourses()
  }
}
</script>

<style lang="scss" scoped>
#courses{
  margin-top: 1rem;
  max-width: 100%;
  h1{
    margin-left: 9rem;
    margin-bottom: 2rem;
    font-size: 50px;
    font-weight: bold;
  }
  .insert-new-course{
    box-shadow: 0 0 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);
    border-radius: 0.5rem;
    display: flex;
    flex-direction: row;
    background: white;
    height: 15%;
    margin-left: 9rem;
    max-width: 85%;
    align-items: center;
    .insert-name{
      display: flex;
      flex-direction: column;
      margin-left: 3rem;
      margin-right: 27rem;
      margin-bottom: 1rem;
      input{
        height: 2.5rem;
        font-size: 20px;
      }
    }
    .insert-activity{
      display: flex;
      flex-direction: column;
      margin-left: 1rem;
      margin-right: 17rem;
      margin-bottom: 1rem;
      select{
        height: 2.5rem;
        font-size: 20px;
      }
    }
    button{
      width: 7rem;
      height: 3rem;
      background: #009b4d;
      color: white;
      border-radius: 0.5rem;
      margin-right: 5rem;
    }
  }
  .courses-variables{
    display: flex;
    flex-direction: row;
    margin-top: 2rem;
    margin-left: 9rem;
    margin-bottom: 3rem;
    h3{
      padding-top: 1rem;
      padding-left: 3rem;
      font-weight: bold;
      font-size: 20px;
      padding-right: 38rem;
    }
  }
  .all-courses{
    display: flex;
    flex-direction: column;
    background: white;
    margin-left: 4rem;
  }
}
</style>