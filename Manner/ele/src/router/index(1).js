import Vue from 'vue'
import Router from 'vue-router'
import NewsList from '@/components/NewsList'
import AddNews from '@/components/AddNews'
import Chart from '@/components/Chart'
import UserList from '@/components/UserList'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name:' NewsList',
      component:  NewsList
    },
    {
      path: '/Chart',
      name:' Chart',
      component:  Chart
    },
    {
      path: '/AddNews',
      name:' AddNews',
      component:  AddNews
    },
    {
      path: '/UserList',
      name:' UserList',
      component: UserList
    }
  ]
})
