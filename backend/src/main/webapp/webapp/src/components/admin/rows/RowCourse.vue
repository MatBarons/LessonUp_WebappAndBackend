<template>
  <div class="row-course">
    <h3>{{ course.name }}</h3>
    <h3>{{ course.isActive }}</h3>
    <button @click="deleteCourse">Elimina</button>
  </div>
</template>

<script>
export default {
  name: "RowCourse",
  emits: ['deleteCourse'],
  props:{
    course: {
      type: Object,
      required: true
    }
  },
  methods:{
    deleteCourse(){
      this.$emit('deleteCourse',this.course)
    }
  }
}
</script>

<style lang="scss" scoped>
.row-course{
  display: flex;
  flex-direction: row;
  h3{
    margin-right: 40rem;
  }
  button{
    background: red;
  }
}
</style>