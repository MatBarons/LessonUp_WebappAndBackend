<template>
  <aside class="primary">
    <h2>Lesson Up</h2>
    <h3>Menu</h3>
    <div class="menu">
      <router-link to="/home" class="button">
        <span class="material-icons">home</span>
        <span class="text">Home</span>
      </router-link>
      <router-link to="/cart" class="button">
        <span class="material-icons">shopping_cart</span>
        <span class="text">Cart</span>
      </router-link>
      <div v-if="store.data.token !== ''">
        <router-link to="/profile" class="button">
          <span class="material-icons">person</span>
          <span class="text">You</span>
        </router-link>
      </div>
      <div v-else>
        <router-link to="/" class="button">
          <span class="material-icons">person</span>
          <span class="text">Login</span>
        </router-link>
      </div>
    </div>
    <div class="logo">
      <img src="../../assets/images/logo.png" alt="logo" />
    </div>
  </aside>
</template>

<script>
import {store} from "@/apiCalls/User";

export default {
  name: 'Sidebar',
  computed: {
    store() {
      return store
    }
  }
}
/*

import { ref } from 'vue'
//import logoURL from '../assets/logo.png'
const is_expanded = ref(localStorage.getItem("is_expanded") === "true")
const ToggleMenu = () => {
  is_expanded.value = !is_expanded.value
  localStorage.setItem("is_expanded", is_expanded.value)
}
 */
</script>

<style lang="scss" scoped>
aside {
  position: relative;
  display: flex;
  flex-direction: column;
  overflow: auto;
  color: white;
  width: 12vw;
  min-height: 100vh;
  background: #009b4d;
  padding: 1rem;
  box-shadow: 2px 2px 2px 2px rgba(0,0,0,0.15);
  h2{
    color: white;
    font-weight: bold;
    padding-bottom: 3rem;
  }
  .logo {
    bottom: 0px;
    position: absolute;
    margin: 2rem;
    img {
      width: 100%;
      height: 100%;
    }
  }
  h3 {
    color: #FFFFFFFF;
    font-weight: bold;
    font-size: 0.875rem;
    margin-bottom: 0.5rem;
    text-transform: uppercase;
    margin-top: 2rem;
  }
  .menu {
    margin: 0 -1rem;

    router-link{
    }
    .button {
      display: flex;
      align-items: center;
      text-decoration: none;
      padding: 0.5rem 1rem 2rem;
      .material-icons {
        font-size: 2rem;
        color: white;
      }
      .text {
        color: white;
        font-size: 1rem;
        padding-left: 1rem;
      }
      &:hover {
        background-color: var(--dark-alt);
        .material-icons, .text {
          color: #FFFFFFFF;
        }
      }
      &.router-link-exact-active {
        background-color: var(--dark-alt);
        border-right: 5px solid #ffcc00;
        .material-icons, .text {
          color: #ffcc00 ;
        }
      }
    }
  }
  .footer {
    opacity: 0;
    p {
      font-size: 0.875rem;
      color: var(--grey);
    }
  }
  @media (max-width: 1024px) {
    position: absolute;
    z-index: 99;
  }
}
</style>