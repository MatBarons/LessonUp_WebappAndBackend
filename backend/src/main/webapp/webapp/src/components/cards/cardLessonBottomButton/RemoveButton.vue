<template>
  <button class="btn" @click="removeFromCart">
    <i class="material-icons">close</i>
    <h4>Rimuovi dal carrello</h4>
  </button>
</template>

<script>
export default {
  name: "RemoveButton",
  methods:{
    removeFromCart(){

    }
  }
}
</script>

<style lang="scss" scoped>
.btn {
  background-color: red;
  color: black;
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
    background-color: lighten(red,10);
  }
}
</style>