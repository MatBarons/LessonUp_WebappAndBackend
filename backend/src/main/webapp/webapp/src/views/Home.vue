<template>
  <main id="homepage">
    <div class="sumOfLessons">
      <CardLesson/>
    </div>
    <div class="left-side-homepage">
      <div class="calendar">

      </div>
      <div class="sumOfSubjects">
        <CardSubject/>
      </div>
    </div>

  </main>
  <!-- Cards lectures -->
  <!-- Calendar -->
  <!-- Cards professor -->
</template>

<script>
import CardLesson from "@/components/CardLesson.vue";
import CardSubject from "@/components/CardSubject.vue";

export default {
  name: "Home",
  components: {CardSubject, CardLesson}
}
</script>

<style lang="scss" scoped>
#homepage{
  width: 100vh;
  display: flex;

  .left-side-homepage{
    display: flex;
    flex-direction: column;
    .sumOfSubjects{
      width: 100vh;
      display: flex;
    }
  }

  .sumOfLessons{
    width: 100vh;
    display: flex;
  }
}


</style>