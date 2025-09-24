<template>
    <div class="hello">
        <h2></h2>
        <v-chart class="chart" :option="option"/>
        <v-chart class="chart" :option="option1"/>
    </div>
</template>

<script>
import { use } from "echarts/core";
import { CanvasRenderer } from "echarts/renderers";
import { PieChart } from "echarts/charts";
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent
} from "echarts/components";
import VChart, { THEME_KEY } from "vue-echarts";

use([
  CanvasRenderer,
  PieChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent
]);

export default {
  name: "Chart",
  data() {
    return {
      option: {
        title: {
          text: "各门功课所布置的作业数",
          left: "center"
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          orient: "vertical",
          left: "left",
          data: []
        },
        series: [
          {
            name: "各门功课所布置的作业数",
            type: "pie",
            radius: "55%",
            center: ["50%", "60%"],
            data: [],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)"
              }
            }
          }
        ]
      },
      option1: {
        title: {
          text: "各门功课所布置的作业数",
          left: "center"
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          orient: "vertical",
          left: "left",
          data: []
        },
        xAxis:{
          data:[]
        },
        yAxis:{
        },
        series: [
          {
            name: "各门功课所布置的作业数",
            type: "bar",
            data: [],
             emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)"
              }
            }
          }
        ]
      }
    };
  },
  created(){
      this.axios.get("class/selectFindBySno").then(response=>{
          var res=response.data;
          var data1=[];
          var data2=[];
          res.forEach(item=>{
              data1.push(item.content);
              data2.push({
                  value:item.nums,
                  name:item.course
              })
          })
          this.option1.legend.data=data1;
          this.option.legend.data=data1;
          this.option.series[0].data=data2;
          this.option1.series[0].data=data2;
      })
  },
  method:{

  },
};
</script>

<style scoped>
.chart{
    height: 400px;
}
</style>