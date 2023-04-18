<template>
<main id="login">
  <div class="main-part">
    <div class="left-side-login">
      <h1>Lesson Up</h1>
      <h3>Prenota oggi le tue prossime lezioni!</h3>
      <img src="src" alt="logo">
    </div>
    <div class="right-side-login">
      <div class="response-login">
        <h3>Loggati ora per prenotare una lezione</h3>
      </div>
      <form @submit.prevent>
        <label for="username">Email</label>
        <input v-model="email" name="username" placeholder="Inserisci la tua email" required>
        <label for="password">Password</label>
        <input v-model="password" name="password" placeholder="Inserisci la tua password" required>
        <button type="submit" class="btn-login" @click="login">Login</button>
      </form>
      <a href="url">Non hai un account? Registrati!</a>
      <div class="response-error">{{response}}</div>
    </div>
  </div>
</main>
</template>

<script>
import {logMeIn,store} from "@/apiCalls/User"
import router from "@/router";
export default {
  name: "Login",
  data(){
    return{
      email: "",
      password: "",
      store,
      response: ""
    }
  },
  methods:{
    login(){
      let statusCode;
      logMeIn(this.email,this.password).then(response => {
        console.log("bene")
        store.setEmail(this.email);
        router.push('layout');
      }).catch(reason => {
        console.log("male")
        this.response="Credenziali errate"
      })

    },
  }
}
</script>

<style lang="scss" scoped>
  #login{
    width: 100vw;
    height: 100vh;
    padding-top: 15rem;
    padding-left: 23rem;
    .main-part{
      width: 60vw;
      height: 50vh;
      display: flex;
      border: 2px solid black;
      .left-side-login{
        width: 40%;
        height: 100%;
        background-color: forestgreen;
        h1{
          padding-top: 7rem;
          margin-left: 3rem;
          color: white;
          font-weight: bold;
          font-size: xx-large;
          padding-bottom: 0.5rem;
        }
        h3{
          margin-left: 3rem;
          color: white;
          font-weight: bold;
        }
      }
      .right-side-login{
        width: 60%;
        height: 100%;
        padding-left: 10rem;
        padding-top: 3rem;
        .response-login{
          padding-bottom: 1rem;
          color: v-bind(colorResponse)
        }
        form{
          width: 100%;
          display: flex;
          flex-direction: column;
          label{
            height: 15px;
            padding-bottom: 2rem;
          }
          input{
            font-size: 20px;
            height: 3rem;
            width: 15rem;
            padding-bottom: 2rem;
            &::placeholder{
              top: 1rem;
              right: 1rem;
            }
          }
          .btn-login{
            padding-top: 1rem;
            padding-bottom: 1rem;
            width: 30%;
            color: white;
            background-color: blue;
            margin-top: 2rem;
            margin-left: 3rem;
            margin-bottom: 2rem;
          }
        }
        a{
          color: blue;
        }
        .response-error{
          color: red;
        }
      }
    }
  }
</style>