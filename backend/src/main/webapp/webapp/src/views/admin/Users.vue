<template>
  <main id="users">
    <h1>Lista di Utenti</h1>
    <div class="insert-new-user">
      <div class="insert-name">
        <label for="name">Nome</label>
        <input v-model="name_user" name="name" required/>
      </div>
      <div class="insert-surname">
        <label for="surname">Cognome</label>
        <input v-model="surname_user" name="surname" required/>
      </div>
      <div class="insert-email">
        <label for="email">Professore</label>
        <input v-model="email" name="email" required/>
      </div>
      <div class="insert-password">
        <label for="password">Password</label>
        <input v-model="password" name="password" required/>
      </div>
      <div class="insert-role">
        <label for="role">Ruolo</label>
        <input v-model="role" name="role" required/>
      </div>
      <button @click="insertUser">Invia</button>
    </div>
    <div class="user-variables">
      <h3>Nome</h3>
      <h3>Cognome</h3>
      <h3>Email</h3>
      <h3>Password</h3>
      <h3>Ruolo</h3>
      <select name="role-choice">
        <option value="student">Studente</option>
        <option value="professor">Professore</option>
        <option value="admin">Admin</option>
      </select>
    </div>
    <div class="all-users" v-for="user in users">
      <RowUser :user="user"/>
      <hr>
    </div>
  </main>
</template>

<script>
import RowUser from "@/components/admin/rows/RowUser.vue";

export default {
  name: "Users",
  components: {RowUser},
  data(){
    return{
      name_user: "",
      surname_user: "",
      email: "",
      password: "",
      role: "",
      users: []
    }
  },
  methods:{
    insertUser(){

    },
    deleteUser(){

    }
  }
}
</script>

<style lang="scss" scoped>
#users{
  margin-top: 1rem;
  h1{
    margin-left: 9rem;
    margin-bottom: 2rem;
    font-size: 50px;
    font-weight: bold;
  }
  .insert-new-user{
    box-shadow: 0 0 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);
    border-radius: 0.5rem;
    display: flex;
    flex-direction: row;
    background: white;
    align-self: center;
    height: 15%;
    margin-left: 9rem;
    padding-top: 2rem;
    padding-right: 1rem;
    .insert-name{
      display: flex;
      flex-direction: column;
      margin-left: 1rem;
      input{
        height: 2.5rem;
      }
    }
    .insert-surname{
      display: flex;
      flex-direction: column;
      margin-left: 3rem;
      input{
        height: 2.5rem;
      }
    }
    .insert-email{
      display: flex;
      flex-direction: column;
      margin-left: 3rem;
      input{
        height: 2.5rem;
      }
    }
    .insert-password{
      display: flex;
      flex-direction: column;
      margin-left: 3rem;
      input{
        height: 2.5rem;
      }
    }
    .insert-role{
      display: flex;
      flex-direction: column;
      margin-left: 3rem;
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
      margin-left: 7rem;
      margin-top: 1.2rem;
    }
  }
  .user-variables{
    display: flex;
    flex-direction: row;
    margin-top: 2rem;
    h3{
      padding-top: 1rem;
      padding-left: 11rem;
      font-weight: bold;
      font-size: 20px;
    }
    select{
      margin-left: 15rem;
      width: 6rem;
      height: 3rem;
      font-size: 13px;
    }
  }
  .all-users{
    display: flex;
    flex-direction: column;
    background: white;
  }
}
</style>