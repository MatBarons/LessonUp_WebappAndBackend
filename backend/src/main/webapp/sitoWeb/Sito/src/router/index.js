import { createRouter, createWebHistory } from 'vue-router'
import Homepage from "@/views/Homepage.vue";
import Orders from "@/views/Orders.vue";
import Cart from "@/views/Cart.vue";
import Profile from "@/views/Profile.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Homepage
    },
    {
      path: '/Orders',
      name: 'orders',
      component: Orders
    },
    {
      path: '/Cart',
      name: 'cart',
      component: Cart
    },
    {
      path: '/Profile',
      name: 'profile',
      component: Profile
    }
  ]
})

export default router
