<template>
  <button class="card" @click="this.$emit('setSubject',this.name)">
    <div class="card-header"></div>
    <div class="card-title">
      <h2>{{ name }}</h2>
    </div>
    <div class="card-footer">
      <p>the language of the universe</p>
      <img src="subjImg" alt="subject image">
    </div>
  </button>
</template>

<script>
export default {
  name: "CardSubject",
  emits:['setSubject'],
  props:{
    name:{
      type: String,
      required: true //change to true
    }
  },
  methods:{
  }
}
</script>

<style lang="scss" scoped>

.card{
  height: 8rem;
  width: 13rem;
  background-color: lightgrey;
  border-radius: 0.5rem;

  @media(max-width: 1024px){
    max-width: 8rem;
    max-height: 3rem;
    .card-title{
      font-size: 12px;
    }
    .card-footer{
      p{
        font-size: 0px;
      }
      img{

      }
    }
  }
  .card-header{
    border-top-left-radius: 0.5rem;
    border-top-right-radius: 0.5rem;
    color: red;
    background-color: red;
    height: 0.3rem;
  }
  .card-title{
    padding-top: 1rem;
    padding-left: 1rem;
    text-decoration-style: solid;
    h2 {

      color: black;
      text-decoration-style: solid;
    }
  }
  .card-footer{
    padding-left: 1rem;
    padding-top: 0.5rem;
    display: flex;
    flex-direction: row;
    p{
      color: grey;
      font-size: 12px;
    }
    img{
      padding-top: 3rem;

    }
  }


}


</style>