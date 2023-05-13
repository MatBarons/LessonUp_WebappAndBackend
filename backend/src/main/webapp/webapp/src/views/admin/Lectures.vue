<template>
  <main id="lectures">
    <h1>Lista di Lezioni</h1>
    <div class="insert-new-lecture">
      <div class="insert-date">
        <label for="date">Data</label>
        <DatePicker v-model="lecture.date" color="green" @click="log"/>
        <!--<input v-model="lecture.date" name="date" required/> -->
      </div>
      <div class="insert-time">
        <label for="time">Ora</label>
        <select v-model="lecture.time" name="time" required >
          <option v-for="hour in hours"  :value="hour">{{hour}}</option>
        </select>
      </div>
      <div class="insert-prof">
        <label for="professor">Professore</label>
        <select v-model="lecture.professor" name="professor" required @click="getSubjects">
          <option v-for="professor in professors"  :value="professor">{{professor}}</option>
        </select>
      </div>
      <div class="insert-subject">
        <label for="subject">Materia</label>
        <select v-model="lecture.subject" name="subject" required @click="getProfessors">
          <option v-for="subject in subjects" :value="subject">{{subject}}</option>
        </select>
      </div>
      <div class="insert-status">
        <label for="status">Status</label>
        <select v-model="lecture.status" name="status" required @change="changeDisplayedStudentsValue">
          <option value="free">Libera</option>
          <option value="booked">Prenotata</option>
          <option value="completed">Completata</option>
          <option value="ended">Confermata</option>
        </select>
      </div>
      <div class="insert-student-free" v-if="showStudentList === false" >
        <label>Studente</label>
        <h2>null</h2><!-- Modifica qui per rimuovere l'inserimento -->
      </div>
      <div class="insert-student-other" v-else>
        <label for="student">Studente</label>
        <select v-model="lecture.student" required>
          <option v-for="student in students" :value="student.email">{{student.email}}</option>
        </select>
      </div>
      <button @click="insertLecture">Invia</button>
    </div>
    <div class="lecture-variables">
      <h3>Data</h3>
      <h3>Ora</h3>
      <h3>Professore</h3>
      <h3>Materia</h3>
      <h3>Status</h3>
      <h3>Studente</h3>
      <select v-model="selectedStatusBottomPart" name="status-choice" @change="handleChangeStatus">
        <option value="free">Libera</option>
        <option value="booked">Prenotata</option>
        <option value="completed">Completata</option>
        <option value="ended">Confermata</option>
      </select>
    </div>
    <div class="all-lectures" v-for="lecture in lectures">
      <RowLecture :lecture="lecture" :status="selectedStatusBottomPart" :distance_right="distance_right" @deleteLecture="deleteLecture"/>
      <hr>
    </div>
  </main>
</template>

<script>
import RowLecture from "@/components/admin/rows/RowLecture.vue";
import {deleteLecture, getAllLecturesByStatus, insertLecture} from "@/apiCalls/Lecture";
import {getAllUsersByRole} from "@/apiCalls/User";
import {
  getAllProfessor,
  getAllSubject,
  getProfessorsBySubject, getSubjectsByProfessor
} from "@/apiCalls/Teaching";
import {Calendar, DatePicker} from "v-calendar";
import {format_date, parse_string_to_date, parse_string_to_time} from "@/utils/Utils";

export default {
  name: "Lectures",
  components: {DatePicker, Calendar, RowLecture},
  data(){
    return{
      lectures: [],
      students: [],
      subjects: [],
      professors: [],
      hours: ["14:00","15:00","16:00","17:00","18:00"],
      lecture: {
        date: "",
        time: "",
        professor: "",
        subject: "",
        status: "free",
        student: "",
      },
      selectedStatusBottomPart: "free",
      distance_right: "21%",
      showStudentList: true,
    }
  },
  methods:{
    insertLecture(){
      let newDate = format_date(this.lecture.date)
      let newTime = parse_string_to_time(this.lecture.time)
      const newLecture = {
        date: newDate,
        time: newTime,
        professor: this.lecture.professor,
        subject: this.lecture.subject,
        status: this.lecture.status,
        student: this.lecture.student
      }
      insertLecture(newLecture).then(response =>{

      })
    },
    deleteLecture(lecture){
      deleteLecture(lecture.date,lecture.time,lecture.professor,lecture.subject).then(response =>{

      })
    },
    getLectures(){
      getAllLecturesByStatus(this.selectedStatusBottomPart).then(response =>{
       this.lectures = response.data
      })
    },
    getStudents(){
      getAllUsersByRole("student").then(response => {
        this.students = response.data
      })
    },
    getSubjects(){
      if(this.lecture.professor === ""){
        getAllSubject().then(response =>{
          this.subjects = response.data
          this.subjects.push("")
        })
      }else {
        getSubjectsByProfessor(this.lecture.professor).then(response =>{
          this.subjects = response.data
          this.subjects.push("")
        })
      }
    },
    getProfessors(){
      if(this.lecture.subject === ""){
        getAllProfessor().then(response =>{
          this.professors = response.data
          this.professors.push("")
        })
      }else {
        getProfessorsBySubject(this.lecture.subject).then(response =>{
          this.professors = response.data
          this.professors.push("")
        })
      }
    },
    handleChangeStatus(){
      this.getLectures()
      if(this.selectedStatusBottomPart === "free"){
        this.distance_right = "21%"
      }else{
        this.distance_right = "15%"
      }
    },
    changeDisplayedStudentsValue(){
      if(this.lecture.status === "free"){
        this.showStudentList = false;
        this.lecture.student = null;
      }else{
        this.showStudentList = true;
      }
    }
  },
  beforeMount() {
    this.getLectures()
    this.getStudents()
    this.changeDisplayedStudentsValue()
    this.getSubjects()
    this.getProfessors()
  }
}
</script>

<style lang="scss" scoped>
#lectures{
  margin-top: 1rem;
  h1{
    margin-left: 9rem;
    margin-bottom: 2rem;
    font-size: 50px;
    font-weight: bold;
  }
  .insert-new-lecture{
    box-shadow: 0 0 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);
    border-radius: 0.5rem;
    display: flex;
    flex-direction: row;
    background: white;
    align-self: center;
    margin-left: 6rem;
    padding-top: 2rem;
    padding-right: 1rem;
    padding-bottom: 3rem;
    .insert-date{
      display: flex;
      flex-direction: column;
      margin-left: 4.5rem;
    }
    .insert-time{
      display: flex;
      flex-direction: column;
      margin-left: 4rem;
      align-self: center;
      select{
        height: 2.5rem;
        width: 5rem;
      }
    }
    .insert-prof{
      display: flex;
      flex-direction: column;
      margin-left: 4rem;
      align-self: center;
      select{
        height: 2.5rem;
      }
    }
    .insert-subject{
      display: flex;
      flex-direction: column;
      margin-left: 4.5rem;
      align-self: center;
      select{
        height: 2.5rem;
      }
    }
    .insert-status{
      display: flex;
      flex-direction: column;
      margin-left: 4rem;
      align-self: center;
      select{
        height: 2.5rem;
      }
    }
    .insert-student-free{
      display: flex;
      flex-direction: column;
      margin-left: 4rem;
      align-self: center;
      width: 8rem;
    }
    .insert-student-other{
      display: flex;
      flex-direction: column;
      margin-left: 4rem;
      align-self: center;
      select{
        height: 2.5rem;
        width: 8rem;
      }
    }
    button{
      width: 7rem;
      height: 3rem;
      background: #009b4d;
      color: white;
      border-radius: 0.5rem;
      margin-right: 3rem;
      margin-left: 3rem;
      margin-top: 1.2rem;
      align-self: center;
    }
  }
  .lecture-variables{
    display: flex;
    flex-direction: row;
    margin-top: 2rem;
    margin-bottom: 2rem;
    h3{
      padding-top: 1rem;
      padding-left: 9.25rem;
      font-weight: bold;
      font-size: 20px;
    }
    select{
      margin-left: 7.5rem;
      width: 6rem;
      height: 3rem;
      font-size: 13px;
    }
  }
  .all-lectures{
    display: flex;
    flex-direction: column;
    background: white;
    margin-left: 4rem;
    width: 100%;
  }
}
</style>