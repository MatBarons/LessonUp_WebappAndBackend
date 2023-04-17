<template>
  <aside>
    <div class="logo">
      <img :src="logoURL" alt="logo" />
    </div>
    <h3>Menu</h3>
    <div class="menu">
      <router-link to="/" class="button">
        <span class="material-icons">home</span>
        <span class="text">Home</span>
      </router-link>
      <router-link to="/cart" class="button">
        <span class="material-icons">shopping_cart</span>
        <span class="text">Cart</span>
      </router-link>
      <router-link to="/orders" class="button">
        <span class="material-icons">list</span>
        <span class="text">Past Orders</span>
      </router-link>
      <router-link to="/profile" class="button">
        <span class="material-icons">person</span>
        <span class="text">You</span>
      </router-link>
    </div>
  </aside>
</template>

<script>
export default {
  name: 'Sidebar'
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
  display: flex;
  flex-direction: column;
  background-color: var(--dark);
  color: var(--light);
  width: 12vw;
  overflow: hidden;
  min-height: 100vh;
  padding: 1rem;
  .logo {
    margin: 1rem;
    img {
      width: 2rem;
    }
  }
  h3 {
    color: var(--grey);
    font-size: 0.875rem;
    margin-bottom: 0.5rem;
    text-transform: uppercase;
    margin-top: 3rem;
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
        color: var(--light);
      }
      .text {
        color: var(--light);
        padding-left: 1rem;
      }
      &:hover {
        background-color: var(--dark-alt);
        .material-icons, .text {
          color: var(--primary);
        }
      }
      &.router-link-exact-active {
        background-color: var(--dark-alt);
        border-right: 5px solid var(--primary);
        .material-icons, .text {
          color: var(--primary);
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