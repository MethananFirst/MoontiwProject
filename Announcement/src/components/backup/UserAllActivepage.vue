<script setup>
import { ref , onMounted } from 'vue';
import {  DataUserpage } from '../getdata'
import { useRouter } from 'vue-router';

const changeTime = (dateTime) => {
  const time = new Date(dateTime)
  if (time.getFullYear() > 1970)
  return time.toLocaleString('en-GB',{hour12: false, year: 'numeric', month: 'short', day: '2-digit', hour: '2-digit', minute: '2-digit'})
  else return '-'
}



const AnList = ref([])
const route = useRouter();

const getTimezone = new Intl.DateTimeFormat();
const timezoneCountry = ref(getTimezone.resolvedOptions().timeZone)


const x = import.meta.env.VITE_API_ROOT

onMounted(async () =>{
    AnList.value = await DataUserpage()
    console.log(AnList.value)
    
} )

const goToDetail = (id) => {
    route.push({name:"Userdetail",params:{id}})
}



</script>

<template>
    <!-- <div class="colors"> 
      <span class="active" style="--clr:#aeffe9;" @click="changeColor('#aeffe9')"></span>
      <span style="--clr:#ffd3d3;" @click="changeColor('#ffd3d3')"></span>
      <span style="--clr:#bddeff;" @click="changeColor('#bddeff')"></span>
      <span style="--clr:#ffd1f9;" @click="changeColor('#ffd1f9')"></span>
      <span style="--clr:#ffe5cd;" @click="changeColor('#ffe5cd')"></span>
  </div> -->
      <div>
          <h1 style="text-align: center ; font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif; margin: 40px;">SIT Announcement System(SAS) </h1>
          <p style=" font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif; margin-left:0px" >Date/Time show in Timezone : {{  timezoneCountry }}</p>
          
      </div>
      <div>
          <router-link :to="{name:'add'} " ><button  class="ann-button" style=" top: 85px; right: 10px;">Closed Announcements</button></router-link>
     </div>
      <div >
         <table >
          <thead>
                  <tr>
                      <th>No.</th>
                      <th>Title</th>
                      <th >Category</th>
                      <!-- <th>Publish Date</th>
                      <th >Close Date</th>
                      <th>Announcement Display</th>
                      <th colspan="2" >Action</th> -->
                  </tr>
              </thead>
              <tbody v-if="AnList?.length > 0" >
                <tr v-for="({id,announcementTitle,announcementCategory},index) in AnList" :key="index" class="ann-item">
                      <td> {{ index+1 }}</td>
                      <router-link :to="``" @click="goToDetail(id)">
                      <td class="ann-title" style="text-decoration: none; cursor: pointer;">{{ announcementTitle }}</td>
                      </router-link>
                      <td class="ann-category">{{ announcementCategory }}</td> 
                      <!-- <td  class="ann-publish-date">{{ changeTime(publishDate) ? changeTime(publishDate) : '-'}}</td>
                      <td class="ann-close-date">{{ changeTime(closeDate) ? changeTime(closeDate) : '-'}}</td>
                      <td  class="ann-display">{{ announcementDisplay }}</td>
                      <td><button @click="goToDetail(id)"  class="ann-button">view</button></td>
                      <td><button @click="confirmDeleteAnnouncement(id)"  class="ann-button">delete</button></td> -->
                      <!-- <td><router-link :to="{name:'announcementDetail'} " ><button @click="goToDetail(id)" >details</button></router-link></td> -->
                  </tr>
              </tbody>
              
          </table>  
          <div v-show="AnList === null || AnList?.length === 0" class="p1">
         <p>No Announcement</p>
          </div>
      </div>
  
     
      
      <RouterView/>
  </template>