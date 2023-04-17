<template>
  <button class="btn" @click="addToCart">
    <i class="material-icons">add_shopping_cart</i>
    <h4> Aggiungi al carrello</h4>
  </button>
</template>

<script>
export default {
  name: "BuyButton",
  emits: [],
  methods:{
    addToCart() {
      // Aggiungi la lezione al carrello
    },
  }
}
</script>

<style lang="scss" scoped>
.btn {
  background-color: cornflowerblue;
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
    background-color: #0069d9;
  }
}
</style>