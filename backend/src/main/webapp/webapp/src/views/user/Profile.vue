<template>
<main id="profile">
  <div class="background-image-profile">
    <div class="profile-image">
    </div>
  </div>
  <h1>Martina Barone</h1>
  <div class="profile-stats">
    <div class="favorite-subject">
      <h1>Materia preferita: </h1>
      <h2>Francese</h2>
    </div>
    <div class="email">
      <h1>Email: </h1>
      <h2>{{store.email}}</h2>
    </div>
    <div class="number-of-completed-lectures">
      <h1>Numero di lezioni completate:</h1>
      <h2>{{this.numberOfLectures}}</h2>
    </div>
  </div>
  <button class="change-password" @click="showChangePasswordForm">Cambia password</button>
  <div class="new-password-form" v-if="showChangePassword">
    <form @submit.prevent>
      <div class="old-password">
        <label for="username">Vecchia password</label>
        <input v-model="oldPassword" name="username" placeholder="Inserisci la tua vecchia password" required>
      </div>
      <div class="new-password">
        <label for="password">NuovaPassword</label>
        <input v-model="newPassword" name="password" placeholder="Inserisci la tua nuova password" required>
      </div>
    </form>
    <div class="bottom-side-submit-change">
      <button class="submit-change" type="submit" @click="changePassword">Invia</button>
      <h3>{{changePasswordMessage}}</h3>
    </div>

  </div>
  <div v-else></div>
</main>
</template>

<script>
import {store} from "@/apiCalls/User";
import {getLecturesByStudentAndStatus} from "@/apiCalls/Lecture";
import {changePassword} from "@/apiCalls/User";

export default {
  name: "Profile",
  data(){
    return{
      store,

      numberOfLectures: 0,
      subject: "",
      changePasswordMessage: "",
      changePasswordMessageColor: "grey",

      completedLectures: [],
      endedLectures: [],

      oldPassword: "",
      newPassword: "",
      showChangePassword: false,

    }
  },
  methods:{
    numberOfCompletedLectures(){
      getLecturesByStudentAndStatus(store.email,"completed").then(response =>{
        this.numberOfLectures += response.data.length
        this.completedLectures = response.data
      })
    },
    numberOfEndedLectures(){
      getLecturesByStudentAndStatus(store.email,"ended").then(response =>{
        this.numberOfLectures += response.data.length
        this.endedLectures = response.data
      })
    },
    showChangePasswordForm(){
      this.showChangePassword = true
    },
    changePassword(){
      changePassword(store.email,this.oldPassword,this.newPassword).then(response =>{
        this.showChangePassword = false
        this.changePasswordMessage = "Password modificata correttamente"
        this.changePasswordMessageColor = "green"
      }).catch(reason => {
        this.changePasswordMessage = "Errore nella modifica"
        this.changePasswordMessageColor = "red"
      })

    }
  },
  beforeMount() {
    this.numberOfCompletedLectures()
    this.numberOfEndedLectures()
  }
}
</script>

<style lang="scss" scoped>
#profile{
  width: 88vw;
  height: 100vh;
  .background-image-profile{
    background: red;
    width: 100%;
    height: 40%;
    .profile-image{
      width: 20%;
      height: 70%;
      background: yellow;
      border-radius: 50%;
      top: 67.5%;
      margin-left: 37.5%;
    }
  }
  h1{
    font-size: 60px;
    font-weight: bolder;
    margin-top: 12%;
    margin-left: 35%;
  }
  .profile-stats{
    padding-top: 3rem;
    display: flex;
    flex-direction: row;
    .favorite-subject{
      display: flex;
      flex-direction: row;
      width: 33%;
      height: 20%;
      align-items: center;
      h1{
        font-size: 30px;
        font-weight: bold;
        margin-left: 10px;
        margin-top: 0px;
        margin-right: 1rem;
      }
      h2{
        font-size: 20px;
        margin-top: 3px;
      }
    }
    .email{
      display: flex;
      flex-direction: row;
      width: 33%;
      height: 20%;
      align-items: center;
      h1{
        font-size: 30px;
        font-weight: bold;
        margin-left: 10px;
        margin-top: 0px;
        margin-right: 1rem;
      }
      h2{
        font-size: 20px;
        margin-top: 3px;
      }
    }
    .number-of-completed-lectures{
      display: flex;
      flex-direction: row;
      width: 33%;
      height: 20%;
      align-items: center;
      h1{
        font-size: 30px;
        font-weight: bold;
        margin-left: 10px;
        margin-top: 0px;
        margin-right: 1rem;
      }
      h2{
        font-size: 20px;
        margin-top: 3px;
      }
    }
  }
  .change-password{
    width: 30%;
    height: 10%;
    background: #ffcc00;
    margin-top: 5%;
    margin-left: 32.5%;
    font-size: 30px;
    font-weight: bold;
    border-radius: 0.5rem;
  }
  .new-password-form{
    margin-top: 2rem;
    margin-left: 25%;
    display: flex;
    flex-direction: column;
    form {
      width: 100%;
      display: flex;
      .old-password{
        margin-right: 3rem;
        label {
          margin-right: 1rem;
          height: 15px;
          padding-bottom: 2rem;
          font-weight: bold;
        }
        input {
          font-size: 20px;
          height: 3rem;
          width: 15rem;
        }
      }
      .new-password{
        label {
          margin-right: 1rem;
          height: 15px;
          padding-bottom: 2rem;
          font-weight: bold;
        }
        input {
          font-size: 20px;
          height: 3rem;
          width: 15rem;
        }
      }
    }
    .bottom-side-submit-change{
      .submit-change{
        margin-top: 1rem;
        margin-left: 20rem;
        width: 15%;
        height: 3rem;
        background: #009b4d;
        border-radius: 0.5rem;
      }
      h3{
        color: v-bind(changePasswordMessageColor);
      }
    }
  }
}


</style>