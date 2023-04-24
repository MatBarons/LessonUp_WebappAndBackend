<template>
  <main id="lectures">
    <h1>Lista di Lezioni</h1>
    <div class="insert-new-lecture">
      <div class="insert-date">
        <label for="date">Data</label>
        <input v-model="lecture.date" name="date" required/>
      </div>
      <div class="insert-time">
        <label for="time">Ora</label>
        <input v-model="lecture.time" name="time" required/>
      </div>
      <div class="insert-prof">
        <label for="professor">Professore</label>
        <input v-model="lecture.professor" name="professor" required/>
      </div>
      <div class="insert-subject">
        <label for="subject">Materia</label>
        <input v-model="lecture.subject" name="subject" required/>
      </div>
      <div class="insert-status">
        <label for="status">Status</label>
        <input v-model="lecture.status" name="status" required/>
      </div>
      <div class="insert-student">
        <label for="student">Studente</label>
        <input v-model="lecture.student" name="student" required/>
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
      <select v-model="selectedStatus" name="status-choice" @change="handleChangeStatus">
        <option value="free">Libera</option>
        <option value="booked">Prenotata</option>
        <option value="completed">Completata</option>
        <option value="ended">Confermata</option>
      </select>
    </div>
    <div class="all-lectures" v-for="lecture in lectures">
      <RowLecture :lecture="lecture" :status="selectedStatus" :distance_right="distance_right"/>
      <hr>
    </div>
  </main>
</template>

<script>
import RowLecture from "@/components/admin/rows/RowLecture.vue";
import {getAllLecturesByStatus} from "@/apiCalls/Lecture";

export default {
  name: "Lectures",
  components: {RowLecture},
  data(){
    return{
      lectures: [],
      lecture: {
        date: "",
        time: "",
        professor: "",
        subject: "",
        status: "", //ricorda di mettere la scelta multipla
        student: "",  //elimina la possibilità di scrivere se lo status è free
      },
      selectedStatus: "free",
      distance_right: "21%"
    }
  },
  methods:{
    insertLecture(){

    },
    deleteLecture(){

    },
    getLectures(){
      console.log("siamo qui" + this.selectedStatus)
      getAllLecturesByStatus(this.selectedStatus).then(response =>{
       this.lectures = response.data
      })
    },
    handleChangeStatus(){
      this.getLectures()
      if(this.selectedStatus === "free"){
        this.distance_right = "21%"
      }else{
        this.distance_right = "15%"
      }
    }
  },
  beforeMount() {
    this.getLectures()
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
    margin-left: 9rem;
    padding-top: 2rem;
    padding-right: 1rem;
    padding-bottom: 3rem;
    .insert-date{
      display: flex;
      flex-direction: column;
      margin-left: 1rem;
      input{
        height: 2.5rem;
      }
    }
    .insert-time{
      display: flex;
      flex-direction: column;
      margin-left: 1rem;
      input{
        height: 2.5rem;
      }
    }
    .insert-prof{
      display: flex;
      flex-direction: column;
      margin-left: 1rem;
      input{
        height: 2.5rem;
      }
    }
    .insert-subject{
      display: flex;
      flex-direction: column;
      margin-left: 1rem;
      input{
        height: 2.5rem;
      }
    }
    .insert-status{
      display: flex;
      flex-direction: column;
      margin-left: 1rem;
      input{
        height: 2.5rem;
      }
    }
    .insert-student{
      display: flex;
      flex-direction: column;
      margin-left: 1rem;
      input{
        height: 2.5rem;
      }
    }
    button{
      width: 7rem;
      height: 3rem;
      background: #009b4d;
      color: white;
      border-radius: 0.5rem;
      margin-left: 2rem;
      margin-top: 1.2rem;
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