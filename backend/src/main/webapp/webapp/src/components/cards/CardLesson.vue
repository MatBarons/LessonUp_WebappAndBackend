<template>
  <div class="card">
    <div class="card-header">
      <h3>Prof. Matteo Barone</h3> <!-- {{ lesson.professor }} -->
    </div>
    <div class="card-body">
      <div class="container-subject">
        <h4>Matematica</h4>
      </div> <!-- {{ lesson.subject }} -->
      <div class="container-date">
        <h4>20/04/2023</h4>
      </div> <!-- {{ lesson.date }} -->
      <div class="container-time">
        <h4>16:00</h4>
      </div> <!-- {{ lesson.time }} -->
    </div>
    <div class="card-footer">
      <button class="btn" @click="addToCart">
        <i class="material-icons">add_shopping_cart</i>
        Aggiungi al carrello
      </button>
    </div>
  </div>
</template>

<script>

export default {
  name: 'CardLesson',
  props: {
    lesson: {
      type: Object,
      required: false
    }
  },
  methods: {
    addToCart() {
      // Aggiungi la lezione al carrello
    }
  }
}
</script>

<style lang="scss" scoped>
h3{
  color: black;
}

.card {
  box-shadow: none;
  display: flex;
  flex-direction: column;
  border-radius: 5px;
  margin-bottom: 20px;
  background-color: lightgrey;
  width: 15rem;
  max-height: 15rem;
  //clip-path: polygon(0% 0%,100% 5%, 100% 100%,100% 100%, 0% 100%);

  @media (max-width: 1024px){
    max-width: 10rem;
    max-height: 20rem;
  }

  .card-header {
    padding: 10px;
    border-top-left-radius: 5px;
    border-top-right-radius: 5px;
    text-align: center;
    background: linear-gradient(135deg, white 20px, transparent 20px);
  }

  .card-body {
    padding: 20px;
    .container-subject{
      color: black;
      display: flex;
      background-color: #007bff;
      border-radius: 5px;

      h4{
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
      }
    }
    .container-date{
      color: black;
      display: flex;
      background-color: aquamarine;
      border-radius: 5px;
      margin-top: 0.5rem;
    }
    .container-time{
      color: black;
      display: flex;
      background-color: blueviolet;
      border-radius: 5px;
      margin-top: 0.5rem;
    }

  }
  .card-footer {
    background-color: lightgrey;
    padding: 10px;
    border-bottom-left-radius: 5px;
    border-bottom-right-radius: 5px;
    text-align: center;
    .btn {
      background-color: cornflowerblue;
      color: #fff;
      border: none;
      border-radius: 3px;
      padding: 10px 20px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }
    .btn:hover {
      background-color: #0069d9;
    }
  }
}








</style>