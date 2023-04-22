<template>
  <button class="card" @click="onClick">
    <div class="card-title">
      <h2>{{ this.firstUpperCase(this.name) }}</h2>
      <span class="material-symbols-outlined">
        {{this.icon}}
      </span>
    </div>
    <div class="divider-line"/>
    <div class="card-footer">
      <p>{{this.quote}}</p>
    </div>
  </button>
</template>

<script>
import {capitalize} from "@/utils/Utils";
export default {
  name: "CardSubject",
  emits:['setSubject'],
  props:{
    name:{
      type: String,
      required: true
    }
  },
  data(){
    return{
      icon: "",
      quote: "",
      backgroundColor: "",
    }
  },
  mounted() {
    this.setupFooter(this.name)
  },
  methods:{
    onClick(){
      this.$emit('setSubject',this.name)
    },
    firstUpperCase(string){
      return capitalize(string)
    },
    setupFooter(string){
      switch (string){
        case "matematica":
          this.backgroundColor = "#FF8C00"
          this.icon = "calculate"
          this.quote = "Maths is the language of the universe"
          break;
        case "informatica" :
          this.backgroundColor = "#FF8C00"
          this.icon = "computer"
          this.quote = "Technology is best when it brings people together"
          break;
        case "scienze" :
          this.backgroundColor = "#FF8C00"
          this.icon = "science"
          this.quote = "Everything is impossible until it's done"
          break;
        case "letteratura" :
          this.backgroundColor = "#21AB12"
          this.icon = "school"
          this.quote = "Literature is a luxury; fiction is a necessity"
          break;
        case "storia" :
          this.backgroundColor = "#21AB12"
          this.icon = "history_edu"
          this.quote = "There is a History in all men's lives"
          break;
        case "geografia" :
          this.backgroundColor = "#21AB12"
          this.icon = "public";
          this.quote = "Geography is not just memorizing places"
          break;
        case "francese" :
          this.backgroundColor = "#2A5EFF"
          this.icon = "language_french";
          this.quote = "French is the language of love"
          break;
        case "inglese" :
          this.backgroundColor = "#2A5EFF"
          this.icon = "language_gb_english"
          this.quote = "English is the language of business"
          break;
        case "tedesco" :
          this.backgroundColor = "#2A5EFF"
          this.icon = "import_contacts"
          this.quote = "Learning German is what eternity is made for"
          break;
      }
    }
  }
}
</script>

<style lang="scss" scoped>
$background-color: v-bind(backgroundColor);
.card{
  background-color: v-bind(backgroundColor);
  box-shadow: 2px 2px 0px 0px grey;
  height: 8rem;
  width: 13rem;
  border-radius: 0.5rem;
  &:hover {
    background-color: white;
    color: v-bind(backgroundColor);
    .divider-line{
      background: v-bind(backgroundColor);
    }
    .card-title > h2{
      color: $background-color;
    }
    .card-footer > p{
      color: $background-color;
    }
  }
  &:active{
    box-shadow:inset 2px 2px 0px 0px grey;
    background-color: white;
  }
  .card-title{
    display: flex;
    align-items: center;
    padding-top: 8px;
    padding-left: 1rem;
    .material-symbols-outlined{
      padding-right: 1rem;
      padding-bottom: 1rem;
    }
    h2{
      padding-right: 5rem;
      padding-bottom: 10px;
      overflow: hidden;
      text-decoration-style: solid;
      color: black;
    }
  }
  .divider-line{
    width: 100%;
    height: 1px;
    background-color: black;
    color: black;
  }
  .card-footer{
    padding-left: 1rem;
    display: flex;
    flex-direction: row;
    padding-top: 1.5rem;
    padding-bottom: 2rem;
    p{
      color: black;
      font-size: 0.75rem;
    }
  }
  @media(max-width: 1024px){
    max-width: 8rem;
    max-height: 3rem;
    h2{
      padding-left: 1rem;
    }
    .card-footer{
      p{
        padding-left: 3rem;
      }
    }
  }
}

</style>