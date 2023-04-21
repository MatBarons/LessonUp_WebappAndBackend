<template>
  <button class="btn" @click="addToCart">
    <i class="material-icons">{{icon}}</i>
    <h4>{{message}}</h4>
  </button>
</template>

<script>
import {store} from "@/apiCalls/User"
export default {
  name: "BuyButton",
  props:{
    lesson: {
      type: Object,
      required: true
    }
  },
  data(){
    return{
      message: store.isElementInCartList(this.lesson) ? "Aggiunto al carrello" : "Aggiungi al carrello",
      icon: store.isElementInCartList(this.lesson) ? "check" : "add_shopping_cart",
      store,
    }
  },
  methods:{
    addToCart() {
      if(store.isElementInCartList(this.lesson) === true){
        store.removeElementInCartList(this.lesson)
        this.message = "Aggiungi al carrello"
        this.icon = "add_shopping_cart"
      }else{
        store.addElementInCartList(this.lesson)
        this.message = "Aggiunto al carrello"
        this.icon = "check"
      }
    },
  },

}
</script>

<style lang="scss" scoped>
.btn {
  background-color: #ffcc00;
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
    background-color: lighten(#ffcc00,50);
  }
}
</style>