<script setup>
import Sidebar from "@/components/Sidebar.vue";

</script>

<template>
  <sidebar/>
  <router-view/>
</template>

<style lang="scss">
html{
  body{
    width: 100%;
    background-color: white;
    #app{
      width: 100%;
      max-width: 100%;
      padding: 0;
      margin: 0;
    }
  }
  width: 100%;
  height: 100%;
  @media(max-width: 768px){
    padding-left: 6rem;
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
