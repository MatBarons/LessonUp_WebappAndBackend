<template>
  <main id="cart">
    <div class="left-side-cart">
      <div class="sumOfLessons">
        <div class="lesson" v-for="lecture in lectures">
          <CardLesson context="cart" :lesson="lecture"/>
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
    }
  },
  created() {
    this.getCartLectures()
  }
}
</script>

<style lang="scss" scoped>
#cart{
  height: 100vh;
  width: 88vw;
  .left-side-homepage{
    width: 50vw;
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