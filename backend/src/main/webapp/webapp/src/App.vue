<script>
import {store} from "@/apiCalls/User"
import colors from "@/css_colors_webapp/colors.module.css"
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
  mounted() {
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
    background-color: var(--md-sys-color-background);
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
:root{
  --primary: #4CE346;
  --grey: #64748b;
  --dark: #1e293b;
  --light: #f1f4f9;
  --sidebar-width: 300px;


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
