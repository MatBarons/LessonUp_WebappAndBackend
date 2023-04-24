<template>
<div class="row-lecture">
  <h3 class="lecture-date">{{ lecture.date }}</h3>
  <h3 class="lecture-time">{{ lecture.time }}</h3>
  <h3 class="lecture-professor">{{ lecture.email }}</h3>
  <h3 class="lecture-subject">{{ lecture.subject }}</h3>
  <h3 class="lecture-status">{{ status }}</h3>
  <h3 class="lecture-student">{{ lecture.student }}</h3>
  <button @click="deleteLectures">Elimina</button>
</div>
</template>

<script>
export default {
  name: "RowLecture",
  emits: ['deleteLecture'],
  props: {
    lecture: {
      type: Object,
      required: true
    },
    status:{
      type: String,
      required: true
    },
    distance_right:{
      type: String,
      required: true
    }
  },
  methods:{
    deleteLectures(){
      this.$emit('deleteLecture',this.lecture)
    },
    decideDistance(){
      if(this.status === "free"){
        this.distance_right = "21%"
      }else{
        this.distance_right = "15%"
      }
    }
  },
}
</script>

<style lang="scss" scoped>
.row-lecture{
  display: flex;
  flex-direction: row;
  height: 5rem;
  width: 100%;
  align-items: center;
  .lecture-date{
    margin-left: 4rem;
    margin-right: 7.5rem;

  }
  .lecture-time{
    margin-right: 6.5rem;
    display: inline-block;
  }
  .lecture-professor{
    display: inline-block;
  }
  .lecture-subject{
    right: 48.5%;
    position: absolute;
  }
  .lecture-status{
    right: 36%;
    position: absolute;
  }
  .lecture-student{
    right: v-bind(distance_right);
    position: absolute;
  }
  button{
    height: 55%;
    width: 5%;
    position: absolute;
    right: 5px;
    background: red;
    border-radius: 0.5rem;
    font-size: 15px;
    margin-right: 2rem;
  }
}
</style>