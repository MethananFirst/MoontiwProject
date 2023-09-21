<script setup>
import { ref,watch } from 'vue';
import { useRouter } from 'vue-router';
import { fetchData } from '../getdata'
const router = useRouter();
const apiUrl = import.meta.env.VITE_API_ROOT;


const datauser = ref()
const username = ref('')
const name = ref('')
const email = ref('')
const password = ref('')
const role = ref('announcer')


const confirmpassword = ref('')


const Errorusername = ref('')
const Errorpassword = ref('')
const Errorconfirmpassword = ref('')
const Erroremail = ref('')
const Errorname = ref('')


const cancelForm = () => {
  router.push({ name: 'user' })
}
const RoleID = () => {
  let roleuser = role.value;
  let num = 0;
  switch (roleuser) {
    case "announcer":
      num = 2;
      break;
    case "admin":
      num = 1;
      break;
    default:
      num = 2;
  }
  role.value = num;
  return num;
}


const data = () => {
  RoleID(role.value)
  datauser.value = {
    "username": username.value,
    "name": name.value,
    "email": email.value,
    "role": role.value,
    "password": password.value
  }
}

watch(username, (newUsername) => {
  if (newUsername.length > 0) {
    Errorusername.value = '';
  }
});
watch(name, (newname) => {
  if (newname.length > 0) {
    Errorname.value = '';
  }
});
watch(password, (newpassword) => {
  if (newpassword.length > 0) {
    Errorpassword.value = '';
  }
});
watch(email, (newemail) => {
  if (newemail.length > 0) {
    Erroremail.value = '';
  }
});
const adduser = async () => {
  data()
 

if (confirmpassword.value !== password.value) {
  Errorpassword.value = 'The password DOES NOT match';
  
}
 else if (username.value.length == 0) {
  Errorusername.value = 'Username is required';

}else if (password.value.length == 0) {
  Errorpassword.value = ' is required';
 
} else if (name.value.length == 0) {
  Errorname.value = 'Name is required';
 
}  else if (email.value.length == 0) {
  Erroremail.value = 'Email is required';

}

 else {
 
  const url = `${apiUrl}/api/users`;
  const responseData = await fetchData(url, 'POST', datauser.value);
  console.log('Data sent:', datauser.value);
  console.log('Response data:', responseData);
  console.log(responseData.detail);
 


  if (responseData.detail != undefined) {
    for (let i = 0; i < responseData.detail.length; i++) {
      if (responseData.detail[i].field == 'username' ) {
        if (responseData.detail[i].errorMessage == 'must not be blank') {
          Errorusername.value = responseData.detail[i].errorMessage;
        }else  if (responseData.detail[i].errorMessage == 'does not unique') {
          Errorusername.value = responseData.detail[i].errorMessage;
        }
      } else if (responseData.detail[i].field == 'name') {
        if (responseData.detail[i].errorMessage == 'must not be blank') {
          Errorname.value = responseData.detail[i].errorMessage;
        }else if (responseData.detail[i].errorMessage == 'does not unique'){
          Errorname.value = responseData.detail[i].errorMessage;
        }
      } else if (responseData.detail[i].field == 'email') {
        if (responseData.detail[i].errorMessage == 'must not be blank') {
          Erroremail.value = responseData.detail[i].errorMessage;
        }else if (responseData.detail[i].errorMessage == 'Email should be valid'){
          Erroremail.value = responseData.detail[i].errorMessage;
        }else if (responseData.detail[i].errorMessage == 'does not unique'){
          Erroremail.value = responseData.detail[i].errorMessage;
        }
      } else if (responseData.detail[i].field == 'password') {
         if (responseData.detail[i].errorMessage == 'size must be between 8 and 14' && password.value.length < 8) {
          Errorpassword.value = responseData.detail[i].errorMessage;
          Errorconfirmpassword.value = responseData.detail[i].errorMessage;
        } else if (responseData.detail[i].errorMessage == 'must be 8-14 characters long, at least 1 of uppercase, lowercase, number and special characters' && password.value.length >= 8) {
          Errorpassword.value = responseData.detail[i].errorMessage;
          Errorconfirmpassword.value = '';
        }
     
    }

  }
  } else {

   
    router.push({ name: 'user' });
  }
}

}
</script>
<template>
  <div class="rectangle2">
  
      <h1 class="ann-title">User Detail</h1>
          <p>Username</p>
        <input type="text" class="ann-username"  required minlength="1" maxlength="45" style="width: 70%;"
          v-model.trim="username"> &nbsp;<br>
        <p class="ann-error-username">{{ Errorusername }}</p>
        <p>Password   <span v-show="password!=confirmpassword" style="color: red;">Password DOES NOT match</span> </p>
        <input type="password" class="ann-password"  required minlength="8" maxlength="14"
          style="width: 70%;" v-model.trim="password"> &nbsp;  <br>
        <p class="ann-error-password" v-show="Errorpassword">Password {{ Errorpassword }}</p>
        <p>Confirm Password <span v-show="password!=confirmpassword" style="color: red;">Password DOES NOT match</span></p>
        <input type="password" class="ann-confirm-password"  required minlength="8" maxlength="14"
          style="width: 70%;" v-model.trim="confirmpassword">&nbsp;   &nbsp;<br>
        <p class="ann-error-password" v-show="Errorconfirmpassword">Confirm password {{ Errorconfirmpassword }}</p>
        <p>Name </p>
        <input type="text" class="ann-name"  required minlength="1" maxlength="100" style="width: 70%;"
          v-model.trim="name">&nbsp;   &nbsp;
         <br>
        <p class="ann-error-name">{{ Errorname }}</p>
        <p>Email</p>
        <input type="email" class="ann-email"  required minlength="1" maxlength="150" style="width: 70%;"
          v-model.trim="email" pattern="^\w+([.\-]?\w+)@\w+([.\-]?\w+)\.\w{2,3}$"> &nbsp;&nbsp;<br>
        <p class="ann-error-email">{{ Erroremail }}</p>
        <p>Role</p>
        <select class="ann-role" style="width: 50%; " v-model="role">
          <option value="announcer">announcer</option>
          <option value="admin">admin</option>
        </select>
        <div class="bt">
      <button type="button"  class="ann-button" @click="adduser()"  style="margin-right: 10px;">Save</button>
      <button class="ann-button"  @click="cancelForm()">Cancel</button>
    </div>

      </div>
  
</template>

<style scoped>
.ann-error-username,
.ann-error-email,
.ann-error-name,
.ann-error-password {
  color: red;
  font-size: 14px;
  transition: color 10.3s ease-in-out, font-size 0.3s ease-in-out;
}




.rectangle2 {
  background-color: #e7e7e7;
  width: 55%;
  border-radius: 10px;
  padding-left: 15px;
  margin: 15px;
  justify-content: center;
  position: absolute;
  top: 50%;
  left: 50%;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  transform: translate(-50%, -50%);
}


.bt {

}

.ann-username,
.ann-name,
.ann-email,
.ann-role,
.ann-password,
.ann-confirm-password {
  font-size: 21px;
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
p{
  font-size: 22px;
}
span{
  font-size: 14px;
 
}
</style>