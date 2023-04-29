<template>
  <main id="teachings">
    <h1>Associazioni professore materia</h1>
    <div class="insert-new-association">
      <div class="insert-professor">
        <label for="email-choice">Professore</label>
        <select v-model="email" name="email-choice" required>
          <option v-for="professor in allProfessors" :value="professor.email">{{professor.email}}</option>
        </select>
      </div>
      <div class="insert-course">
        <label for="course-choice">Materia</label>
        <select v-model="course" name="course-choice" required>
          <option v-for="course in allCourses" :value="course.name">{{course.name}}</option>
        </select>
      </div>
      <button @click="insertAssociation">Invia</button>
    </div>
    <div class="association-variables">
      <h3 class="email-variable">Email</h3>
      <h3 class="subject-variable">Materia</h3>
      <select v-model="selectedSubject" name="status-choice" @change="handleSubjectChange">
        <option v-for="course in allCourses" :value="course.name">{{ course.name }}</option>
      </select>
    </div>
    <div class="all-associations" v-for="professor in professors">
      <RowTeaching :email="professor" :subject="selectedSubject" />
      <hr>
    </div>
  </main>
</template>

<script>
import {getAllUsersByRole, getProfessorsBySubject} from "@/apiCalls/User";
import {getAllCourses} from "@/apiCalls/Subject";
import RowTeaching from "@/components/admin/rows/RowTeaching.vue";

export default {
  name: "Teachings",
  components: {RowTeaching},
  data(){
    return{
      email: "",
      course: "",
      allCourses: [],
      selectedSubject: "francese",
      professors: [],
      allProfessors: [],
    }
  },
  methods:{
    handleSubjectChange(){
      this.getProfessors()
    },
    insertAssociation(){

    },
    getProfessors(){
      getProfessorsBySubject(this.selectedSubject).then(response =>{
        this.professors = response.data
      })
    },
    getAllCourses(){
      getAllCourses().then(response =>{
        this.allCourses = response.data
      })
    },
    getAllProfessors(){
      getAllUsersByRole("professor").then(response =>{
        this.allProfessors = response.data
      })
    }
  },
  beforeMount() {
    this.getProfessors()
    this.getAllCourses()
    this.getAllProfessors()
  }
}
</script>

<style lang="scss" scoped>
#teachings{
  margin-top: 1rem;
  max-width: 100%;
  width: 80vw;
  h1{
    margin-left: 9rem;
    margin-bottom: 2rem;
    font-size: 50px;
    font-weight: bold;
  }
  .insert-new-association{
    box-shadow: 0 0 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);
    border-radius: 0.5rem;
    display: flex;
    flex-direction: row;
    background: white;
    align-self: center;
    margin-left: 9rem;
    padding-top: 2rem;
    padding-right: 1rem;
    padding-bottom: 3rem;
    align-items: center;
    .insert-professor{
      display: flex;
      flex-direction: column;
      margin-left: 1rem;
      select{
        height: 2.5rem;
        font-size: 20px;
      }
    }
    .insert-course{
      display: flex;
      flex-direction: column;
      margin-left: 30rem;
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
      margin-left: 20rem;
    }
  }
  .association-variables{
    display: flex;
    flex-direction: row;
    margin-top: 2rem;
    margin-left: 9rem;
    margin-bottom: 3rem;
    .email-variable{
      padding-left: 6rem;
      font-size: 30px;
      font-weight: bold;
    }
    .subject-variable{
      padding-left: 32rem;
      padding-right: 20rem;
      font-size: 30px;
      font-weight: bold;
    }
    select{
      margin-left: 7.5rem;
      width: 6rem;
      height: 3rem;
      font-size: 13px;
      .options{

      }
    }
  }
  .all-associations{
    display: flex;
    flex-direction: column;
    background: white;
    margin-left: 4rem;
    width: 100%;
  }
}
</style>