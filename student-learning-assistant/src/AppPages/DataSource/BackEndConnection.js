import axios from "axios";

const guestAPIs = axios.create({
    baseURL:"/api/v1/auth"
});

/*This api connection allows us to send the register request to the backend*/
export default async function register(userData){
    return await (guestAPIs.post("/register",userData));
}

