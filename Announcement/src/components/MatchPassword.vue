<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { fetchData } from '../getdata'
const router = useRouter();
const apiUrl = import.meta.env.VITE_API_ROOT;
const datamatch = ref()
const usernamem = ref('')
const passwordm = ref('')
const showusernamem = ref('')
const showpasswordm = ref('')
const showpasswordnm = ref('')

const matchpassword = async () => {
    showpasswordm.value = ''
    showpasswordnm.value = ''
    showusernamem.value = ''
    datamatch.value = {
        "username": usernamem.value,
        "password": passwordm.value
    }
    const url = `${apiUrl}/api/users/match`;
    const responseData = await fetchData(url, 'POST', datamatch.value);
    console.log('Data sent:', datamatch.value);
    console.log('Response data:', responseData);
    console.log(responseData.message);

    if (responseData.message != undefined) {

        if (responseData.message == 'Incorrect password') {
            showpasswordnm.value = 'Password NOT Matched'
        } else if (responseData.message == 'Username does not exist') {
            showusernamem.value = 'The specified username DOES NOT exist'
        }

    } else {
        showpasswordm.value = 'Password Matched'
    }
}

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
            <li class="nav-item"><router-link to="/admin/user" class="nav-link">User</router-link></li>
            <li class="nav-item"><router-link to="/admin/announcement" class="nav-link">Announcement</router-link></li>
            <li class="nav-item"><router-link to="/admin/user/match" class="nav-link">Match Password</router-link></li>
        </ul>
    </nav>


    <div class="app">
        <div class="frame">
            <div class="erorMessage" v-show="showusernamem">
                <p class="ann-message">{{ showusernamem }}</p>
            </div>
            <div class="erorMessage2" v-show="showpasswordm">
                <p class="ann-message">{{ showpasswordm }}</p>
            </div>
            <div class="erorMessage3" v-show="showpasswordnm">
                <p class="ann-message">{{ showpasswordnm }}</p>
            </div>
            <div class="inapp">
                <h1>Match Password</h1>
                <div class="input-group">
                    <input type="text" name="username" required maxlength="45" minlength="1" class="ann-username"
                        v-model.trim="usernamem" style="width: 70%;  ">
                    <label class="user-label">Username</label>
                </div>

                <div class="input-group">
                    <div class="group">
                        <svg stroke="currentColor" stroke-width="1.5" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"
                            class="icon">
                            <path
                                d="M16.5 10.5V6.75a4.5 4.5 0 10-9 0v3.75m-.75 11.25h10.5a2.25 2.25 0 002.25-2.25v-6.75a2.25 2.25 0 00-2.25-2.25H6.75a2.25 2.25 0 00-2.25 2.25v6.75a2.25 2.25 0 002.25 2.25z"
                                stroke-linejoin="round" stroke-linecap="round"></path>
                        </svg>
                        <input type="password" name="text" required maxlength="14" minlength="8" v-model.trim="passwordm"
                            class="ann-password" style="width: 70%;">
                        <label class="user-label2">Password</label>
                    </div>
                </div>
                <button class="ann-button" @click="matchpassword()">Match or not</button>
            </div>
        </div>
    </div>
</template>
<style scoped>
/* .app {
    background-color: #e7e7e7;

    margin-top: 25px;
    border-radius: 10px;
    width: 1350px;
    height: 830px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
} */
.erorMessage {
    font-family: Arial, sans-serif;
    margin: 20px;
    padding: 20px;
    border: 5px solid #e93939;
    border-radius: 10px;
    width: 850px; 
    margin: 0 auto;
    position: absolute;
    top: 10%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-align: center;
    color:  #e93939;
    background-color: #ffcccc;
}
.ann-message{
    font-family: Arial, sans-serif;
    font-size: 24px;
    font-weight: bold;
}
.erorMessage2 {
    font-family: Arial, sans-serif;
    margin: 20px;
    padding: 20px;
    border: 5px solid #008512;
    border-radius: 10px;
    width: 850px;
    margin: 0 auto;
    position: absolute;
    top: 10%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-align: center;
    color: #008512 ;
    background-color: #c6ffcd;
}
.erorMessage3 {
    font-family: Arial, sans-serif;
    margin: 20px;
    padding: 20px;
    border: 5px solid #e93939;
    border-radius: 10px;
    width: 850px; 
    margin: 0 auto;
    position: absolute;
    top: 10%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-align: center;
    color:  #e93939;
    background-color: #ffcccc;
}

.frame {
    background-color: #ffffff;
    border-radius: 10px;
    width: 1050px;
    height: 715px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

}

.inapp {
    padding-left: 15px;
    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}


.ann-username,
.ann-password {
    font-size: 21px;
}


.group {
    display: flex;
    line-height: 30px;
    align-items: center;
    position: relative;
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
    margin: 88px 2px;
    cursor: pointer;
    border-radius: 5px;
}

.inapp {
    /* background-color: #cecece; */
    font-family: Arial, sans-serif;
    margin: 20px;
    padding: 20px;
    border: 5px solid #ccc;
    border-radius: 10px;
    width: 850px;
    height: 350px;
    margin: 0 auto;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}

/* .ann-message {
    color: #5bc551;
    font-size: 24px;
    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
} */


h1 {
    font-size: 24px;
    /* margin-top: 10px; */
}

p {
    margin: 5px 0;
}

input {
    width: 150%;
    height: 45px;
    line-height: 30px;
    padding: 0 5rem;
    padding-left: 3rem;
    border: 2px solid transparent;
    border-radius: 10px;
    outline: none;
    transition: .5s ease;
    border: solid 1.5px #9e9e9e;
    transition: border 150ms cubic-bezier(0.4, 0, 0.2, 1);
}

.icon {
    position: absolute;
    left: 1rem;
    fill: none;
    width: 1rem;
    height: -1rem;
}

.input-group {
    position: relative;
    margin-top: 50px;
}

/* .ann-username {
    border: solid 1.5px #9e9e9e;
    border-radius: 1rem;
    background: none;
    color: #f5f5f5;
    transition: border 150ms cubic-bezier(0.4, 0, 0.2, 1);
} */

.user-label {
    position: absolute;
    left: 15px;
    color: #000000;
    pointer-events: none;
    transform: translateY(1rem);
    transition: 150ms cubic-bezier(0.4, 0, 0.2, 1);
    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;

}

.user-label2 {
    position: absolute;
    left: 40px;
    color: #000000;
    pointer-events: none;
    transform: translateY(0rem);
    transition: 150ms cubic-bezier(0.4, 0, 0.2, 1);
    z-index: 1;
    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}

.ann-password:focus,
.ann-password:valid {
    outline: none;
    border: 1.5px solid #000000;
}

.ann-password:focus~label,
.ann-password:valid~label {
    transform: translateY(-25px) scale(0.8);
    font-size: 20px;
    background-color: #212121;
    padding: 0 .2em;
    color: #ffffff;
}

.ann-username:focus,
.ann-username:valid {
    outline: none;
    border: 1.5px solid #000000;
}

.ann-username:focus~label,
.ann-username:valid~label {
    transform: translateY(-12px) scale(0.8);
    font-size: 20px;
    background-color: #212121;
    padding: 0 .2em;
    color: #ffffff;
}

.navbar {
    background-color: #333;
    color: white;
    height: 5vh;
    display: flex;
    justify-content: space-between;
    /* จัดให้ Element รายการและฝั่งซ้ายเต็มแนว */
    align-items: center;
    padding: 0 20px;
    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
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
    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
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

</style>