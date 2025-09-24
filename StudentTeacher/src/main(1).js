// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

import Vant from 'vant';
import 'vant/lib/index.css';
Vue.use(Vant);

import axios from 'axios';
import VueAxios from 'vue-axios';

Vue.use(VueAxios,axios)
axios.defaults.baseURL='http://localhost:8080/ss/'
import Vue from 'vue'
import App from './App'
import router from './router'
import './assets/all.css'
Vue.config.productionTip = false

import moment from 'moment/moment'
Vue.filter('moment' ,function(value,formatString){
  formatString = formatString || 'YYYY-MM-DD HH:mm:ss';
  return moment(value).format(formatString);//value 可以是普通日期，
  //return moment.unix(value).format(formatString);
  //时间截转时间
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
