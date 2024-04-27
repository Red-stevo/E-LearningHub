import axios from "axios";


const guestAPIs = axios.create({
    baseURL:"http://localhost:8080/api/v1/auth",
    withCredentials:true,
});

/*This api connection allows us to send the register request to the backend*/
export async function registerUser(userData){
    return await (guestAPIs.post("/register",userData));
}

export async function userNameCheck(username){
    return await (guestAPIs.get(`/check/username?username=${username}`));
}
export async function emailCheckAvailable(email){
    return await (guestAPIs.get(`/check/email?email=${email}`));
}

export async function verifyEmailCode(code, username){
    return await (guestAPIs.put(`/verify/code?code=${code}&username=${username}`));
}

export async function resendVerificationCode(username){
    return await (guestAPIs.put(`/verify/resend?username=${username}`));
}

export async function userLogin(loginModel){
    return await (guestAPIs.post("/login", loginModel));
}


const access_token=sessionStorage.getItem("jwt");

const securedAPs = axios.create({
    baseURL:"http://localhost:8080/api/v1/learn",
    withCredentials:true,
    headers:{Authorization:`${access_token}`}
});


securedAPs.interceptors.request.use(async request => {
    console.log("interceptor called.")

    await guestAPIs.put("/token/refresh").then(response => {
        sessionStorage.setItem("jwt", response.data.jwt);
    })

    return request;
});

export async function test(){
    return await securedAPs.get("hey");
}