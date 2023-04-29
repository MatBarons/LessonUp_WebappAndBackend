<template>
  <aside class="primary">
    <h2>Lesson Up</h2>
    <h3>Admin console</h3>
    <h3>Menu</h3>
    <div class="menu">
      <router-link to="/lectures" class="button">
        <span class="material-icons">home</span>
        <span class="text">Lezioni</span>
      </router-link>
      <router-link to="/courses" class="button">
        <span class="material-icons">shopping_cart</span>
        <span class="text">Corsi</span>
      </router-link>
      <router-link to="/users" class="button">
        <span class="material-icons">person</span>
        <span class="text">Utenti</span>
      </router-link>
      <router-link to="/teachings" class="button">
        <span class="material-icons">person</span>
        <span class="text">Docenza</span>
      </router-link>
    </div>
    <div class="logo">
      <img src="../../assets/images/logo.png" alt="logo" />
    </div>
  </aside>
</template>

<script>
export default {
  name: "SidebarAdmin"
}
</script>

<style lang="scss" scoped>
aside {
  position: relative;
  display: flex;
  flex-direction: column;
  overflow: auto;
  color: black;
  width: 12vw;
  height: 110%;
  min-height: 100vh;
  background: #b4b1b1;
  padding: 1rem;
  box-shadow: 2px 2px 2px 2px rgba(0,0,0,0.15);
  h2{
    color: black;
    font-weight: bold;
    padding-bottom: 3rem;
  }
  .logo {
    bottom: 0px;
    position: absolute;
    margin: 2rem;
    img {
      width: 100%;
      height: 100%;
    }
  }
  h3 {
    color: black;
    font-weight: bold;
    font-size: 0.875rem;
    margin-bottom: 0.5rem;
    text-transform: uppercase;
    margin-top: 2rem;
  }
  .menu {
    margin: 0 -1rem;

    router-link{
    }
    .button {
      display: flex;
      align-items: center;
      text-decoration: none;
      padding: 0.5rem 1rem 2rem;
      .material-icons {
        font-size: 2rem;
        color: black;
      }
      .text {
        color: black;
        font-size: 1rem;
        padding-left: 1rem;
      }
      &:hover {
        background-color: var(--dark-alt);
        .material-icons, .text {
          color: black;
        }
      }
      &.router-link-exact-active {
        background-color: var(--dark-alt);
        border-right: 5px solid #009b4d;
        .material-icons, .text {
          color: #009b4d ;
        }
      }
    }
  }
  .footer {
    opacity: 0;
    p {
      font-size: 0.875rem;
      color: var(--grey);
    }
  }
  @media (max-width: 1024px) {
    position: absolute;
    z-index: 99;
  }
}
</style>