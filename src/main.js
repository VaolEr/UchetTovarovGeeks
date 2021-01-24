import Vue from 'vue'
import { store } from './store'
import Axios from 'axios'
import VueObserveVisibility from 'vue-observe-visibility'
import App from './App.vue'
import './plugins/element.js'
import router from './router'

// local test
Axios.defaults.baseURL = 'http://127.0.0.1:8001/'

// stage heroku
//Axios.defaults.baseURL = 'https://pydjango-geek-stockcontrol.herokuapp.com/'

Axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem('token')}`
/*Axios.interceptors.request.use(
  function (config) {   
    config.headers['Authorization'] = `Bearer ${localStorage.getItem('token')}`
  return config
    },
  function (error) {
      return Promise.reject(error);
      }
);
Axios.interceptors.response.use(
  function (response) {
    if (response.data.token) {localStorage.setItem('token', response.data.token)}
  return response
    },
  function (error) {
      return Promise.reject(error);
      }
);*/

Vue.use(VueObserveVisibility)

Vue.config.productionTip = false
Vue.prototype.$axios = Axios



new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
