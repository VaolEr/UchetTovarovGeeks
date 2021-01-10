import Vue from 'vue'
import VueRouter from 'vue-router'
import GetListProducts from '../views/GetListProductsView.vue'
import ProductFormView from '../views/ProductFormView.vue'
import UniversalListView from '../views/UniversalListView.vue'
import UniversalFormView from '../views/UniversalFormView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'index',
    component: GetListProducts
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

export default router
