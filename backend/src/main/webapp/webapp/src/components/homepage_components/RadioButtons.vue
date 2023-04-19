<template>
  <div class="selectType">
    <input type="radio" id="free" name="chosenType" value="free" v-model="valueButton" @change="setSelectedButton" checked/>
    <label for="free">Libera</label>
    <input type="radio" id="booked" name="chosenType" value="booked" v-model="valueButton" @change="setSelectedButton"/>
    <label for="booked">Prenotata</label>
    <input type="radio" id="completed" name="chosenType" value="completed" v-model="valueButton" @change="setSelectedButton"/>
    <label for="completed">Completata</label>
    <input type="radio" id="ended" name="chosenType" value="ended" v-model="valueButton" @change="setSelectedButton"/>
    <label for="ended">Confermata</label>
  </div>
</template>

<script>
import {ref} from "vue";
export default {
  name: "RadioButtons",
  data(){
    return{
        valueButton: ref(null)
    }
  },
  setup(){
    ref()
  },
  emits: ['setButton'],
  methods:{
    setSelectedButton(){
      console.log("Emissione setSelectedButton da RadioButtons" + this.valueButton)
      this.$emit('setButton',this.valueButton)
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