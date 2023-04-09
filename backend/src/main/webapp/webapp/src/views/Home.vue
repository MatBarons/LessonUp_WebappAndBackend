<template>
  <main id="homepage">
    <div class="left-side-homepage">
      <h1>Prenota ora le tue prossime lezioni!</h1>
      <div class="sumOfLessons">
        <CardLesson/>
      </div>
    </div>
    <div class="right-side-homepage">
      <CustomCalendar/>
      <div class="sumOfSubjects" v-for="subject in subjects">
        <CardSubject :name="subject"/>
      </div>
    </div>
  </main>
</template>

<script>
import CardLesson from "@/components/cards/CardLesson.vue";
import CardSubject from "@/components/cards/CardSubject.vue";
import CustomCalendar from "@/components/CustomCalendar.vue";

export default {
  name: "Home",
  components: {CustomCalendar, CardSubject, CardLesson},
}
</script>

<style lang="scss" scoped>
#homepage{
  padding-top: 10px;
  padding-left: 10px;
  display: flex;
  flex-direction: row;
  width: 70%;
  @media(max-width: 1024px){
    padding-left: 3rem;
  }
  .left-side-homepage{
    display: flex;
    flex-direction: column;
    h1{
      color: green;
      font-size: 50px;
    }
    .sumOfLessons{
     width: 80vh;
    }
  }
  .right-side-homepage{
    display: flex;
    flex-direction: column;
    padding: 1rem;
    .sumOfSubjects{

      display: flex;
      padding-top: 2rem;
    }
  }


}


</style>