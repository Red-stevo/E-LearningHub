import axios from "axios";

const guestAPIs = axios.create({
    baseURL:"http://localhost:8080/api/v1/auth"
});

/*This api connection allows us to send the register request to the backend*/
export async function registerUser(userData){
    return await (guestAPIs.post("/register",userData));
}

export async function userNameCheck(username){
    return await (guestAPIs.get(`/check/username?username=${username}`))
}