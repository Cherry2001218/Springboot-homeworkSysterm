import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

import Mine from '@/components/Mine'
import Regist from '@/components/Regist'
import Detail from '@/components/Detail'
import Find from '@/components/Find'
import FindAnswer from '@/components/FindAnswer'
import Question from '@/components/Question'
import Comment from '@/components/Comment'
import Course from '@/components/Course'
import DeCourse from '@/components/DeCourse'
import Chat from '@/components/Chat'
import Follow from '@/components/Follow'
import AddNewWork from '@/components/AddNewWork'
import CourseServe from '@/components/CourseServe'
import ShowStudentA from '@/components/ShowStuentA'
import AddWork from '@/components/AddWork'
import Insert from '@/components/Insert'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
   
  
    {
      path: '/AddNewWork/:value',
      name: 'AddNewWork',
      component: AddNewWork
    },

    {
      path: '/Insert/:value',
      name: 'Insert',
      component: Insert
    },
    {
      path: '/AddWork/:value',
      name: 'AddWork',
      component: AddWork
    },

    {
      path: '/Chat',
      name: 'Chat',
      component: Chat
    },
    {
      path: '/ShowStudentA/:value',
      name: 'ShowStudentA',
      component: ShowStudentA
    },
    {
      path: '/Follow',
      name: 'Follow',
      component: Follow
    },
    {
      path: '/CourseServe',
      name: 'CourseServe',
      component: CourseServe
    },
    
    {
      path:'/Mine',
      name:'Mine',
      component: Mine

    },
    {
      path:'/Regist',
      name:'Regist',
      component:Regist
    },
    {
      path:'/Detail/:value',
      name:'Detail',
      component:Detail
    },
    {
      path:'/Find',
      name:'Find',
      component:Find
    },
    {
      path:'/FindAnswer:value',
      name:'FindAnswer',
      component:FindAnswer
    },
    {
      path:'/Question/:value',
      name:'Question',
      component:Question
    },
    {
      path:'/Comment',
      name:'Comment',
      component:Comment
    },
    {
      path:'/Course',
      name:'Course',
      component:Course
    },
    {
      path:'/DeCourse',
      name:'DeCourse',
      component:DeCourse
    },
  ]
})
