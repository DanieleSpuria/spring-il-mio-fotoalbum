import { createRouter, createWebHistory } from 'vue-router';
import Home from '../components/Home.vue';
import Contact from '../components/Contact.vue';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/contact',
      name: 'contact',
      component: Contact
    }
  ]
})

export {router}