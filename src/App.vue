<template>
<div>

  <el-container v-if="authCheck" id="app" style="flex-direction: column;">
    
      <el-header class="header" height="40px">
        <logout-button />
      </el-header>
      
    <el-container>
      <el-aside width="200px" >
        <Menu />
      </el-aside>
      <el-main style="padding-top: 0px;"> 
        <router-view :key="$route.path" />
      </el-main>
    </el-container>
  </el-container>

  <el-container v-if="!authCheck" id="app">
    <el-main style="padding-top: 250px;"> 
      <router-view  :key="$route.path" />
    </el-main>  
  </el-container>
  
</div>
</template>

<script>
import Menu from './components/Menu.vue'

import { mapGetters } from 'vuex'
import LogoutButton from './components/LogoutButton.vue'

export default {
  name: 'app',
  components: {
    Menu,
    LogoutButton,
    },
  data() {
    return {

    }
  },
  computed:{
    ...mapGetters([
          'authCheck',
          'userRole'
        ])
  },
  methods: {
    axiosSetup() {
      let vm = this

      this.$axios.interceptors.request.use(
        function (config) {   
          config.headers['Authorization'] = `Bearer ${vm.$store.state.token}` 
        return config
          },
        function (error) {
            return Promise.reject(error);
            }
      );

      this.$axios.interceptors.response.use(
        function (response) {
        return response
          },
        function (error) {
          if (error.response.status == 403){
            vm.$notify.error(`Ошибка авторизации. Код ошибки ${error.response.status}`);
            if (vm.$route.name !== 'login-form') {
              vm.$store.commit('logOut')
              vm.$router.push({name: 'login-form'})
            }
          }
            return Promise.reject(error);
            }
      );
    }
  },
  created() {
       this.axiosSetup()
  } 
}
</script>

<style>
body {
  margin: 0;
  background-color: rgb(238, 241, 246);
}


#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 0;  
}

#app > * {
  margin-top: 20px;
}


.header {
        display: flex;
        justify-content: flex-end;
    }
</style>