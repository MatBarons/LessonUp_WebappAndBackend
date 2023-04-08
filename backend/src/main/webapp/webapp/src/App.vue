<script setup>
import Sidebar from "@/components/Sidebar.vue";

</script>

<template>
  <sidebar/>
  <router-view/>
</template>

<style lang="scss">
html,body,#app{
  margin: 0;
  padding: 0;
  background-color: white;
  width: 100%;
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

main{
  flex: 1 1 0;
  padding: 2rem;
  @media(max-width: 1024px){
    padding-left: 6rem;
  }
}


</style>
