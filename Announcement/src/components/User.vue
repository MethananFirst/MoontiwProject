<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { FetchUser } from '../getdata'
import { fetchDelete } from '../getdata'
const getTimezone = new Intl.DateTimeFormat();
const timezoneCountry = ref(getTimezone.resolvedOptions().timeZone)
const apiUrl = import.meta.env.VITE_API_ROOT;
const UserList = ref([])

const route = useRouter();

const changeTime = (dateTime) => {
  const time = new Date(dateTime)
  if (time.getFullYear() > 1970)
    return time.toLocaleString('en-GB', { hour12: false, year: 'numeric', month: 'short', day: '2-digit', hour: '2-digit', minute: '2-digit' })
  else return '-'
}

onMounted(async () => {
  UserList.value = await FetchUser()
  console.log(UserList.value);
})

const goToDetail = (id) => {
  route.push({ name: "edituser", params: { id } })
}
const confirmDeleteAnnouncement = async (id) => {
  const confirmed = window.confirm("Do you want to delete");
  if (confirmed) {
    await deleteAnnouncement(id);
  }
};

const deleteAnnouncement = async (id) => {
  try {    
   const url = `${apiUrl}/api/users/${id}`;
   const responseData = await fetchDelete(url,'DELETE');
   UserList.value = UserList.value.filter((us) => us.id !== id);
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


    <div class="app">
      <!-- <div class="sidebar">
        <div class="menu">
        <h1 class="ann-app-title" style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;">SAS</h1>
          <p class="ann-menu" >Announcement</p>
          <p class="ann-menu">User</p>
        </div>
      </div> -->
      <div class="content">
        <h2 class="ann-title" style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif; text-align: center;">User Management</h2>
        <div>
   
      <router-link :to="{ name: 'adduser' }"> <button id="addbt" class="ann-button" style=" top: 85px;" >Add User</button></router-link>
  </div>
 
        <div class="rectangle">
          <p class="ann-timezone" style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;">Date/Time show in
      Timezone : {{ timezoneCountry }}</p>
          <table>
      <thead>
        <tr>
          <th>No.</th>
          <th>Username</th>
          <th>name</th>
          <th>Email</th>
          <th>Role</th>
          <th>Created On</th>
          <th>Updated On</th>
          <th colspan="2">Action</th>
        </tr>
      </thead>
      <tbody v-if="UserList?.length > 0">
        <tr
          v-for="({ id, username, name, email, role, createdOn , updatedOn }, index) in UserList"
          :key="index" class="ann-item">
          <td> {{ index + 1 }}</td>
          <td class="ann-username">{{ username }}</td>
          <td class="ann-name">{{ name }}</td>
          <td class="ann-email">{{ email }}</td>
          <td class="ann-role">{{ role }}</td>
          <td class="ann-created-on">{{ changeTime (createdOn) }}</td>
          <td class="ann-updated-on">{{ changeTime (updatedOn) }}</td>
          <td><button @click="goToDetail(id)" class="ann-button">edit</button></td>
          <td><button @click="confirmDeleteAnnouncement(id)" class="ann-button">delete</button></td>
        </tr>
      </tbody>

    </table>
    <div v-show="UserList === null || UserList?.length === 0" class="p1">
      <p class="showeror">No User</p>
    </div>
        </div>
      </div>
    </div>
    
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
  font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
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
  font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
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


/* .app {
 
    display: flex;
    height: 80vh;
    border: 10px solid #3b3b3b;
    margin: 100px ; 
  } */
  .sidebar {
    width: 15%;
    background-color: #f0f0f0;
    padding: 20px;
  }
  
  .menu {
    display: flex;
    flex-direction: column;
  }
  
  .content {
    width: 100%;
    /* padding: 30px;  */
  }
  
  .rectangle {
    width: 100%;
    height: 70%;
    justify-content: space-between
  }
  .rectangle2 {
    width: 98%;
    height: 99%;
    border: 5px solid #ccc;
    padding-left: 15px;
    justify-content: space-between
  }
.ann-menu{
  border-radius: 15px;
  align-items: center;
  height: 50px;
  
}
.ann-timezone{
  padding-top: 30px;
}
.ann-menu:hover{
  background: rgb(107, 107, 107);
  color: white;
}
.form{
  font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}
.form2{
 padding-top: 10px;
}
.bt{
  padding-top: 15%;
}
.ann-button{
   float: right;
}
.ann-title{
  font-size: 34px;
}

tr{
  font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  font-size: 18px;
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
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

tr:hover {
  
  transition-duration: 500ms;
  background-color: #e9e9e9;
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
.showeror{
  text-align: center;
  font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  font-size: 38px;
}
</style>