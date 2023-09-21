
import { UseCounterStore } from "./Store.js";




const apiUrl = import.meta.env.VITE_API_ROOT;

const fetchData = async (url, method, data) => {
  try {
    const response = await fetch(url, {
      method: method,
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    });

    if (response.ok) {
      return response;
    } else {
      let error = await response.json()
      return error
    }
  } catch (error) {
    throw error;
  }
};

const fetchDelete = async (url, method) => {
  try {
    const response = await fetch(url, {
      method: method,
      headers: {
        'Content-Type': 'application/json'
      }
    });

  } catch (error) {
    throw error;
  }
};

const Dataall = async () => {
  try {
    const response = await fetch(`${apiUrl}/api/announcements/admin`);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error(error);
  }
};
const FetchUser = async () => {
  try {
    const response = await fetch(`${apiUrl}/api/users`);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error(error);
  }
};



const DataUserlist = async (page) => {

  const stage = UseCounterStore();
  if (page == undefined) {
    page = 0;
  }
  let path = "";

  if (stage.mode === "close") {
    path = `${apiUrl}/api/announcements/pages?mode=close&category=${stage.category}&page=${page}`;
  } else if (stage.mode === "admin") {
    path = `${apiUrl}/api/announcements/pages?mode=admin`;
  } else {
    path = `${apiUrl}/api/announcements/pages?mode=active&category=${stage.category}&page=${page}`;

  }
  try {
    const response = await fetch(`${path}`);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error(error);
  }
};

const DataUserdetail = async (id) => {
  try {
    const response = await fetch(`${apiUrl}/api/announcements/${id}`);
    const data = await response.json();
    if(data.status===404 || data.status===400){
      alert("The requested page is not available!");
      window.location='/ssi2/announcement'
    }
    return data;
  } catch (error) {
    console.error(error);
  }
};
const DataAdmindetail = async (id) => {

  try {
    const response = await fetch(`${apiUrl}/api/announcements/${id}`);
    const data = await response.json();
    if(data.status===404 || data.status===400){
      alert("The requested page is not available!");
      window.location='/ssi2/admin/announcement'
    }
    return data;
  } catch (error) {
    console.error(error);
  }
};
const DataUser = async (id) => {
  try {
    const response = await fetch(`${apiUrl}/api/users/${id}`);
    const data = await response.json();
    if(data.status===404 || data.status===400){
      alert("The requested page is not available!");
      window.location='/ssi2/admin/announcement'
    }
    return data;
  } catch (error) {
    console.error(error);
  }
};

export { Dataall,DataUserlist, DataUserdetail,DataAdmindetail,fetchData,fetchDelete,FetchUser,DataUser };
