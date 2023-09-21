import {createRouter, createWebHistory } from 'vue-router'
const history = createWebHistory(import.meta.env.BASE_URL)
const routes = [
  {
    path: '/admin/announcementDetail/:id',
    name: 'announcementDetail',
    component: () => import('./components/announcementDetail.vue')
  },
  {
    path: '/admin/announcement',
    name: 'allAnnouncement',
    component: () => import('./components/allAnnouncement.vue')
  },
  {
    path: '/admin/announcement/add',
    name: 'add',
    component: () => import('./components/add.vue')
  },
  {
    path: '/admin/announcement/:id/edit',
    name: 'edit',
    component: () => import('./components/edit.vue')
  },
  {
    path: '/announcement',
    name: 'UserList',
    component: () => import('./components/UserAllList.vue')
  },
  {
    path: '/announcement/:id',
    name: 'Userdetail',
    component: () => import('./components/UserAnnouncementdetail.vue')
  },
  {
    path: '/admin/user',
    name: 'user',
    component: () => import('./components/User.vue')
  },
  {
    path: '/admin/user/match',
    name: 'match',
    component: () => import('./components/MatchPassword.vue')
  }
  ,
  {
    path: '/admin/user/add',
    name: 'adduser',
    component: () => import('./components/adduser.vue')
  }
  ,
  {
    path: '/admin/user/:id/edit',
    name: 'edituser',
    component: () => import('./components/edituser.vue')
  }
  ,
  {
    path: '/',
    redirect:'/admin/announcement'
  }

]

const router = createRouter({history,routes})
export default router
