<script setup>
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';
import { fetchData } from '../getdata'
import { ref, onMounted, watch } from 'vue';
import { UseCounterStore } from '../Store.js'
import 'moment-timezone/builds/moment-timezone-with-data'
const stage = UseCounterStore()
const route = useRoute();
const announcementById = ref(route.params.id)

const AnList = ref([]);
const router = useRouter();



const pdate = ref()
const ptime = ref()
const cdate = ref()
const ctime = ref()
const dis = ref()
const datacopy = ref()
const dataall = ref()
const dateTimee = ref()

const title = ref()
const category = ref()
const des = ref()
const checkx = ref(true)

const TitleCount = ref(0)
const DesCount = ref(0)

const apiUrl = import.meta.env.VITE_API_ROOT;


onMounted(async () => {
  AnList.value = await stage.AdminfetchId(announcementById.value);
  if (AnList.value.publishDate != null) {
    ptime.value = chagetime(AnList.value.publishDate)
    pdate.value = changedate(AnList.value.publishDate)
  }
  if (AnList.value.closeDate != null) {
    ctime.value = chagetime(AnList.value.closeDate)
    cdate.value = changedate(AnList.value.closeDate)
  }
  dis.value = AnList.value.announcementDisplay === 'Y' ? true : false
  title.value = AnList.value.announcementTitle
  category.value = AnList.value.announcementCategory
  des.value = AnList.value.announcementDescription

  datacopy.value = {
    "title": AnList.value.announcementTitle,
    "category": AnList.value.announcementCategory,
    "des": AnList.value.announcementDescription,
    "pdate": changedate(AnList.value.publishDate),
    "ptime": chagetime(AnList.value.publishDate),
    "cdate": changedate(AnList.value.closeDate),
    "ctime": chagetime(AnList.value.closeDate),
    "dis": AnList.value.announcementDisplay === 'Y' ? true : false
  }
})


const changedate = (dateTime) => {
  const date = new Date(dateTime)
  if (date.getFullYear() > 1970)
    return date.getFullYear() + '-' + (date.getMonth() + 1).toString().padStart(2, '0') + '-' + date.getDate().toString().padStart(2, '0')
}
const chagetime = (date) => {
  const time = new Date(date)
  return time.toLocaleString('en-GB', { hour: '2-digit', minute: '2-digit' })
}
const cancel = () => {
  router.push({ name: 'announcementDetail', params: { id: announcementById.value } })
}
const TitleCharacterCount = () => {
  TitleCount.value = title.value.length;
}
const DesCharacterCount = () => {
  DesCount.value = des.value.length;
}

watch(title, TitleCharacterCount);
watch(des, DesCharacterCount);

const data = () => {
  if (!dis.value) {
    dis.value = 'N';
  } else {
    dis.value = 'Y';
  }

  const categoryId = (x) => {
    let c = 0;
    switch (x) {
      case "ทั่วไป":
        c = 1;
        break;
      case "ทุนการศึกษา":
        c = 2;
        break;
      case "หางาน":
        c = 3;
        break;
      case "ฝึกงาน":
        c = 4;
        break;
      default:
        c = 0;
    }

    category.value = c; // กำหนดค่า category เป็นค่า c ที่ได้
    return c;
  }

  categoryId(category.value)

  dataall.value = {
    "announcementTitle": title.value,
    "announcementDescription": des.value,
    "categoryId": category.value,
    "publishDate": dataUtc(pdate.value, ptime.value),
    "closeDate": dataUtc(cdate.value, ctime.value),
    "announcementDisplay": dis.value
  }
}

const dataUtc = (dataDate, dataTime) => {
  if (dataDate !== null && dataDate !== undefined && dataTime !== null && dataTime !== undefined && dataDate?.length != 0 && dataTime?.length != 0) {
    const newDatetime = dataDate + ' ' + dataTime
    const timeformat = new Date(newDatetime)
    timeformat.toLocaleString('en-GB')
    dateTimee.value = timeformat
    return timeformat.toISOString()
  } else {
    return null
  }
}




const checksubmit = () => {
  const publistd = AnList.value.publishDate != null ? AnList.value.publishDate.toLocaleString('en-GB') : null
  const clostd = AnList.value.closeDate != null ? AnList.value.closeDate.toLocaleString('en-GB') : null

  const publistdn = dataUtc(pdate.value, ptime.value)
  const clostdn = dataUtc(cdate.value, ctime.value)
  if (AnList.value.announcementTitle === title.value
    && AnList.value.announcementCategory === category.value
    && AnList.value.announcementDescription === des.value
    && publistd === publistdn
    && clostd === clostdn
    && AnList.value.announcementDisplay === dis.value
  ) {
    checkx.value = true

  } else {
    checkx.value = false
  }

}

const updateAnnouncement = async () => {
  data();

  if (title.value === undefined || title.value.trim() === '') {
    alert('Please Input Title! || Data cannot be blank!');
  }

  else if (des.value === undefined || des.value.trim() === '') {
    alert('Please Input Description! || Data cannot be blank!');
  }

  else if (category.value === 0) {
    alert("Please Input Category");
  }

  else if (pdate.value !== undefined && ptime.value === undefined) {
    alert("Please Input Publish Time");
  } 
  else if (cdate.value !== undefined && ctime.value === undefined) {
    alert("Please Input Close Time");
  } 
 
  const currentTime = new Date().getTime();
  const selectedDateTime = new Date(dataUtc(pdate.value, ptime.value)).getTime();
  const selectedCloseTime = new Date(dataUtc(cdate.value, ctime.value)).getTime();

  if (pdate.value !== undefined && selectedDateTime < currentTime) {
    alert("Publish Date cannot be earlier than the current time");
  }
  else if (cdate.value !== undefined && selectedCloseTime < selectedDateTime) {
    alert("Closed Date cannot be earlier than the current time");
  } else {
    try {
        const url = `${apiUrl}/api/announcements/${announcementById.value}`;
        const data = dataall.value;
        const responseData = await fetchData(url, 'PUT', data);
        console.log('Data sent:', data);
        console.log('Response data:', responseData);
        router.push({ name: 'announcementDetail', params: { id: announcementById.value } });
      } catch (error) {
        console.error(error);
      }
    }
  // try {
  //   // const response = await axios.put(`${x}/api/announcements/${announcementById.value}`, dataall.value);
  //   const response = await fetch(`${x}/api/announcements/${announcementById.value}`, {
  //   method: 'PUT',
  //   headers: {
  //     'Content-Type': 'application/json'
  //   },
  //   body: JSON.stringify(dataall.value)
  // });
  //   console.log(dataall.value);
  //   console.log(response.data);
  //   router.push({ name: 'announcementDetail', params: { id: announcementById.value } });
  // } catch (error) {
  //   console.error(error);
  // }

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
        @input="checksubmit(); TitleCharacterCount()" maxlength="200"><br>

      <label for="categoryName" class="ann-ct">
        <h2 style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;">Category</h2>
      </label>
      <select name="categoryName" required class="ann-category" v-model="category" @change="checksubmit">
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

      <textarea class="ann-description" name="announcementDescription" required
        style="height: 100px; width: 99%; resize: vertical;" v-model="des" @input="checksubmit(); DesCharacterCount()"
        maxlength="10000"></textarea><br>

      <label for="publishDate">
        <h2 style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;">PublishDate</h2>
      </label>
      <div style="display: flex; ">
        <!-- <input type="text" class="ann-publish-time" name="publishdate"  style=" height: 20px; margin-right: 10px; " v-model="changeTime(AnList.publishDate)">  -->

        <input type="date" class="ann-publish-date" name="publishdate" style="height: 20px; margin-right: 10px;"
          v-model="pdate" @input="checksubmit">
        <!-- <input type="time" class="ann-publish-time" name="publishtime" style="height: 20px; margin-right: 10px;" v-model="ptime" @input="checksubmit"> -->
        <input v-if="pdate === undefined" type="time" class="ann-publish-time" name="publishTime" v-model="ptime"
          disabled>
        <input v-else type="time" class="ann-publish-time" name="publishTime" v-model="ptime">

      </div>
      <label for="closeDate">
        <h2 style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;">Close Date</h2>
      </label>
      <div style="display: flex; ">
        <input type="date" class="ann-close-date" name="closedate" style="height: 20px; margin-right: 10px;"
          v-model="cdate" @input="checksubmit">
        <!-- <input type="time" class="ann-close-time" name="closetime" style="height: 20px; margin-right: 10px;" v-model="ctime" @input="checksubmit"> -->
        <input v-if="cdate === undefined" type="time" class="ann-closeTime-time" name="closeTime" v-model="ctime"
          disabled>
      <input v-else type="time" class="ann-closeTime-time" name="closeTime" v-model="ctime">
    </div>
    <label for="announcementDisplay">
      <h2 style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif; ">Display</h2>
    </label>
    <div style="display: flex; "><input type="checkbox" class="ann-display" name="announcementDisplay" id="Checkbox"
        style=" margin-right: 10px;" v-model="dis" @input="checksubmit">
      <p class="ann-d"
        style="font-size: 16px; font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;">Check to show
        this announcement</p>

    </div>


    <button type="submit" class="ann-button" @click="updateAnnouncement" style="margin-right: 10px;"
      :disabled="checkx">Submit</button>
    <button class="ann-button" @click="cancel">Cancel</button>


  </div>
</div>
</template>

<style scoped>
.frame1 {
    background-color: #e7e7e7;
    padding: 20px;
    border-radius: 10px;
    width: 1350px;
    height: 830px;
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