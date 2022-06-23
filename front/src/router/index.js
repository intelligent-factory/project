import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    children:[
      {
        path:'test',
        name:'test',
        component:() =>import("@/views/routeTest1/route1")
      }
    ],
  },{
  path:'/Login',
    name:'login',
    component: () =>import("@/views/Login")
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
