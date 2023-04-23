import { createRouter, createWebHistory } from 'vue-router'

import Home from "@/views/user/Home.vue";
import Cart from "@/views/user/Cart.vue";
import Profile from "@/views/user/Profile.vue";
import Login from "@/views/user/Login.vue";
import Layout from "@/views/user/Layout.vue";

import Courses from "@/views/admin/Courses.vue";
import Lectures from "@/views/admin/Lectures.vue";
import Users from "@/views/admin/Users.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Login',
      component: () => import("@/views/user/Login.vue"),
    },
    {
      path: '/layout',
      name: 'Layout',
      component: () => import("@/views/user/Layout.vue"),
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
    {
      path: '/layoutAdmin',
      name: 'layoutAdmin',
      component: () => import("@/views/admin/LayoutAdmin.vue"),
      children: [
        {
          path: '/courses',
          name: 'courses',
          component: Courses
        },
        {
          path: '/lectures',
          name: 'lectures',
          component: Lectures
        },
        {
          path: '/users',
          name: 'users',
          component: Users
        }
      ]
    }
  ]
})

export default router
