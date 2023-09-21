import { defineStore,acceptHMRUpdate } from "pinia";
import { ref } from 'vue'; 
import {DataAdmindetail, DataUserdetail,DataUser}from './getdata.js'
export const UseCounterStore = defineStore('counter',() => {
    const mode = 'active'
    const category = ref('')
    const page = ref('')

    const UserfetchId = async (id)=>{
            return await DataUserdetail(id)
    }
    const AdminfetchId = async (id)=>{
        return await DataAdmindetail(id)
}
   const Userfetch = async (id) =>{
     return await DataUser(id)
   }


    return { mode, category ,page,UserfetchId,AdminfetchId, Userfetch}


})
if(import.meta.hot){
    import.meta.hot.accept(acceptHMRUpdate(UseCounterStore,import.meta.hot))
}


