<template>
  <div class="row-teaching">
    <h3 class="email">{{email}}</h3>
    <h3 class="subject">{{subject}}</h3>
    <button @click="deleteAssociation">Elimina</button>
  </div>
</template>

<script>
export default {
  name: "RowTeaching",
  emits: ['deleteAssociation'],
  props: {
    email:{
      type: String,
      required: true
    },
    subject: {
      type: String,
      required: true
    }
  },
  methods:{
    deleteAssociation(){
      this.$emit('deleteAssociation',this.email,this.subject)
    }
  }
}
</script>

<style lang="scss" scoped>
.row-teaching{
  display: flex;
  flex-direction: row;
  height: 5rem;
  width: 100%;
  align-items: center;
  .email{
    left: 9%;
    position: absolute;
  }
  .subject{
    left: 53%;
    position: absolute;
  }
  button{
    height: 55%;
    width: 10%;
    position: absolute;
    right: 5px;
    background: red;
    border-radius: 0.5rem;
    font-size: 15px;
    margin-right: 2rem;
  }
}
</style>