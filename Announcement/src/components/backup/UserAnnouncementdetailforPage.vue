<script setup>
 import { useRoute} from 'vue-router';
 import { ref } from 'vue'; 
 import { UseCounterStore } from '../Store.js'
const stage = UseCounterStore()
const route = useRoute();
const announcementById = ref(route.params.id)
console.log(announcementById.value);

const AnList = ref([])

const changeTime = (dateTime) => {
  console.log(new Date(dateTime).toLocaleString('en-GB'));
  if(new Date(dateTime).getFullYear() >1970){             
    return new Date(dateTime).toLocaleString('en-GB',{dateStyle: 'medium', timeStyle: 'short'} )         
  }else{ return '-'}
}

const x = import.meta.env.VITE_API_ROOT

const Data = async () => {
    try {
        const response = await fetch(`${x}/api/announcements/${announcementById.value}`)
       const data = await response.json()
        AnList.value=data
        if(data.status===404 || data.status===400){
          alert("The requested page is not available!");
          window.location='/announcement'
        }
    } catch (error) {
        console.error(error)
      }
    }
    Data()

</script>

<template>

<div>
        <h1 style="text-align: center; margin: 40px; font-family: cursive;">Announcement Detail: </h1>
       
</div>

 <div class="frame" v-if="stage.mode === 'active'" >
  
  <div class="ann-item">

    <h3 >Title : <span class="ann-title"  >{{ AnList.announcementTitle }} </span> </h3><br>
    
    <h3 class="ann-category">Category : <span class="ann-category" >{{ AnList.announcementCategory }} </span></h3> <br>
    <h3 >Descrition : <span class="ann-description" > {{ AnList.announcementDescription }}</span></h3> <br>
  </div>

  <router-link :to="{name:'UserList'}" ><button class="ann-button">Back</button></router-link>
  
</div>

<div class="frame" v-if="stage.mode === 'close'" >
  
  <div class="ann-item">

    <h3 >Title : <span class="ann-title"  >{{ AnList.announcementTitle }} </span> </h3><br>
    
    <h3 class="ann-category">Category : <span class="ann-category" >{{ AnList.announcementCategory }} </span></h3> <br>
    <h3 >Descrition : <span class="ann-description" > {{ AnList.announcementDescription }}</span></h3> <br>
    <h3 >Close On: <span class="ann-close-date" > {{ changeTime(AnList.closeDate) ? changeTime(AnList.closeDate) :'-' }}</span></h3>
  </div>

  <router-link :to="{name:'UserList'}" ><button class="ann-button">Back</button></router-link>
  
</div>
</template>
