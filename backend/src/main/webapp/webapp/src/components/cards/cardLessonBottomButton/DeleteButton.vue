<template>
  <button class="btn" @click="cancelBookedLecture">
    <i class="material-icons">delete</i>
    <h4>Annulla la prenotazione</h4>
  </button>
</template>

<script>
export default {
  name: "DeleteButton",
  methods:{
    cancelBookedLecture(){

    }
  }
}
</script>

<style lang="scss" scoped>
.btn {
  background-color: red;
  color: #fff;
  border: none;
  border-radius: 3px;
  padding: 10px 20px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  display: flex;
  align-content: center;
  h4{
    padding-left: 0.25rem;
    padding-top: 0.3rem;
  }
  &:hover{
    background-color: lighten(red,1);
  }
}
</style>