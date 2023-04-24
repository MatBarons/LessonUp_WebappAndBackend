<template>
  <div class="row-user">
    <h3 class="user-name">{{ user.name }}</h3>
    <h3 class="user-surname">{{ user.surname }}</h3>
    <h3 class="user-email">{{ user.email }}</h3>
    <h3 class="user-password">{{ user.password }}</h3>
    <h3 class="user-role">{{ user.role }}</h3>
    <button @click="deleteUser">Elimina</button>
  </div>
</template>

<script>
export default {
  name: "RowUser",
  emits: ['deleteUser'],
  props:{
    user:{
      type: Object,
      required: true
    }
  },
  methods:{
    deleteUser(){
      this.$emit('deleteUser',this.user)
    }
  }
}
</script>

<style lang="scss" scoped>
.row-user{
  display: flex;
  flex-direction: row;
  height: 5rem;
  width: 100%;
  align-items: center;
  .user-name{
    left: 9%;
    position: absolute;
  }
  .user-surname{
    left: 24%;
    position: absolute;
  }
  .user-email{
    left: 35%;
    position: absolute;
  }
  .user-password{
    right: 40%;
    position: absolute;
  }
  .user-role{
    right: 24%;
    position: absolute;
  }
  button{
    height: 55%;
    width: 5%;
    position: absolute;
    right: 5px;
    background: red;
    border-radius: 0.5rem;
    font-size: 15px;
    margin-right: 2rem;
  }
}
</style>