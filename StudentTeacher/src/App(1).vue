<template>
  <div id="app">
    <router-view/>
    <van-tabbar v-model="active">
  <van-tabbar-item icon="home-o" to="/">Hello</van-tabbar-item>
  <van-tabbar-item icon="search" >查询</van-tabbar-item>
  <van-tabbar-item icon="friends-o" to="/Friend">分类</van-tabbar-item>
  <van-tabbar-item icon="star-o" to="/Mine">自己</van-tabbar-item>
</van-tabbar>
  </div>
</template>

<script>
import Vue from 'vue';
import { Tabbar, TabbarItem } from 'vant';

Vue.use(Tabbar);
Vue.use(TabbarItem);
export default {
  name: 'App',
  active:'',
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  
}
</style>
