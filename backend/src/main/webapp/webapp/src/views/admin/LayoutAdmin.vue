<template>
  <main id="layout-admin">
    <SidebarAdmin/>
    <router-view/>
  </main>
</template>

<script>
import SidebarAdmin from "@/components/admin/SidebarAdmin.vue";
import router from "@/router";
import {store} from "@/apiCalls/User";
export default {
  name: "LayoutAdmin",
  components: {SidebarAdmin},
  data(){
    return{
      store
    }
  },
  beforeCreate() {
    if(store.email === null){
      router.push('/')
    }else{
      router.push('/lectures')
    }
  }
}
</script>

<style lang="scss" scoped>
#layout-admin{
  display: flex;
  height: 100%;
  width: 100%;
}
</style>