<template>
  <div class="selectType">
    <input type="radio" id="free" name="chosenType" value="free" checked @click="setSelectedButton"/>
    <label for="free">Libera</label>
    <input type="radio" id="booked" name="chosenType" value="booked"  @click="setSelectedButton"/>
    <label for="booked">Prenotata</label>
    <input type="radio" id="completed" name="chosenType" value="completed" @click="setSelectedButton"/>
    <label for="completed">Completata</label>
    <input type="radio" id="ended" name="chosenType" value="ended" @click="setSelectedButton"/>
    <label for="ended">Confermata</label>
  </div>
</template>

<script>
import * as $ from "jquery";
export default {
  name: "RadioButtons",
  emits: ['setButton'],
  methods:{
    setSelectedButton(){
      console.log("sbora" + $(".selectType input[name=chosenType]:checked").val())
      this.$emit('setButton',$(".selectType input[name=chosenType]:checked").val())
    }
  }
}
</script>

<style lang="scss" scoped>
.selectType{
  padding-bottom: 1rem;
  input{
    padding-left: 1rem;
    border: 0.15em solid currentColor;
    border-radius: 50%;
  }
  label{
    font-size: 1.3rem;
  }
}
</style>