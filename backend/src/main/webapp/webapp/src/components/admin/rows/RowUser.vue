<template>
  <div class="row-user">
    <h3 class="user-name">{{ user.name }}</h3>
    <h3 class="user-surname">{{ user.surname }}</h3>
    <h3 class="user-email">{{ user.email }}</h3>
    <h3 class="user-password">{{ user.password }}</h3>
    <h3 class="user-role">{{ user.role }}</h3>
    <h3 class="user-activity">{{user.isActive}}</h3>
    <button @click="toggleUser">Cambia stato</button>
  </div>
</template>

<script>
export default {
  name: "RowUser",
  emits: ['toggleUser'],
  props:{
    user:{
      type: Object,
      required: true
    },
  },
  methods:{
    toggleUser(){
      this.user.isActive = !this.user.isActive
      this.$emit('toggleUser',this.user)
    }
  },
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
    left: 8%;
    position: absolute;
  }
  .user-surname{
    left: 22%;
    position: absolute;
  }
  .user-email{
    left: 33%;
    position: absolute;
  }
  .user-password{
    right: 43%;
    position: absolute;
  }
  .user-role{
    right: 28%;
    position: absolute;
  }
  .user-activity{
    right: 15%;
    position: absolute;
  }
  button{
    height: 55%;
    width: 8%;
    position: absolute;
    right: 10px;
    background: red;
    border-radius: 0.5rem;
    font-size: 15px;
  }
}
</style>