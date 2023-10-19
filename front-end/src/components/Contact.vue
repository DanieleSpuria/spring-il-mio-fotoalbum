<template>
  
  <h2 class="text-center border border-dark rounded">Contattami</h2>

  <router-link to="/">Home</router-link>

  <form
    @submit.prevent="sendMessage()"
  >

    <span v-if="sendS">Dove vai</span>

    <div class="text-center d-flex flex-column align-items-start mb-2 p-2">
      <label>Email</label>
      <input type="email" v-model="newMessage.email" name="email" class="form-control">

      <span v-if=controlMail()>Campo obbligatorio</span>
    </div>

    <div class="text-center d-flex flex-column align-items-start mb-2 p-2">
      <label>Messaggio</label>
      <textarea class="form-control" v-model="newMessage.text"  name="text" rows="5"></textarea>

      <span v-if=controlText()>Campo obbligatorio</span>
    </div>

    <button type="submit">Invia</button>

  </form>

</template>




<script setup>

import { ref } from 'vue';
import axios from 'axios';

const apiEmails = "http://localhost:8080/api/emails";
let sendE = ref(false);
let sendT = ref(false);
let sendS = ref(false);

const message = {
  email: "",
  text: ""
}

const newMessage = ref(message);

function sendMessage() {

  if (!sendE.value && !sendT.value) {
    axios.post(apiEmails, newMessage.value).then(result => {
      newMessage.value = message;
      sendE.value = false;
      sendT.value = false;
      sendS.value = false;
    })
  } else {
    sendS.value = true;
  }
}

function controlMail() {
  if (newMessage.value.email.length == 0) sendE.value = true;
  else sendE.value = false;

  return sendE.value;
}

function controlText() {
  if (newMessage.value.text.length == 0) sendT.value = true;
  else sendT.value = false;

  return sendT.value;
}

</script>

<style>

</style>