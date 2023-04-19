<template>
  <div class="selectType">
    <div class="free">
      <input type="radio" id="free" name="chosenType" value="free" v-model="valueButton" @change="setSelectedButton" checked/>
      <label for="free">Libera</label>
    </div>
    <div class="booked">
      <input type="radio" id="booked" name="chosenType" value="booked" v-model="valueButton" @change="setSelectedButton"/>
      <label for="booked">Prenotata</label>
    </div>
    <div class="completed">
      <input type="radio" id="completed" name="chosenType" value="completed" v-model="valueButton" @change="setSelectedButton"/>
      <label for="completed">Completata</label>
    </div>
    <div class="ended">
      <input type="radio" id="ended" name="chosenType" value="ended" v-model="valueButton" @change="setSelectedButton"/>
      <label for="ended">Confermata</label>
    </div>
  </div>
</template>

<script>
export default {
  name: "RadioButtons",
  data(){
    return{
        valueButton: null
    }
  },
  emits: ['setButton'],
  methods:{
    setSelectedButton(){
      this.$emit('setButton',this.valueButton)
    }
  }
}
</script>

<style lang="scss" scoped>
.selectType{
  padding-top: 1rem;
  display: flex;
  padding-bottom: 1rem;
  *{
    padding-left: 1rem;
    input[type='radio']:after {
      width: 20px;
      height: 20px;
      border-radius: 15px;
      top: -2px;
      left: -1px;
      position: relative;
      background-color: #d1d3d1;
      content: '';
      display: inline-block;
      visibility: visible;
      border: 2px solid white;
    }
    input[type='radio']:checked:after {
      width: 20px;
      height: 20px;
      border-radius: 15px;
      top: -2px;
      left: -1px;
      position: relative;
      background-color: #009b4d;
      content: '';
      display: inline-block;
      visibility: visible;
      border: 2px solid white;
    }
    label{
      font-size: 1.4rem;
    }
  }
}
</style>