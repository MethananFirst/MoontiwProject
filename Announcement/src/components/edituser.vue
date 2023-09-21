<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { FetchUser } from '../getdata'
import { fetchData } from '../getdata'
import { fetchDelete } from '../getdata'
import { UseCounterStore } from '../Store.js'
const stage = UseCounterStore()
const route = useRoute();
const announcementById = ref(route.params.id)

const apiUrl = import.meta.env.VITE_API_ROOT;

const UserList = ref([])
const datauseredit = ref()
const router = useRouter();
const check = ref(true)
const us = ref()
const na = ref()
const em = ref()
const ro = ref()

const Erusername = ref('')
 const Eremail = ref('')
 const Ername = ref('')

const changeTime = (dateTime) => {
  const time = new Date(dateTime)
  if (time.getFullYear() > 1970)
    return time.toLocaleString('en-GB', { hour12: false, year: 'numeric', month: 'short', day: '2-digit', hour: '2-digit', minute: '2-digit' })
  else return '-'
}

onMounted(async () => {
    UserList.value = await stage.Userfetch(announcementById.value);
    us.value = UserList.value.username
    na.value = UserList.value.name
    em.value = UserList.value.email
    ro.value = UserList.value.role
    console.log(UserList.value);
})

const checksubmit = () => {
  if (UserList.value.username ===  us.value &&
  UserList.value.name === na.value  &&
  UserList.value.email === em.value  &&
  UserList.value.role === ro.value  
  ) {
    check.value = true

  } else {
    check.value = false
  }
}

const Role = () => {
  let x = ro.value;
  let c = 0;
  switch (x) {
    case "announcer":
      c = 1;
      break;
    case "admin":
      c = 2;
      break;
    default:
      c = 1;
  }
  ro.value = c;
  return c;
}

const cancelForm = () => {
  router.push({ name: 'user' })
}

Role(ro.value)
const edituser = () =>{
  datauseredit.value = {
  "username":us.value,
  "name":na.value,
  "email":em.value,
  "role":ro.value
}
} 


const updateuser = async () => {
  edituser()
      const url = `${apiUrl}/api/users/${announcementById.value}`;
      const responseData = await fetchData(url, 'PUT', datauseredit.value);
      console.log('Data sent:', datauseredit.value);
      console.log('Response data:', responseData);
      if (responseData.detail != undefined) {
    for (let i = 0; i < responseData.detail.length; i++) {
      if (responseData.detail[i].field == 'username' ) {
        if (responseData.detail[i].errorMessage == 'must not be blank') {
          Erusername.value = responseData.detail[i].errorMessage;
        }else  if (responseData.detail[i].errorMessage == 'does not unique') {
          Erusername.value = responseData.detail[i].errorMessage;
        }
    }
      else if (responseData.detail[i].field == 'name') {
        if (responseData.detail[i].errorMessage == 'must not be blank') {
          Ername.value = responseData.detail[i].errorMessage;
      }else  if (responseData.detail[i].errorMessage == 'does not unique') {
          Ername.value = responseData.detail[i].errorMessage;
        }
      } else if (responseData.detail[i].field == 'email') {
        if (responseData.detail[i].errorMessage == 'must not be blank') {
          Eremail.value = responseData.detail[i].errorMessage;
      } else if (responseData.detail[i].errorMessage == 'Email should be valid'){
          Eremail.value = responseData.detail[i].errorMessage;
        }else if (responseData.detail[i].errorMessage == 'does not unique'){
          Eremail.value = responseData.detail[i].errorMessage;
    }
  }
}
}
   else {
    router.push({ name: 'user' });
  }
}

</script>
<template> 

        <div class="rectangle2">
          <div class="form">
          <h1 class="ann-title">User Detail:</h1>
          <div class="form2">
          <p>Username</p>
          <p class="ann-error-username">{{ Erusername }}</p>
          <input type="text" class="ann-username" name="username" required minlength="1" maxlength="45" style="width: 70%;" v-model.trim="us" @input="checksubmit"><br>
          <p>Name</p>
          <p class="ann-error-name">{{ Ername }}</p>
          <input type="text" class="ann-name" name="name" required minlength="1" maxlength="100" style="width: 70%;" v-model.trim="na" @input="checksubmit"><br>
          <p>Email</p>
          <p class="ann-error-email">{{ Eremail }}</p>
          <input type="email" class="ann-email" name="email" required minlength="1" maxlength="150" style="width: 70%;" v-model.trim="em" @input="checksubmit" pattern="^\w+([.\-]?\w+)@\w+([.\-]?\w+)\.\w{2,3}$"><br>
          <p>Role</p>
          <select name="role" required class="ann-role" style="width: 50%; " v-model="ro" @change="checksubmit">
        <option value="announcer">announcer</option>
        <option value="admin">admin</option>1
      </select><br><br>
     
          </div>
          </div>
          <span>created On : <span class="ann-created-on"> {{  changeTime(UserList.createdOn) }}</span></span> &nbsp;
          <span >Updated On : <span class="ann-updated-on">  {{  changeTime(UserList.updatedOn) }}</span></span>
          <div class="bt">
          <button type="submit" class="ann-button" @click="updateuser()" :disabled="check" style="margin-right: 10px;">Save</button>
          <button class="ann-button" @click="cancelForm">Cancel</button>
         </div> 
         
        </div>
  </template>
  <style scoped>
  .ann-error-username,
.ann-error-password,
.ann-error-email,
.ann-error-name {
  color: red;
}
  .form,span{
    font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  }
  .form2{
   padding-top: 10px;
  }
  .bt{
    padding-top: 15%;
  }
   
  .ann-title{
    font-size: 34px;
  }
  
  tr{
    font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
    font-size: 18px;
  }

  .rectangle2 {
    background-color: #e7e7e7;
    width: 60%;
    border-radius: 10px;
    padding-left: 15px;
    margin: 15px;
    justify-content: center;
    position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
  }

  
.ann-username,.ann-name,.ann-email,.ann-role{
  font-size: 21px;
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

  </style>