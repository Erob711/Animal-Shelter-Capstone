<template>
  <body>
    <div class="register-wrapper">
      <div class="form-wrapper">
        <div id="register" class="text-center">
          <form @submit.prevent="register" v-if="!showVolunteerApplication">
            <h1>Create Volunteer Account</h1>
            <div role="alert" v-if="registrationErrors">
              {{ registrationErrorMsg }}
            </div>
            <div class="form-input-group">
              <!-- <span class ="icon"><ion-icon name="person-outline"></ion-icon></span> -->
              <ion-icon name="person-outline"></ion-icon
              ><input
                type="text"
                id="username"
                class="placeholder-animate"
                placeholder="Username"
                v-model="user.username"
                required
                autofocus
              />
            </div>
            <div class="form-input-group">
              <!-- <span class ="icon"><ion-icon name="lock-closed-outline"></ion-icon></span> -->
              <ion-icon name="lock-closed-outline"></ion-icon
              ><input
                type="password"
                id="password"
                class="placeholder-animate"
                placeholder="Password"
                v-model="user.password"
                required
              />
            </div>
            <div class="form-input-group">
              <!-- <span class ="icon"><ion-icon name="lock-closed-outline"></ion-icon></span> -->
              <ion-icon name="lock-closed-outline"></ion-icon
              ><input
                type="password"
                id="confirmPassword"
                class="placeholder-animate"
                placeholder="Confirm Password"
                v-model="user.confirmPassword"
                required
              />
            </div>
            <button type="submit">Create Account</button>
            <p>
              <router-link :to="{ name: 'login' }"
                ><button>Already have an account? Sign in.</button></router-link
              >
            </p>
          </form>
        </div>
      </div>
    </div>
    <volunteer-application
      v-if="showVolunteerApplication"
      v-bind:username="user.username"
    />
  </body>
</template>

<script>
import authService from "../services/AuthService";
// import VolunteerApplication from "../components/VolunteerApplication.vue";

export default {
  name: "volunteerRegister",
  components: {
    // VolunteerApplication,
  },
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "PENDINGVOLUNTEER",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
      showVolunteerApplication: false,
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.showVolunteerApplication = true;
              this.$router.push({
                name: 'volunteer-application',
                query: { registration: 'success' },
                params: { username: this.user.username }
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
h1 {
  border: 1px solid black;
  background: #62a18f;
  color: white;
  padding: 20px;
  border-radius: 50%
  
}
body {
  background-image: url("../assets/background2.png");
  background-color: rgb(230, 222, 240);
  background-repeat: repeat;
  background-size: contain;
  min-height: 100%;
  min-width: 100%;
  margin: 0;
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
  padding: .5rem;
  border: 2px solid #ccc;
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
button:hover {
  background: #fff;
  color: #fff;
}
.register-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
</style>
