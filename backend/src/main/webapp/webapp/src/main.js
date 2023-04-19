import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './assets/main.css'
import { setupCalendar } from 'v-calendar';
import './css_colors_webapp/colors.module.css'
// Use calendar defaults (optional)


const app = createApp(App)
app.use(setupCalendar, {})
app.use(router)

app.mount('#app')
