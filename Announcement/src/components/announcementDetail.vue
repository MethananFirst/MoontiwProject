<script setup>
 import { useRoute} from 'vue-router';
 import { ref,onMounted } from 'vue'; 
 import { UseCounterStore } from '../Store.js'
const stage = UseCounterStore()
const route = useRoute();
const announcementById = ref(route.params.id)

const AnList = ref([])

const changeTime = (dateTime) => {
  if(new Date(dateTime).getFullYear() >1970){             
    return new Date(dateTime).toLocaleString('en-GB',{dateStyle: 'medium', timeStyle: 'short'} )         
  }else{ return '-'}
}

onMounted(async () => {
 AnList.value = await stage.AdminfetchId(announcementById.value);
})
</script>

<template>
<div>
    <h1 style="text-align: center; margin: 40px; font-family: cursive;">Announcement Detail: </h1>
</div>
 <div class="frame">
  <div class="ann-item">
    <h3 >Title : <span class="ann-title"  >{{ AnList.announcementTitle }} </span> </h3><br>
    <h3 class="ann-category">Category : <span class="ann-category" >{{ AnList.announcementCategory }} </span></h3> <br>
    <h3 >Descrition : <span class="ann-description" > {{ AnList.announcementDescription }}</span></h3> <br>
    <h3 >Publish Date :  <span class="ann-publish-date" >{{ changeTime(AnList.publishDate) ? changeTime(AnList.publishDate) :'-'}}</span></h3> <br>
    <h3 >Close Date : <span class="ann-close-date" > {{ changeTime(AnList.closeDate) ? changeTime(AnList.closeDate) :'-' }}</span></h3> <br>
    <h3 >Display : <span class="ann-display" > {{ AnList.announcementDisplay }}</span></h3>
  </div>

  <router-link :to="{name:'allAnnouncement'}" ><button class="ann-button">Back</button></router-link>
  <router-link :to="{name:'edit'}" ><button class="ann-button">Edit</button></router-link>
</div>
</template>

<style scoped>

 button {
  transition-duration: 500ms;
  background-color: #353835;
  border: none;
  color: white;
  padding: 8px 16px;
  text-align: center;
  text-decoration: none;
  font-size: 14px;
  margin: 18px 2px;
  cursor: pointer;
  border-radius: 5px;

}

button:hover {
  transition-duration: 500ms;
  background-color: #000000;
}
h3{
  display: inline;
  padding: 5px;
}

h3 {
  
  font-size: 30px;
  padding-left: 20px;
  font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}

p {
  font-size: 21px;
}


#back {
  margin-left: 20px;
  margin-top: 30px;
}

.p1 {
  text-align: center;
  margin-top: 200px;
}

span{
font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
font-size: 24px;
line-height:45px;
font-weight: lighter;

}

.frame {
  background-color: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 5px;
  border: 5px solid #000;
  border-style: groove;
  padding: 10px;
  padding-top: 50px;
  padding-bottom: 50px;
  padding-left: 50px;
  padding-right: 50px;
  width: 1350px;
  height: 330px;
  white-space: pre-wrap;
  word-wrap: break-word;
  height: fit-content;
  max-width: 100%;
  transform: translateX(12.5%);
}
 a {
  text-decoration: none;
  color: #000;
  font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  transition: color 0.2s ease;
}
a:hover {
  color: #ff0000;
}
.Title {
  display: flex;
  align-items: center;
}

.Title {
  margin-right: 20px;
}
#p9 {
  margin: 8px;
  margin-top: 16px;
  font-size: 16px;
  animation: slide-in 0.5s ease-in-out;
}
#btmode,#addbt{
    float: right;
}
#btback{
  position: fixed;
  left: 95%;
  transform: translateX(-50%);
  bottom: 1px;
}
#dis{
  padding-left: 20px;
}

@keyframes slide-in {
  0% {
    opacity: 0;
    transform: translateY(-10px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}


@keyframes slide-in {
  from {
    transform: translateX(-100%);
  }
  to {
    transform: translateX(0%);
  }
}

#buttonpage {
  animation: slide-in 1000ms;
}

</style>
