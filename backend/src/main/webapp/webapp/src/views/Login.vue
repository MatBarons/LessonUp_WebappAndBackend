<template>
<main id="login">
  <div class="main-part">
    <div class="left-side-login">
      <h1>Lesson Up</h1>
      <h3>Prenota oggi le tue prossime lezioni!</h3>
      <img src="src" alt="logo">
    </div>
    <div class="right-side-login">
      <form @submit.prevent>
        <label for="username"><b>Email</b></label>
        <input v-model="email" required>
        <label for="password"><b>Password</b></label>
        <input v-model="password" required>
        <button type="submit" class="btn-login" @click="login"></button>
      </form>
      <a href="url">Non hai un account? Registrati!</a>
      <div class="response-login">
        <i class="material-icons">{{ icon }}</i>
        <h3>{{ response }}</h3>
      </div>
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
      email: "Inserisci la tua email",
      password: "Inserisci la tua password",

      store,

      response: "Loggati ora per prenotare una lezione!",
      icon: "",
      colorResponse: "grey"
    }
  },
  methods:{
    Login(){
      let statusCode;
      logMeIn(this.email,this.password).then(response => {
        statusCode = response.statusCode()
      });
      if(statusCode === 200){
        store.setEmail(this.email);
        this.icon = "done"
        this.response = "Dati corretti"
        this.colorResponse = "green"
        router.push('/')
      }else{
        this.icon = "remove"
        this.response = "Username o password errati"
        this.colorResponse = "red"
      }
    },
  }
}
</script>

<style lang="scss" scoped>
  #login{
    width: 100vw;
    height: 100vh;
    align-content: center; //metti al centro la div
    .main-part{
      width: 75vw;
      height: 60vh;
      .left-side-login{
        width: 60%;
        height: 100%;
        background-color: forestgreen;
        h1{
          color: black;
        }
        h3{
          color: black;
        }
      }
      .right-side-login{
        width: 40%;
        height: 100%;
        form{
          label{
            height: 15px;
          }
          input{
            border-radius: 0.5rem;
          }
          .btn-login{
            color: black;
            background-color: blue;
          }
        }
        a{
          color: blue;
        }
        .response-login{
          color: v-bind(colorResponse)
        }
      }
    }
  }
</style>