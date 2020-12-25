import Axios from 'axios'
import Vue from 'vue'
import App from './App.vue'
import './plugins/element.js'


Axios.defaults.baseURL = 'http://127.0.0.1:8001/'

Vue.config.productionTip = false
Vue.prototype.$axios = Axios

new Vue({
  render: h => h(App),
}).$mount('#app')
