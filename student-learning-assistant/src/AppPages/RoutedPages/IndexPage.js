import {useEffect} from "react";
import {refreshToken} from "../DataSource/BackEndConnection.js";

export const IndexPage = () => {

    useEffect(() => {
        refreshToken().then(r => {
            console.log("token refreshed.")});
    }, []);




    return(
        <div>
            <h1>Index Page.</h1>
        </div>
    )
}