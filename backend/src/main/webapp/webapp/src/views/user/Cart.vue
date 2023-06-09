<template>
  <main id="cart">
    <div class="left-side-cart">
      <h1>Carrello</h1>
      <div class="sumOfLessons">
        <div class="lesson" v-for="lecture in lectures">
          <CardLesson context="cart" :lesson="lecture" @removeElement="removeLessonFromCart"/>
        </div>
      </div>
    </div>
    <div class="right-side-cart">
      <div class="buy_area">
        <h3>Riepilogo</h3>
        <hr>
        <div class="numberOfLectures">
          <h3>Numero di lezioni: </h3>
          <h4> {{this.lectures.size}} </h4>
        </div>
        <div class="costPerLecture">
          <h3>Costo a lezione: </h3>
          <h4>15</h4>
        </div>
        <hr>
        <div class="totalCost">
          <h3>Costo totale: </h3>
          <h4>{{this.lectures.size * 15}}</h4>
        </div>
        <button class="btn-buy" @click="buyLessons">
          Compra
        </button>
        <div class="bottom-message">
          <i class="material-icons">{{icon}}</i>
          <h4>{{message}}</h4>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import CardLesson from "@/components/user/cards/CardLesson.vue";

import {store} from "@/apiCalls/User"
import {changeStatusAndStudent} from "@/apiCalls/Lecture";
import {parse_string_to_date, parse_string_to_time} from "@/utils/Utils";
import router from "@/router";

export default {
  name: "Cart",
  components: {CardLesson},
  data(){
    return{
      lectures: new Set(),
      message: "",
      icon: "",
      message_color: "",
      store
    }
  },
  methods:{
    getCartLectures(){
      this.lectures = store.cart_list
    },
    removeLessonFromCart(value){
      store.removeElementInCartList(value)
      this.lectures = store.cart_list
    },
    buyLessons(){
      if(this.lectures.size === 0){
        this.message = "Il carrello è vuoto"
        this.icon = "close"
        this.message_color = "red"
      }else{
        if(store.data.token === ''){
          router.push("/")
        }else{
          this.lectures.forEach(lecture => {
            const l = {
              date: parse_string_to_date(lecture.date),
              time: parse_string_to_time(lecture.time),
              professor: lecture.email,
              subject: lecture.subject,
              status: "booked",
              student: store.email
            }
            changeStatusAndStudent(l).then(
                response => {
                  store.cart_list.clear()
                  this.lectures = store.cart_list
                  this.message = "Grazie per aver acquistato"
                  this.icon = "check"
                  this.message_color = "#009b4d"
                }
            ).catch(
                reason => {
                  this.message = "Errore durante l'acquisto"
                  this.icon = "remove"
                  this.message_color = "red"
                }
            )
          })
        }
      }
    }
  },
  mounted() {
    this.getCartLectures()
  }
}
</script>

<style lang="scss" scoped>
#cart{
  height: 100vh;
  width: 88vw;
  display: flex;
  flex-direction: row;
  .left-side-cart{
    width: 50vw;
    padding-top: 2rem;
    padding-left: 2rem;
    display: flex;
    flex-direction: column;
    h2{
      color: var(--md-sys-color-background);
      font-size: 3.2rem;
      padding-left: 5rem;
      padding-bottom: 10px;
    }
    .sumOfLessons{
      display: flex;
      flex-wrap: wrap;
      .lesson{
        flex-basis:33.333333%;
        padding-bottom: 5rem;
        padding-right: 1rem;
      }
    }
  }
  .right-side-cart{
    width: 38vw;
    height: 100vh;
    .buy_area{
      margin-left: 25%;
      margin-top: 15%;
      width: 50%;
      height: 60%;
      display: flex;
      flex-direction: column;
      border: lightgrey 1px solid;
      border-radius: 0.5rem;
      box-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);
      background: lighten(lightgrey,12);
      h3{
        font-weight: bold;
        padding-top: 2rem;
        margin-left: 7.5rem;
        margin-bottom: 10px;
        font-size: 25px;
      }
      hr{
        width: 90%;
        margin-left: 1rem;
      }
      .numberOfLectures{
        margin-top: 1em;
        display: flex;
        flex-direction: row;
        align-items: center;
        h3{
          margin-left: 1rem;
        }
        h4{
          margin-top: 1.5rem;
          margin-left: 7rem;
          font-size: 20px;
        }
      }
      .costPerLecture{
        display: flex;
        flex-direction: row;
        align-items: center;
        margin-bottom: 4rem;
        h3{
          margin-left: 1rem;
        }
        h4{
          margin-top: 1.5rem;
          margin-left: 8rem;
          font-size: 20px;
        }
      }
      .totalCost{
        margin-top: 1rem;
        display: flex;
        align-items: center;
        h3{
          margin-left: 1rem;
        }
        h4{
          margin-top: 1.5rem;
          margin-left: 10rem;
          font-size: 20px;
        }
      }
      .btn-buy{
        font-size: 20px;
        font-weight: bold;
        margin: 1rem 2rem 2rem 2rem;
        height: 10%;
        background: #ffcc00;
        border-radius: 0.5rem;
      }
      .bottom-message{
        display: flex;
        flex-direction: row;
        align-items: center;
        padding-bottom: 0.5rem;
        .material-icons{
          padding-left: 2rem;
          color: v-bind(message_color);
        }
        h4{
          font-size: 20px;
          color: v-bind(message_color);
          padding-left: 3rem;
        }
      }
    }
  }
}
</style>