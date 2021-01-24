import Vue from 'vue'
import VueRouter from 'vue-router'
import GetListProducts from '../views/GetListProductsView.vue'
import ProductFormView from '../views/ProductFormView.vue'
import UniversalListView from '../views/UniversalListView.vue'
import UniversalFormView from '../views/UniversalFormView.vue'
import AuthFormView from '../views/AuthFormView.vue'
import {store} from '../store'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'index',
    component: GetListProducts
  },
  {
    path: '/login',
    name: 'login-form',
    component: AuthFormView,
    meta: { publicAwailible: true },
    beforeEnter: (to, from, next) => {
      if (store.getters.authCheck) {
        next({
          name: 'index'
        })
      }
      else {
        next()
      }
    }
  },
  {
    path: '/product-list',
    name: 'ProductList',
    component: GetListProducts
  },
  {
    path: '/product-form',
    name: 'ProductFormView',
    component: ProductFormView
  },
  {
    path: '/product-form/:id',
    name: 'ProductFormViewEdit',
    component: ProductFormView
  },
  {
    path: '/universal-list/',
    name: 'UniversalListView',
    component: UniversalListView
  },
  {
    path: '/universal-form/:type/:id',
    name: 'UniversalFormView',
    component: UniversalFormView,
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.name !== 'login-form' && !store.getters.authCheck) {
    next({ name: 'login-form' })
  }
  else { next() }

})

export default router
