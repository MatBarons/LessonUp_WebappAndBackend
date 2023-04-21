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
      <div v-if="this.context === 'free'">
        <BuyButton :lesson="lesson"/>
      </div>
      <div v-else-if="this.context === 'booked'">
        <DeleteButton :date="lesson.date" :time="lesson.time" :professor="lesson.email" :subject="lesson.subject"/>
      </div>
      <div v-else-if="this.context === 'completed'">
        <ConfirmButton :date="lesson.date" :time="lesson.time" :professor="lesson.email" :subject="lesson.subject"/>
      </div>
      <div v-else-if="this.context=== 'ended'">
        <AlreadyConfirmedButton/>
      </div>
      <div v-else-if="this.context === 'cart'">
        <RemoveButton :lesson="lesson" @removeFromCart="removeElement"/>
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
  emits: ['removeElement'],
  props: {
    lesson: {
      type: Object,
      required: true
    },
    context: {
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
    removeElement(value){
      this.$emit('removeElement',value)
    }
  },
}
</script>

<style lang="scss" scoped>
h3{
  color: white;
  font-weight: bolder;
}
.card {
  box-shadow: none;
  display: flex;
  flex-direction: column;
  border-radius: 5px;
  background-color: #009b4d;
  width: 13rem;
  max-height: 13rem;
  .card-header {
    display: flex;
    flex-direction: column;
    padding: 10px;
    border-top-left-radius: 5px;
    border-top-right-radius: 5px;
    text-align: center;
    background: linear-gradient(135deg, rgba(0, 0, 0, 0.06) 20px, transparent 20px);
  }
  hr{
    height: 1rem;
    color: #002201FF;
    background-color: #002201FF;
  }
  .card-body {
    padding: 20px;
    .container-subject{
      color: black;
      display: flex;
      background-color: #ffcc00;
      border-radius: 5px;
      h4{
        font-weight: bold;
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
      background-color: #ffcc00;
      border-radius: 5px;
      margin-top: 0.5rem;
      h4{
        font-weight: bold;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
      }
    }
    .container-time{
      padding-left: 3.5rem;
      color: black;
      display: flex;
      background-color: #ffcc00;
      border-radius: 5px;
      margin-top: 0.5rem;
      h4{
        font-weight: bold;
        display: flex;
        flex-direction: column;
        justify-content: center;
        padding-left: 0.7rem;
      }
    }

  }
  .card-footer {
    background: #009b4d;
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