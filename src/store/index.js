import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);


export const store = new Vuex.Store({
  state: {
      token: localStorage.getItem('token') || null,      
  },
  getters: {
      // Считаем юзера авторизованным, если токен еще действует. Токен обнговляется только при logIn
      authCheck: (state, getters) => {
          
          if (getters.decodedToken && getters.decodedToken.exp >= new Date().getTime()/1000) {
            console.log("Токену осталось жить (сек):", getters.decodedToken.exp - new Date().getTime()/1000)
              return true
          }
             return false
      },
      userRole: (state, getters) => {
            return getters.decodedToken ? getters.decodedToken.role : null
      },
      decodedToken: (state) => {
            if (state.token && state.token !== 'undefined') {
              let tokenBody = atob(state.token.split('.')[1])
              return JSON.parse(tokenBody)  
            }
            return null
      }
  },
  mutations: {
      logIn (state, newToken) { 
          state.token = newToken
          localStorage.setItem('token', newToken)
        },

      logOut (state) { 
          state.token = null
          //localStorage.setItem('token', '')
          localStorage.removeItem('token')
         },
  },
  actions: {
      
  },
});

