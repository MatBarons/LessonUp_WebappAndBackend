import { createRouter, createWebHistory } from 'vue-router'
import Home from "@/views/Home.vue";
import Cart from "@/views/Cart.vue";
import Profile from "@/views/Profile.vue";
import Login from "@/views/Login.vue";
import Layout from "@/views/Layout.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Login',
      component: () => import("@/views/Login.vue"),
    },
    {
      path: '/layout',
      name: 'Layout',
      component: () => import("@/views/Layout.vue"),
      children:[
        {
          path: '/home',
          name: 'home',
          component: Home
        },
        {
          path: '/cart',
          name: 'Cart',
          component: Cart
        },
        {
          path: '/profile',
          name: 'Profile',
          component: Profile
        },
      ]
    },
  ]
})

export default router
