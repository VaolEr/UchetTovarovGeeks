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
    component: GetListProducts,
    meta: { access: ['ADMIN','USER']  },
  },
  {
    path: '/login',
    name: 'login-form',
    component: AuthFormView,
    meta: { access: ['ADMIN','USER']  },
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
    component: GetListProducts,
    meta: { access: ['ADMIN','USER']  },
    
  },
  {
    path: '/product-form',
    name: 'ProductFormView',
    component: ProductFormView,
    meta: { access: ['ADMIN']  },
  },
  {
    path: '/product-form/:id',
    name: 'ProductFormViewEdit',
    component: ProductFormView,
    meta: { access: ['ADMIN','USER']  },
  },
  {
    path: '/universal-list/',
    name: 'UniversalListView',
    component: UniversalListView,
    meta: { access: ['ADMIN']  },
  },
  {
    path: '/universal-form/:type/:id',
    name: 'UniversalFormView',
    component: UniversalFormView,
    meta: { access: ['ADMIN']  },
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.name !== 'login-form' && !store.getters.authCheck) {
    // если пытаешься зайти неавторизованным куда угодно, кроме формы логина - переадресация на форму логина
    next({ name: 'login-form' })
  }
  else { 
    if (to.name == 'login-form' || to.matched.some(record => record.meta.access.includes(store.getters.userRole))) {
      next() 
    }
    else {
      next(false)
    }
  }

})

export default router
