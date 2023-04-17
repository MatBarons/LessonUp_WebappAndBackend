<template>
  <div class="card">
    <div class="card-header">
      <h3>Prof. {{ this.firstUpperCase(lesson.name) }} {{ this.firstUpperCase(lesson.surname)}}</h3>
    </div>
    <hr>
    <div class="card-body">
      <div class="container-subject">
        <h4>{{ this.firstUpperCase(lesson.subject) }}</h4>
      </div>
      <div class="container-date">
        <h4>{{ lesson.date }}</h4>
      </div>
      <div class="container-time">
        <h4>{{ lesson.time }}</h4>
      </div>
    </div>
    <hr>
    <div class="card-footer">
      <div v-if="this.context === 'Homepage'">
        <BuyButton/>
      </div>
      <div v-else-if="this.context === 'Orders_ToBeConfirmed'">
        <ConfirmButton/>
      </div>
      <div v-else-if="this.context === 'Cart'">
        <RemoveButton/>
      </div>
      <div v-else-if="this.context=== ''">
        <DeleteButton/>
      </div>
      <div v-else>
        <AlreadyConfirmedButton/>
      </div>
    </div>
  </div>
</template>

<script>
import moment from 'moment';
import {capitalize} from "@/utils/Utils";
import BuyButton from "@/components/cards/cardLessonBottomButton/BuyButton.vue";
import RemoveButton from "@/components/cards/cardLessonBottomButton/RemoveButton.vue";
import ConfirmButton from "@/components/cards/cardLessonBottomButton/ConfirmButton.vue";
import AlreadyConfirmedButton from "@/components/cards/cardLessonBottomButton/AlreadyConfirmedButton.vue";
import DeleteButton from "@/components/cards/cardLessonBottomButton/DeleteButton.vue";
export default {
  name: 'CardLesson',
  components: {DeleteButton, AlreadyConfirmedButton, ConfirmButton, RemoveButton, BuyButton},
  emits: [],
  props: {
    lesson: {
      type: Object,
      required: true
    },
    context:{
      type: String,
      required: true
    }
  },
  methods: {
    firstUpperCase(string){
      return capitalize(string)
    },
    format_date(value){
      if (value) {
        return moment(String(value)).format('dd/MM/YYYY')
      }
    },
  },
  mounted() {
    console.log(this.lesson.date);
  }
}
</script>

<style lang="scss" scoped>
h3{
  color: black;
  font-weight: bolder;
}
.card {
  box-shadow: none;
  display: flex;
  flex-direction: column;
  border-radius: 5px;
  background-color: lightgrey;
  width: 13rem;
  max-height: 13rem;
  //clip-path: polygon(0% 0%,100% 5%, 100% 100%,100% 100%, 0% 100%);
  .card-header {
    padding: 10px;
    border-top-left-radius: 5px;
    border-top-right-radius: 5px;
    text-align: center;
    background: linear-gradient(135deg, white 20px, transparent 20px);
  }
  hr{
    height: 1rem;
    color: black;
    background-color: black;
  }
  .card-body {
    padding: 20px;
    .container-subject{
      color: black;
      display: flex;
      background-color: #007bff;
      border-radius: 5px;
      h4{
        padding-left: 3.5rem;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
      }
    }
    .container-date{
      padding-left: 3rem;
      color: black;
      display: flex;
      background-color: aquamarine;
      border-radius: 5px;
      margin-top: 0.5rem;
    }
    .container-time{
      padding-left: 3.5rem;
      color: black;
      display: flex;
      background-color: blueviolet;
      border-radius: 5px;
      margin-top: 0.5rem;
    }

  }
  .card-footer {
    background-color: lightgrey;
    padding: 10px;
    border-bottom-left-radius: 5px;
    border-bottom-right-radius: 5px;
    text-align: center;
  }

  @media (max-width: 1024px){
    max-width: 10rem;
    max-height: 20rem;
  }
}

</style>