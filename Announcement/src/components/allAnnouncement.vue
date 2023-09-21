<script setup>
import { ref, onMounted } from 'vue';
import { Dataall} from '../getdata'
import { useRouter } from 'vue-router';
import { fetchDelete } from '../getdata'

const apiUrl = import.meta.env.VITE_API_ROOT;
const changeTime = (dateTime) => {
  const time = new Date(dateTime)
  if (time.getFullYear() > 1970)
    return time.toLocaleString('en-GB', { hour12: false, year: 'numeric', month: 'short', day: '2-digit', hour: '2-digit', minute: '2-digit' })
  else return '-'
}

const AnList = ref([])
const route = useRouter();

const getTimezone = new Intl.DateTimeFormat();
const timezoneCountry = ref(getTimezone.resolvedOptions().timeZone)



onMounted(async () => {
  AnList.value = await Dataall()
})

const goToDetail = (id) => {
  route.push({ name: "announcementDetail", params: { id } })
}

const confirmDeleteAnnouncement = async (id) => {
  const confirmed = window.confirm("Do you want to delete");
  if (confirmed) {
    await deleteAnnouncement(id);
  }
};

const deleteAnnouncement = async (id) => {
  try {
        
   const url = `${apiUrl}/api/announcements/${id}`;
   const responseData = await fetchDelete(url,'DELETE');
   AnList.value = AnList.value.filter((ann) => ann.id !== id);
  } catch (error) {
    console.error(error);
  }
};
</script>

<template>
<nav class="navbar">
    <div class="left-side">
      <img class="logo" src="/favicon2.png" />
      <h1 class="nav-item">
        <span class="nav-text">SAS</span>&nbsp;
        <span class="nav-text">Moontiw website</span>
      </h1>
    </div>
    <ul class="nav-list">
      <li class="nav-item"><router-link to="user" class="nav-link">User</router-link></li>
      <li class="nav-item"><router-link to="announcement" class="nav-link">Announcement</router-link></li>
      <li class="nav-item"><router-link to="/admin/user/match" class="nav-link">Match Password</router-link></li>
    </ul>
  </nav>


  <div>
    <h1
      style="text-align: center ; font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif; margin: 40px;">
      SIT Announcement System(SAS) </h1>
   

  </div>
  <div>
    <router-link :to="{ name: 'add' }"><button id="addbt" class="ann-button" style=" top: 85px; right: 10px;">Add
        Announcement</button></router-link>
  </div>
  <div>
    <p class="ann-timezone" style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;">Date/Time show in
      Timezone : {{ timezoneCountry }}</p>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Announcement Title</th>
          <th>Category</th>
          <th>Publish Date</th>
          <th>Close Date</th>
          <th>Announcement Display</th>
          <th colspan="2">Action</th>
        </tr>
      </thead>
      <tbody v-if="AnList?.length > 0">
        <tr
          v-for="({ id, announcementTitle, publishDate, closeDate, announcementDisplay, announcementCategory }, index) in AnList"
          :key="index" class="ann-item">
          <td> {{ index + 1 }}</td>
          <td class="ann-title">{{ announcementTitle }}</td>
          <td class="ann-category">{{ announcementCategory }}</td>
          <td class="ann-publish-date">{{ changeTime(publishDate) ? changeTime(publishDate) : '-' }}</td>
          <td class="ann-close-date">{{ changeTime(closeDate) ? changeTime(closeDate) : '-' }}</td>
          <td class="ann-display">{{ announcementDisplay }}</td>
          <td><button @click="goToDetail(id)" class="ann-button">view</button></td>
          <td><button @click="confirmDeleteAnnouncement(id)" class="ann-button">delete</button></td>
        </tr>
      </tbody>

    </table>
    <div v-show="AnList === null || AnList?.length === 0" class="p1">
      <p>No Announcement</p>
    </div>
  </div>

<RouterView/>
</template>

<style scoped>
.navbar {
  background-color: #333;
  color: white;
  height: 5vh;
  display: flex;
  justify-content: space-between; /* จัดให้ Element รายการและฝั่งซ้ายเต็มแนว */
  align-items: center;
  padding: 0 20px;
}

.left-side {
  display: flex;
  align-items: center;
}

.logo {
  width: 40px;
  height: 40px;
  margin-right: 10px;
}

.nav-list {
  list-style: none;
  padding: 0;
  display: flex;
  font-size: 24px;
}
.ann-timezone{
  padding-top: 25px;
  font-size: 16px;
}

.nav-item {
  margin-left: 20px;
  margin-right: 20px;
}

.nav-link {
  text-decoration: none;
  color: white;
  transition: color 0.3s;
}

.nav-link:hover {
  color: #ff9900;
}

.nav-text {
  margin: 0;
}

table {
  border-collapse: collapse;
  width: 100%;
  margin-left: auto;
  margin-right: auto;
}

th, td {
  text-align: left;
  padding: 8px;
}


th {
  background-color: #333;
  color: white;
  font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

tr:hover {
  
  transition-duration: 500ms;
  background-color: #e9e9e9;
}

.NoAn{
  text-align: center;
 }
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
</style>