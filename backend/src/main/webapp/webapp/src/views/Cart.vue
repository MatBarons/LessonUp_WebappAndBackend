<template>
  <main id="cart">
    <div class="left-side-cart">
      <div class="sumOfLessons">
        <div class="lesson" v-for="lecture in lectures">
          <CardLesson context="cart" :lesson="lecture" @removeElement="removeLessonFromCart"/>
        </div>
      </div>
    </div>
    <div class="right-side-cart">

    </div>
  </main>
</template>

<script>
import CardLesson from "@/components/cards/CardLesson.vue";
import {store} from "@/apiCalls/User"
export default {
  name: "Cart",
  components: {CardLesson},
  data(){
    return{
      lectures: [],
      store
    }
  },
  methods:{
    getCartLectures(){
      this.lectures = store.cart_list
    },
    removeLessonFromCart(value){
      console.log("SIUM")
      store.removeElementInCartList(value)
      this.lectures = store.cart_list
      console.log("CartList aggiornata:" + this.lectures)
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
}
</style>