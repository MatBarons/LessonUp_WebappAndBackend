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
        <input v-model="activity" name="activity" required/>
      </div>
      <button @click="insertLecture">Invia</button>
    </div>
    <div class="courses-variables">
      <h3>Nome</h3>
      <h3>Attività</h3>
    </div>
    <div class="all-courses" v-for="course in courses">
      <RowCourse :course="course"/>
      <hr>
    </div>
  </main>
</template>

<script>
import RowCourse from "@/components/admin/rows/RowCourse.vue";

export default {
  name: "Courses",
  components: {RowCourse},
  data(){
    return{
      name: "",
      activity: ""
    }
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
    align-self: center;
    height: 15%;
    padding-top: 2rem;
    margin-left: 9rem;
    max-width: 85%;
    .insert-name{
      display: flex;
      flex-direction: column;
      margin-left: 3rem;
      margin-right: 32rem;
      input{
        height: 2.5rem;
      }
    }
    .insert-activity{
      display: flex;
      flex-direction: column;
      margin-left: 1rem;
      margin-right: 13rem;
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
      margin-top: 1.2rem;
      margin-right: 5rem;
    }
  }
  .courses-variables{
    display: flex;
    flex-direction: row;
    margin-top: 2rem;
    margin-left: 9rem;
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
  }
}
</style>