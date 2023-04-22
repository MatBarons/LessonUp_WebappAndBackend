<template>
  <button class="btn" @click="removeFromCart">
    <i class="material-icons">{{icon}}</i>
    <h4>{{message}}</h4>
  </button>
</template>

<script>
import {store} from "@/apiCalls/User";
export default {
  name: "RemoveButton",
  emits : ['removeFromCart'],
  props:{
    lesson:{
      type: Object,
      required: true
    }
  },
  data(){
    return{
      message: "Rimuovi dal carrello",
      icon: "close",
      store,
    }
  },
  methods:{
    removeFromCart(){
      console.log("removeButton 1")
      console.log(store.cart_list)
      console.log(this.lesson)
      if(store.isElementInCartList(this.lesson) === true){
        console.log("removeButton 2")
        this.$emit('removeFromCart',this.lesson)
      }
    }
  },
  mounted() {
    console.log(this.lesson.email)
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