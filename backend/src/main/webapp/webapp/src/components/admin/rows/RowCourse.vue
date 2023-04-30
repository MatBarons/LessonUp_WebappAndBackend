<template>
  <div class="row-course">
    <h3 class="course-name">{{ course.name }}</h3>
    <h3 class="course-active">{{ course.isActive }}</h3>
    <button @click="deleteCourse">Cambia stato</button>
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
      this.course.isActive = !this.course.isActive
      this.$emit('deleteCourse',this.course)
    }
  },
}
</script>

<style lang="scss" scoped>
.row-course{
  display: flex;
  flex-direction: row;
  height: 5rem;
  width: 100%;
  align-items: center;
  .course-name{
    margin-left: 8rem;
  }
  .course-active{
    right: 41%;
    position: absolute;
  }
  button{
    height: 55%;
    width: 8%;
    position: absolute;
    right: 5px;
    background: red;
    border-radius: 0.5rem;
    font-size: 15px;
    margin-right: 2rem;
  }
}
</style>