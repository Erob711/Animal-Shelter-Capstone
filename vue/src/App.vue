<template>
    <div id="nav">
    <router-view/>
    <the-header class="the-header"></the-header>
    <sidebar></sidebar>
    <the-footer></the-footer>

    </div>

    
</template>
<script>
import TheHeader from './components/TheHeader.vue';
import TheFooter from './components/TheFooter.vue';


export default {
  name: 'App',
  components: {
    TheHeader,
    TheFooter,



  }
}
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  background-size:cover;
  height: 100vh;
  display:flex;

}
#nav {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
} 
button {
  min-width: 130px;
  height: 40px;
  color: #fff;
  padding: 5px 10px;
  font-weight: bold;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  outline: none;
  border-radius: 5px;
  z-index: 0;
  background: #fff;
  overflow: hidden;
  border: 2px solid #2c0b8e;
  color: #2c0b8e;
}
button:hover {
  color: #fff;
}
button:hover:after {
  width: 100%;
}
button:after {
  content: "";
  position: absolute;
  z-index: -1;
  transition: all 0.3s ease;
  left: 0;
  top: 0;
  width: 0;
  height: 100%;
  background: #93789e;
}
::-webkit-scrollbar {
  width: 20px;
  height: 8px;
}

::-webkit-scrollbar-track {
  background-color: rgb(195, 226, 199);
  width: 30px;
}

::-webkit-scrollbar-thumb {
  background-color: rgb(206, 191, 224);
  border-radius: 10px;
}

::-webkit-scrollbar-thumb:hover {
  background-color: #bbb;
}
.the-header{
  display: flex;
  
}
</style>