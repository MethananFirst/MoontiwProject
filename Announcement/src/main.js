

import './assets/main.css'
import './assets/add.css'
import './assets/user.css'

import{ createPinia } from 'pinia'
import { createApp } from 'vue'


import App from './App.vue'
import router from './router'
const pinia = createPinia()
const app = createApp(App)
app.use(router)
app.mount('#app')

app.use(pinia)