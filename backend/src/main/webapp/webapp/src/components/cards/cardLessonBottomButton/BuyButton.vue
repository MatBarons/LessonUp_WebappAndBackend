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
  emits: [],
  props:{
    date: null,
    time: null,
    professor: null,
    subject: null
  },
  data(){
    return{
      message: "Aggiungi al carrello",
      icon: "add_shopping_cart",
      cursor: "pointer",
      store
    }
  },
  methods:{
    addToCart() {
      store.setElementInCartList(
        {
          date: this.date,
          time: this.time,
          professor: this.professor,
          subject: this.subject
        }
      )
    },
  }
}
</script>

<style lang="scss" scoped>
.btn {
  background-color: #ffcc00;
  color: black;
  border: none;
  border-radius: 3px;
  padding: 10px 20px;
  cursor: v-bind(cursor);
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