<template>
  <button class="btn">
    <i class="material-icons">check</i>
    <h4>Lezione confermata</h4>
  </button>
</template>

<script>
export default {
  name: "AlreadyConfirmedButton"
}
</script>

<style lang="scss" scoped>
.btn {
  background-color: lighten(#ffcc00,25);
  color: black;
  border: none;
  border-radius: 3px;
  padding: 10px 20px;
  cursor: default;
  transition: background-color 0.3s ease;
  display: flex;
  align-content: center;
  h4{
    padding-left: 0.25rem;
    padding-top: 0.3rem;
  }

}
</style>