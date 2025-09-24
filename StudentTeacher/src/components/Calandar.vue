<template>
  <div class="home">
    <Calendar
      :date="new Date()"
      @enter="mouseenter"
      @leave="mouseleave"
      @changeMonth="changeMonth"
      style="width:400px;height:400px"
    >
      <!-- 可使用动态插槽名设置，v-slot:[slotName] -->
      <template v-slot:2021-5-21>
        <div style="position:absolute;bottom:-20px">今天</div>
      </template>
    </Calendar>
  </div>
</template>

<script>


export default {
  name: 'Home',
  components: {
    Calendar
  },
  methods: {
    mouseenter (event, dateInfo) {
      console.log("悬浮进入", event, dateInfo)
    },
    mouseleave (event, dateInfo) {
      console.log("悬浮离开", event, dateInfo)
    },
    changeMonth (start, end) {
      console.log("开始:", start)
      console.log("结束:", end)
    }
  }
}
</script>

