<template>
  <main id="layout">
    <Sidebar/>
    <router-view/>
  </main>
</template>

<script>
import Sidebar from "@/components/user/Sidebar.vue";
import router from "@/router";
import {store} from "@/apiCalls/User"

export default {
  name: "Layout",
  components: {Sidebar},
  data(){
    return{
      store
    }
  },
  beforeCreate() {
    router.push('/home')
  }
}
</script>

<style lang="scss" scoped>
#layout{
  display: flex;
  height: 100%;
  width: 100%;
}
</style>