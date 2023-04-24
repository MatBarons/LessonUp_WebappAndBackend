<template>
  <div class="row-user">
    <h3>{{ user.name }}</h3>
    <h3>{{ user.surname }}</h3>
    <h3>{{ user.email }}</h3>
    <h3>{{ user.password }}</h3>
    <h3>{{ user.role }}</h3>
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
  h3{
    margin-right: 40rem;
  }
  button{
    background: red;
  }
}
</style>