<template>

  <main>

    <h1 class="text-center border border-dark rounded">FotoAlbum</h1>

    <router-link to="/contact">Contattami</router-link> 

    <div class="text-center d-flex flex-column align-items-center mb-2 p-2">
      <label>Cerca una foto:</label>
      <input type="text" v-model="search" @keyup="searchPhotos()" class="form-control w-25">
    </div>

    <ul class="d-flex flex-wrap border p-3">
      <li
        v-for="photo in photos"
        :key="photo.id"
        class="d-flex flex-column border border-dark m-2"
      >
        <img :src="photo.url" alt="photo.title">
        <span>{{ photo.user.username }}</span>
        <span>{{ photo.title }}</span>
      </li>
    </ul>

  </main>

</template>




<script setup>

  import { ref, onMounted } from 'vue';
  import axios from 'axios'

  const apiPhotos = "http://localhost:8080/api/photos";
  const photos = ref(null);
  const search = ref('');

  function getPhotos() {
    axios.get(apiPhotos).then(result => {
      const data = result.data;  
      photos.value = data;
      console.log(photos.value);
    })
  }

  function searchPhotos() {
    axios.get(apiPhotos + "?search=" + search.value).then(result => {
      const data = result.data;  
      photos.value = data;
    })
  }

  onMounted(() => {
    getPhotos()
  }) 


</script>
