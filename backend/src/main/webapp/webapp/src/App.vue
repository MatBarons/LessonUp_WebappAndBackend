<script>
import {store} from "@/apiCalls/User"

import Layout from "@/views/Layout.vue";
import Login from "@/views/Login.vue";
export default{
  name: 'App',
  components:{Login, Layout},
  data(){
    return{
      store
    }
  },
  beforeCreate() {
  },
  beforeMount() {
  },
  beforeUpdate() {
  },
  created() {
  },
  mounted() {
  },
  setup(){

  },
  methods:{
  }
}
</script>

<template>
  <router-view to="/login"/>
</template>

<style lang="scss">
html{
  body{
    background-color: #faf5e9;//rgba(0, 0, 0, 0.06);
    width: 100%;
    #app{
      width: 100%;
      max-width: 100%;
      padding: 0;
      margin: 0;
      @media(max-width: 768px){
       justify-items: flex-start;
      }
    }
  }
}


*,*::after,*::before{
  margin:0;
  padding: 0;

}
button{
  cursor: pointer;
  appearance: none;
  border: none;
  outline: none;
  background: none;
}

</style>
