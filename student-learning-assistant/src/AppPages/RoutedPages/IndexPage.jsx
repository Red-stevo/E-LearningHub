import {useEffect} from "react";
import {refreshToken} from "../DataSource/BackEndConnection.js";
import {useNavigate} from "react-router";

export const IndexPage = () => {

const navigate = useNavigate();

    useEffect(() => {

        setInterval(async () => {

            refreshToken().then(response => {

                sessionStorage.setItem("token", response.data.accessToken);
                console.log("token refreshed.")});
                navigate("/student-assistant/main");

            }, 5000 * 60);

        }, []);

    return(
        <div>
            <h1>Index Page.</h1>
        </div>
    );
}