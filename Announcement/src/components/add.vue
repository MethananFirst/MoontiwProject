<script setup>
import {  useRouter } from 'vue-router';
import {  ref } from 'vue';
import { fetchData } from '../getdata'
import 'moment-timezone/builds/moment-timezone-with-data'
const router = useRouter();



const title = ref()
const des = ref()
const category = ref()
const pubDate = ref()
const pubtime = ref('06:00:00')
const clsDate = ref()
const clstime = ref('18:00:00')
const dis = ref()

const data = ref()
const dateTimee = ref()
const TitleCount = ref(0)
const DesCount = ref(0)

const apiUrl = import.meta.env.VITE_API_ROOT;


const categoryId = () => {
  let categoryan = category.value;
  let num = 0;
  switch (categoryan) {
    case "ทั่วไป":
    num = 1;
      break;
    case "ทุนการศึกษา":
    num = 2;
      break;
    case "หางาน":
    num = 3;
      break;
    case "ฝึกงาน":
    num = 4;
      break;
    default:
    num = 0;
  }
  category.value = num;
  return num;

}
const dataUtc = (dataDate, dataTime) => {
  if (dataDate !== null && dataDate !== undefined && dataTime !== null && dataTime !== undefined) {
    const newDatetime = dataDate + ' ' + dataTime
    const timeformat = new Date(newDatetime)
    timeformat.toLocaleString('en-GB')
    dateTimee.value = timeformat
    dateTimee.value = timeformat
    return timeformat.toISOString()

  } else {
    return null
  }
}

const cancelForm = () => {
  router.push({ name: 'allAnnouncement' })
}

const TitleCharacterCount = () => {
  TitleCount.value = title.value.length;
}
const DesCharacterCount = () => {
  DesCount.value = des.value.length;
}

const datatime = () => {

  if (!dis.value) {
    dis.value = 'N';
  } else {
    dis.value = 'Y';
  }
  categoryId(category.value)
  data.value = {
    "announcementTitle": title.value,
    "announcementDescription": des.value,
    "categoryId": category.value,
    "publishDate": dataUtc(pubDate.value, pubtime.value),
    "closeDate": dataUtc(clsDate.value, clstime.value),
    "announcementDisplay": dis.value
  }

}

const addAnnouncement = async () => {
  datatime()

  if (title.value === undefined || title.value.trim() === '') {
    alert('Please Input Title! || Data cannot be blank! ');
  }

  else if (des.value === undefined || des.value.trim() === '') {
    alert('Please Input Description! || Data cannot be blank!');
  }

  else if (category.value === 0) {
    alert("Please Input Category");
  }
  else if (pubDate.value !== undefined && pubtime.value === undefined) {
    alert("Please Input Publish Time");
  } 

  else if (clsDate.value !== undefined && clstime.value === undefined) {
    alert("Please Input Close Time");
  } 


  const currentTime = new Date().getTime();
  const selectedDateTime = new Date(dataUtc(pubDate.value, pubtime.value)).getTime();
  const selectedCloseTime = new Date(dataUtc(clsDate.value, clstime.value)).getTime();

  if (pubDate.value !== undefined && selectedDateTime < currentTime) {
    alert("Publish Date cannot be earlier than the current time");
  }
  if (clsDate.value !== undefined && selectedCloseTime < selectedDateTime) {
    alert("Closed Date cannot be earlier than the current time");
  } else {
    try {
      const url = `${apiUrl}/api/announcements`;
      const responseData = await fetchData(url, 'POST', data.value);
      console.log('Data sent:', data);
      console.log('Response data:', responseData);
      router.push({ name: 'allAnnouncement' })
    }catch (error) {
        console.error(error);
      }
    // try {
    //   const response = await axios.post(`${x}/api/announcements`, data.value);
    //   console.log(data.value);
    //   console.log(response.data);
    //   router.push({ name: 'allAnnouncement' })

    // } catch (error) {
    //   console.error(error);
    //   console.log(category.value)
    //   console.log(error);
    // }
  }
}
</script>

<template>
  <div class="frame1">
    <div class="list">
      <h1 style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;">Announcement Detail: </h1>


      <div class="Title">
        <label for="announcementTitle" class="ann-tt">
          <h2 style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;">Title</h2>
        </label>
        <p id="p9">( {{ TitleCount }}/200 )</p>
      </div>

      <input type="text" class="ann-title" name="announcementTitle" required style="width: 99%;" v-model="title"
        maxlength="200" @input="TitleCharacterCount"><br>

      <label for="categoryName" class="ann-ct">
        <h2 style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;">Category</h2>
      </label>
      <select name="categoryName" required class="ann-category" v-model="category">
        <option value="ทั่วไป">ทั่วไป</option>
        <option value="ทุนการศึกษา">ทุนการศึกษา</option>
        <option value="หางาน">หางาน</option>
        <option value="ฝึกงาน">ฝึกงาน</option>
      </select><br>
      <div class="Title">
        <label for="announcementDescription">
          <h2 style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;">Description </h2>
        </label>
        <p id="p9">( {{ DesCount }}/10,000 )</p>
      </div>
      <textarea class="ann-description" required style="height: 100px; width: 99%; resize: vertical;" v-model="des"
        maxlength="10000" @input="DesCharacterCount"></textarea><br>

      <label for="publishDate">
        <h2 style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;">PublishDate</h2>
      </label>
      <div style="display: flex; ">
        <input type="date" class="ann-publish-date" name="publishdate" style=" height: 20px; margin-right: 10px; "
          v-model="pubDate">
        <input v-if="pubDate === undefined" type="time" class="ann-publish-time" name="publishTime" v-model="pubtime"
          disabled>
        <input v-else type="time" class="ann-publish-time" name="publishTime" v-model="pubtime">
      </div>
      <label for="publishDate">
        <h2 style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;">Close Date</h2>
      </label>
      <div style="display: flex; ">
        <input type="date" class="ann-close-date" name="closeDate" style=" height: 20px; margin-right: 10px; "
          v-model="clsDate">
        <input v-if="clsDate === undefined" type="time" class="ann-closeTime-time" name="closeTime" v-model="clstime"
          disabled>
        <input v-else type="time" class="ann-closeTime-time" name="closeTime" v-model="clstime">
      </div>
      <label for="announcementDisplay">
        <h2 style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif; ">Display</h2>
      </label>
      <div style="display: flex; "><input type="checkbox" class="ann-display" name="announcementDisplay" id="Checkbox"
          style=" margin-right: 10px;" v-model="dis">
        <p class="ann-d"
          style="font-size: 16px; font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;">Check to show
          this announcement</p>

      </div>


      <button type="submit" class="ann-button" @click="addAnnouncement()" style="margin-right: 10px;">Submit</button>
      <button class="ann-button" @click="cancelForm">Cancel</button>


    </div>
  </div>
</template>
<style scoped>
.frame1 {
    background-color: #e7e7e7;
    padding: 20px;
    border-radius: 10px;
    width: 1350px;
    height: 850px;
    position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
  }
  .ann-title{
    height: 30px;

  }
  .ann-title:focus{
    border: 3px solid #ccc;
    outline: none;
    border-color: #000000;
  }
  .ann-title::placeholder {
    color: #ccc;
  }

  .ann-description:focus{
    border: 3px solid #ccc;
    outline: none;
    border-color: #000000;
  }
  .ann-description::placeholder {
    color: #ccc;
  }

  .ann-close-date:focus{
    border: 3px solid #ccc;
    outline: none;
    border-color: #000000;
  }

  .ann-close-time:focus{
    border: 3px solid #ccc;
    outline: none;
    border-color: #000000;
  }
  .ann-publish-date:focus{
    border: 3px solid #ccc;
    outline: none;
    border-color: #000000;
  }

  .ann-publish-time:focus{
    border: 3px solid #ccc;
    outline: none;
    border-color: #5f9ea0;
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