import axios from "axios";


const guestAPIs = axios.create({
    baseURL:"http://localhost:8080/api/v1/auth",
    withCredentials:true,
});

/*This api connection allows us to send the register request to the backend*/
export async function registerUser(userData){
    return await (guestAPIs.post("/register",userData));
}


/*End point to check whether the username is already in use.*/
export async function userNameCheck(username){
    return await (guestAPIs.get(`/check/username?username=${username}`));
}


/*Endpoint to check whether the email is already in use.*/
export async function emailCheckAvailable(email){
    return await (guestAPIs.get(`/check/email?email=${email}`));
}


/*End point to verify the email confirmation code sent*/
export async function verifyEmailCode(code, username){
    return await (guestAPIs.put(`/verify/code?code=${code}&username=${username}`));
}


/*End point to resend an email if it fails to go through*/
export async function resendVerificationCode(username){
    return await (guestAPIs.put(`/verify/resend?username=${username}`));
}



/*Login end point*/
export async function userLogin(loginModel){
    return await (guestAPIs.post("/login", loginModel));
}


/*End Point Get a new Access and refresh token*/
export async function refreshToken(){
    return await (guestAPIs.put("/token/refresh"))
}

const access_token=sessionStorage.getItem("jwt");

const securedAPIs = axios.create({
    baseURL:"http://localhost:8080/api/v1/learn",
    withCredentials:true,
    headers:{Authorization:`Bearer ${access_token}`}
});


/*This end point allows us to create a new course collection*/
export async function createCollection(userId, collectionName, createDescriptionFile){
    return await securedAPIs.post(`/new/collection/${userId}`, collectionName, createDescriptionFile);
}
