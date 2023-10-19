<template>
  
  <h2 class="text-center border border-dark rounded">Contattami</h2>

  <router-link to="/">Home</router-link>

  <span v-if="send">{{ msg }}</span>

  <form
    @submit.prevent="sendMessage()"
    v-if="viewForm"
  >

    <div class="text-center d-flex flex-column align-items-start mb-2 p-2">
      <label>Email</label>
      <input type="email" v-model="newMessage.email" name="email" class="form-control">

      <span v-if=controlMail()>Email obbligatoria</span>
    </div>

    <div class="text-center d-flex flex-column align-items-start mb-2 p-2">
      <label>Messaggio</label>
      <textarea class="form-control" v-model="newMessage.text"  name="text" rows="5"></textarea>

      <span v-if=controlText()>Messaggio obbligatorio</span>
      <span v-if="serverErrors.text != null">{{ serverErrors.text }}</span>
    </div>

    <button type="submit">Invia</button>

  </form>

  <button
    v-if="rel"
    @click="reload"
  >Invia un nuovo messaggio</button>

</template>




<script setup>

import { onMounted, ref } from 'vue';
import { router } from '../router/router';
import axios from 'axios';




const apiEmails = "http://localhost:8080/api/emails";

let viewForm = ref(true);
let send     = ref(false);
let rel      = ref(false);
let msg      = ref('');

const message = {
  email: "",
  text: ""
}
const newMessage = ref({ ...message });

const serverErrors = ref([]);




function sendMessage() {

  serverErrors.value = [];
  send.value = false;

  if (!controlMail() && !controlText()) {
    axios.post(apiEmails, newMessage.value).then(result => {
      viewForm.value = false;
      rel.value = true;
      send.value = true;
      msg.value = 'Messaggio inviato correttamente.';
    }).catch(error => {
      serverErrors.value = error.response.data;
      console.log(serverErrors.value);
    }) 
  } else {
    send.value = true;
    msg.value = 'Devi compilare entrambi i campi.';
  }
}

function controlMail() {
  return newMessage.value.email.length == 0;
}

function controlText() {
  return newMessage.value.text.length == 0;
}

function reload() {
  send.value = false;
  rel.value = false;
  viewForm.value = true;
  newMessage.value = {...message};
  router.push({ name: 'contact' })
}

</script>

<style>

</style>