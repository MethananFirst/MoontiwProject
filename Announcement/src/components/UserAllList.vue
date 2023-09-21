<script setup>
import { ref, onMounted, capitalize, computed } from 'vue';
import { DataUserlist} from '../getdata'
import { useRouter } from 'vue-router';
import { UseCounterStore } from '../Store.js'

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

const countpages = ref([]) // จำนวนเลขหน้าที่แสดงบนปุ่ม 10 ปุ่ม
const numberpage = ref(1)
const sentpage = ref(0) // หน้า

// const lockprev = computed(()=> {
//   if(numberpage.value > 1) return false
// })

const NextPage = () => {
  
  if (numberpage.value < 10 && numberpage.value >= 1) {
    numberpage.value++
    sentpage.value = numberpage.value - 1
    stage.page = sentpage.value
    getdata()
  } else if (numberpage.value >= 10) {
    let last = countpages.value[countpages.value.length - 1] // ตัวสุดท้าย
    countpages.value.shift()
    countpages.value.push(last + 1)
    numberpage.value++
    sentpage.value = numberpage.value - 1
    stage.page = sentpage.value
    getdata()
  } else if (countpages.value[countpages.value.length - 1] == AnList.value.totalPages) {
    stage.page = sentpage.value
    getdata()
  }
}
const PrevPage = () => {
  if (numberpage.value < 10 && numberpage.value >= 2 && countpages.value[countpages.value.length - 1] == 10) {
    numberpage.value--
    sentpage.value = numberpage.value - 1
    stage.page = sentpage.value
    getdata()

  } else if (numberpage.value >= 10) {
    let firstPage = countpages.value[0]
    if (countpages.value[0] > 1) {
      countpages.value.unshift(firstPage - 1)
      countpages.value.pop()
    }
    numberpage.value--
    sentpage.value = numberpage.value - 1
    stage.page = sentpage.value
    getdata()

  } else if (numberpage.value == 1) {
    stage.page = sentpage.value
    getdata()
  } else if (countpages.value[countpages.value.length - 1] != 10) {

    let firstPage = countpages.value[0]
    if (countpages.value[0] > 1) {
      countpages.value.unshift(firstPage - 1)
      countpages.value.pop()
    }
    numberpage.value--
    sentpage.value = numberpage.value - 1
    stage.page = sentpage.value
    getdata()
  }
}
const clicknumber = (num) => {
  numberpage.value = num
  sentpage.value = numberpage.value - 1
  stage.page = sentpage.value
  console.log(stage.page);
  getdata()
}

const getdata = async () => {
  AnList.value = await DataUserlist(sentpage.value)
  console.log(sentpage.value);
}

const storePage = () => {
  countpages.value.length = 0
  let x = ''
  AnList.value.totalPages > 10 ? x = 10 : x = AnList.value.totalPages
  for (let i = 0; i < x; i++) {
    countpages.value.push(i + 1)
  }
}
const goToDetail = (id) => {
  route.push({ name: "Userdetail", params: { id } })
}
const goToDetailadmin = (id) => {
  route.push({ name: "announcementDetail", params: { id } })
}

onMounted(async () => {
  stage.page.length ==0 ?stage.page=0:stage.page
  console.log(stage.page);
  AnList.value = await DataUserlist(stage.page)
  if (stage.mode === 'active') {
    closebt.value = 'Closed Announcements'
  } else {
    closebt.value = 'Active Announcements'
  }
  storePage()
})

const stage = UseCounterStore()
const closebt = ref('Closed Announcements')
const category = ref('0')
const status = ref()

const changemode = async () => {
  if (closebt.value === 'Closed Announcements') {
    stage.mode = 'close'
    closebt.value = 'Active Announcements'
    status.value = 'Now You Are Page Close || Press the button to go Active mode'
    AnList.value = await DataUserlist()
    countpages.value = []
    storePage()
  } else {
    stage.mode = 'active'
    closebt.value = 'Closed Announcements'
    status.value = 'Now You Are Page Active || Press the button to go Close mode'
    AnList.value = await DataUserlist()
    countpages.value = []
    storePage()
  }
};

if (stage.mode === 'close') {
  status.value = 'Now You Are Page Closed || Press the button to go Active mode'
} else {
  status.value = 'Now You Are Page Active || Press the button to go Closed mode'
}

if (stage.category === 1){
  category.value = '1'
}
if (stage.category === 2){
  category.value = '2'
}
if (stage.category === 3){
  category.value = '3'
}
if (stage.category === 4){
  category.value = '4'
}

const changecategory = async (category) => {
  if (category === '0') {
    stage.category = 0
    // stage.page.length ==0 ?stage.page=0:stage.page
    // AnList.value = await DataUserlist(stage.page);
    AnList.value = await DataUserlist()
    storePage()
    numberpage.value = 1
  }
  if (category === '1') {
    stage.category = 1
    // stage.page.length ==0 ?stage.page=0:stage.page
    // AnList.value = await DataUserlist(stage.page);
    AnList.value = await DataUserlist()
    storePage()
    numberpage.value = 1
  }
  if (category === '2') {
    stage.category = 2
    // stage.page.length ==0 ?stage.page=0:stage.page
    // AnList.value = await DataUserlist(stage.page);
    AnList.value = await DataUserlist()
    storePage()
    numberpage.value = 1
  }
  if (category === '3') {
    stage.category = 3
    // stage.page.length ==0 ?stage.page=0:stage.page
    // AnList.value = await DataUserlist(stage.page);
    AnList.value = await DataUserlist()
    storePage()
    numberpage.value = 1
  }
  if (category === '4') {
    stage.category = 4
    // stage.page.length ==0 ?stage.page=0:stage.page
    // AnList.value = await DataUserlist(stage.page);
    AnList.value = await DataUserlist()
    storePage()
    numberpage.value = 1
  }
};
</script>

<template>
  <div>
    <h1
      style="text-align: center ; font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif; margin: 40px;">
      SIT Announcement System(SAS) </h1>
    <p style=" font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif; margin-left:0px">Date/Time show in
      Timezone : {{ timezoneCountry }}</p>
    <span style=" font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;">Choose Category: </span>
    <select name="categoryName" required class="ann-category-filter" v-model="category"
      @change="changecategory(category)">
      <option value="0">ทั้งหมด</option>
      <option value="1">ทั่วไป</option>
      <option value="2">ทุนการศึกษา</option>
      <option value="3">หางาน</option>
      <option value="4">ฝึกงาน</option>
    </select>
  </div>
  <div>
    <button @click="changemode" id="btmode" class="ann-button" style=" top: 85px; right: 10px; " >{{ closebt }}</button>
  </div>
  <p style=" font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;"> {{ status }} </p>
  <div>
    <table>
      <thead>
        <tr v-if="stage.mode === 'active'">
          <th>No.</th>
          <th>Title</th>
          <th>Category</th>
        </tr>
        <tr v-if="stage.mode === 'close'">
          <th>No.</th>
          <th>Title</th>
          <th style="color: red;">Close Date</th>
          <th>Category</th>
        </tr>
        <tr v-if="stage.mode === 'admin'">
          <th>No.</th>
          <th>Title</th>
          <th>Category</th>
          <th>Close Date</th>
          <th>Publish Date</th>
          <th>Announcement Display</th>
          <th colspan="2">Action</th>
        </tr>
      </thead>
      <tbody v-if="AnList.content?.length > 0 && stage.mode === 'active'">
        <tr v-for="({ id, announcementTitle, announcementCategory }, index) in AnList?.content" :key="index" class="ann-item">
          <td> {{ index + 1 }}</td>
          <router-link :to="``" @click="goToDetail(id)">
            <td class="ann-title" style="text-decoration: none; cursor: pointer;"> <p>{{ announcementTitle }}</p></td>
          </router-link>
          <td class="ann-category">{{ announcementCategory }}</td>
        </tr>
      </tbody>
      <tbody v-if="AnList.content?.length > 0 && stage.mode === 'close'">
        <tr v-for="({ id, announcementTitle, announcementCategory, closeDate }, index) in AnList?.content" :key="index"
          class="ann-item">
          <td> {{ index + 1 }}</td>
          <td class="ann-title" style="text-decoration: none; cursor: pointer;"><a @click="goToDetail(id)"><p>{{ announcementTitle }}</p></a></td>
          <td class="ann-close-date">{{ changeTime(closeDate) ? changeTime(closeDate) : '-' }}</td>
          <td class="ann-category"><p>{{ announcementCategory }}</p></td>
        </tr>
      </tbody>
      <tbody v-if="AnList.content?.length > 0 && stage.mode === 'admin'">
      
        <tr v-for="({ id, announcementTitle, publishDate, closeDate, announcementDisplay, announcementCategory }, index) in AnList?.content"
          :key="index" class="ann-item">
          <td> {{ index + 1 }}</td>
          <td class="ann-title">{{ announcementTitle }}</td>
          <td v-for="item in filteredItems" :key="item.id" class="ann-category">{{ announcementCategory }}</td>
          <td class="ann-publish-date">{{ changeTime(publishDate) ? changeTime(publishDate) : '-' }}</td>
          <td class="ann-close-date">{{ changeTime(closeDate) ? changeTime(closeDate) : '-' }}</td>
          <td class="ann-display">{{ announcementDisplay }}</td>
          <td><button @click="goToDetailadmin(id)" class="ann-button">view</button></td>
          <td><button @click="confirmDeleteAnnouncement(id)" class="ann-button">delete</button></td>
        </tr>
      </tbody>
   
    </table>
    <div v-if="AnList.content?.length < 1" class="p1">
      <span>No Announcement</span>
    </div>
  </div>
  <div class="page">
    <button v-if="AnList.totalPages > 1" class="ann-page-prev" @click="PrevPage" :disabled="AnList?.first" id="buttonpage">
      Prev
    </button>
    <button v-if="AnList.totalPages > 1" v-for="(n, index) in countpages" style="display: inline;" @click="clicknumber(n)"
      :class="[`ann-page-${index}`, { active: n === (stage.page+1)  }]" id="buttonpage">
      {{ n }}
    </button>
    <button v-if="AnList.totalPages > 1" class="ann-page-next" @click="NextPage" :disabled="AnList?.last" id="buttonpage">
      Next
    </button>
  </div>

  <RouterView />
</template>
<style scoped>

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

 a {
  text-decoration: none;
  color: #000;
  font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  transition: color 0.2s ease;
}
a:hover {
  color: #ff0000;
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