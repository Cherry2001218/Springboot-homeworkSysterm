// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import VueAxios from 'vue-axios'
import moment from 'moment/moment'
import ECharts from 'vue-echarts'
import { use } from 'echarts/core'
// 手动引入 ECharts 各模块来减小打包体积
import JsonExcel from 'vue-json-excel'
Vue.component('downloadExcel', JsonExcel)

import {
  CanvasRenderer
} from 'echarts/renderers'
import {
  BarChart
} from 'echarts/charts'
import {
  GridComponent,
  TooltipComponent
} from 'echarts/components'

use([
  CanvasRenderer,
  BarChart,
  GridComponent,
  TooltipComponent
]);

// 全局注册组件（也可以使用局部注册）
Vue.component('v-chart', ECharts)

Vue.use(VueAxios,axios)
Vue.use(ElementUI);
Vue.config.productionTip = false
axios.defaults.baseURL = 'http://localhost:8080/ss/';
/* eslint-disable no-new */

Vue.filter('moment',function(value,formatString){
  formatString=formatString || 'YYYY-MM-DD HH:mm:ss';
  return moment(value).format(formatString);
  //return moment.unix(value).format(formatString);
});

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
