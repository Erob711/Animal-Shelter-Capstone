<template>
  <body>
    <div class="login-wrapper">
      <div class="form-wrapper">
        <div id="login">
          <form @submit.prevent="login">
            <h1>Please Sign In</h1>
            <div role="alert" v-if="invalidCredentials">
              Invalid username and password!
            </div>
            <div role="alert" v-if="this.$route.query.registration">
              Thank you for registering, please sign in.
            </div>
            <div class="form-input-group">
              <label class="floating-placeholder" for="username"
                ></label
              >
              <ion-icon name="person-outline"></ion-icon
              ><input
                type="text"
                id="username"
                placeholder="Username"
                v-model="user.username"
                required
                autofocus
                class="placeholder-animate"
              />
            </div>
            <div class="form-input-group">
              <ion-icon name="lock-closed-outline"></ion-icon
              ><input
                type="password"
                id="password"
                placeholder="Password"
                v-model="user.password"
                required
                class="placeholder-animate"
              />
            </div>
            <button type="submit">Sign in</button>
            <p>
              <router-link :to="{ name: 'register' }"
                ><button>Need an account? Sign up.</button></router-link
              >
            </p>
          </form>
        </div>
      </div>
    </div>
  </body>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },

  //Reset
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user); 
            if (response.data.user.authorities[0].name === "ROLE_APPROVED") {
              this.$router.push("/change-password");
            } else if (
              response.data.user.authorities[0].name === "ROLE_PENDINGVOLUNTEER"
            ) {
              this.$router.push({ name: "pending-application" });
            } else {
              this.$router.push("/");
            }
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
h1 {
  border: 1px solid black;
  background: #62a18f;
  padding: 20px;
  border-radius: 50%;
  color:white;
}
body {
  background-image: url("../assets/background2.png");
  background-color: rgb(230, 222, 240);
  background-repeat: repeat;
  background-size: contain;
  background-position: center;
  min-height: 100%;
  min-width: 100%;
  margin: auto;
  padding: 0;
}
form {
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin-top: 0;
  max-width: 400px;
  margin: auto;
  padding: 80px;
  border: 2px solid #410553;
  border-radius: 10px;
  background-color: rgba(255, 255, 255, 0.671);
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);
}
.form-input-group {
  margin-bottom: 0.5rem;
  display: flex;
  flex-direction: row;
  align-items: center;
}
.form-input-group .icon {
  width: 30px;
  align-self: flex-start;
  margin-right: -10px;
}
label {
  margin-right: 0.5rem;
}
input {
  padding: 0.5rem;
  border: 2px solid black;
  padding: 10px;
  border-radius: 10px;
}
.placeholder-animate::-webkit-input-placeholder {
  transition: all 0.3s ease-out;
  opacity: 0.5;
  transform: translateY(0px);
}
.placeholder-animate {
  width: 100%;
  border: none;
  padding: 12px 20px;
  margin: 8px 0;
  box-sizing: border-box;
  border-radius: 5px;
  font-size: 16px;
  border: 2px solid rgb(0, 0, 0);
}
button {
  min-width: 130px;
  height: 40px;
  color: #fff;
  padding: -5px 5px;
  font-weight: bold;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  outline: none;
  border-radius: 20px;
  border: 2px solid #4c6e5c;
  background: #62a18f;
  margin-top: 0px;
}
.floating-placeholder {
  position: absolute;
  top: 12px;
  left: 20px;
  font-size: 16px;
  font-weight: bold;
  color: #ccc;
  transition: all 0.2s ease-out;
  pointer-events: none;
}
button:hover {
  background: #fff;
  color: #fff;
}
.login-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
body {
  overflow: hidden;
}
</style>