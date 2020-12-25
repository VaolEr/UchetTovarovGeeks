<template>
  <el-container id="app">
    
    <el-aside width="200px" >
      <Menu v-on:change-component="selectActiveComponent" components="components"/>
    </el-aside>
    <el-main style="padding-top: 0px;">
      <component v-bind:is="activeComponent"></component>
      
    </el-main>
    
  </el-container>
</template>

<script>
import GetListProducts from './components/GetListProducts.vue'
import Menu from './components/Menu.vue'
import CreateProduct from './components/CreateProduct.vue'

export default {
  name: 'app',
  components: {
    GetListProducts,
    Menu,
    CreateProduct,
  },
  data() {
    return {
      activeComponent: GetListProducts,
      appComponents: this.$options.components
    }
  },
  methods: {
    selectActiveComponent: function (compName) {
      this.activeComponent = this.appComponents[compName]
    },
    printStuff() {
      console.log(this)
    }
  },
  mounted() {
    this.printStuff()
  }
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
