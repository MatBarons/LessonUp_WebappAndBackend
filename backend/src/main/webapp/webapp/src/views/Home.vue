<template>
  <main id="homepage">
    <div class="right-side-homepage">
      <h1>Prenota lezioni</h1>
      <div class="sumOfLessons">
        <CardLesson/>
      </div>
    </div>
    <div class="left-side-homepage">
      <div class="calendar">
        <Calendar :initial-page="{ month: 1, year: 2023 }"
                  :color="selectedColor"
                  :attributes="attrs"
        />
        <DatePicker v-model="date"/>
      </div>
      <div class="sumOfSubjects">
        <CardSubject/>
      </div>
    </div>
  </main>
</template>

<script>
import CardLesson from "@/components/CardLesson.vue";
import CardSubject from "@/components/CardSubject.vue";
import { Calendar, DatePicker } from 'v-calendar';
import { ref } from 'vue';

const selectedColor = ref('blue');
const attrs = ref([
  {
    key: 'test',
    highlight: true,
    dates: { start: new Date(2019, 3, 15), end: new Date(2025, 12, 31) },
  }
]);
export default {
  name: "Home",
  components: {CardSubject, CardLesson,Calendar,DatePicker},
  data(){
    return{
      date: new Date()
    }
  }
}
</script>

<style lang="scss" scoped>
#homepage{
  padding-top: 10px;
  padding-left: 10px;
  display: flex;
  flex-direction: row;
  @media(max-width: 1024px){
    padding-left: 3rem;
  }


  .right-side-homepage{
    display: flex;
    flex-direction: column;
    h1{
      color: black;
    }
    .sumOfLessons{
      width: 100vh;
      display: flex;

    }
  }

  .left-side-homepage{
    display: flex;
    flex-direction: column;
    calendar{
      height: 50%;
      color: black;
      background-color: red;
    }
    .sumOfSubjects{
      width: 100vh;
      display: flex;
    }
  }


}


</style>