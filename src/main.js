import Axios from 'axios'
import Vue from 'vue'
import VueObserveVisibility from 'vue-observe-visibility'
import App from './App.vue'
import './plugins/element.js'
import router from './router'

// local test
Axios.defaults.baseURL = 'http://127.0.0.1:8001/'

// stage heroku
//Axios.defaults.baseURL = 'https://pydjango-geek-stockcontrol.herokuapp.com/'

Vue.use(VueObserveVisibility)

Vue.config.productionTip = false
Vue.prototype.$axios = Axios



new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
