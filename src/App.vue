<template>
  <el-container id="app">
    
    <el-aside width="200px" >
      <Menu />
    </el-aside>
    <el-main style="padding-top: 0px;"> 
      <router-view />
    </el-main>
    
  </el-container>
</template>

<script>
import Menu from './components/Menu.vue'

export default {
  name: 'app',
  components: {
    Menu,
    },
  data() {
    return {
      
    }
  },
}
</script>

<style>
body {
  margin: 0;
}

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 0;
  background-color: rgb(238, 241, 246);
}

#app > * {
  margin-top: 60px;
}
</style>