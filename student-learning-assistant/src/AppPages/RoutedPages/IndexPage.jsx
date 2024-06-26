import {useEffect, useState} from "react";
import {refreshToken} from "../DataSource/BackEndConnection.js";
import {useNavigate} from "react-router";
import NavBar from "../Component/NavBar.jsx";
import HomePageSectionA from "../Component/HomePageSectionA.jsx";
import "./../Styles/HomePageStyles.css"

export const IndexPage = () => {

    const navigate = useNavigate();
    const [isFirstMount, setIsFirstMount] = useState(true);

    const tokenRefresh = () => {
        refreshToken()
            .then((response) => {
                sessionStorage.setItem("token", response.data.accessToken);
                navigate("/student-assistant/learn/main");
                console.log("token refreshed.")
            })
            .catch(() => {
                navigate("/");
            })
    }

    useEffect(() => {
        if (!isFirstMount) {
            tokenRefresh();
            setIsFirstMount(false);
        }

        const intervals = setInterval(() => {
            tokenRefresh();
        }, 5000 * 60)


    }, []);


    return (
        <div className={"home"}>
            <NavBar/>
            <HomePageSectionA/>

            {/*background design for screens >= large*/}
            <div className="custom-shape-divider-top">
                <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120"
                     preserveAspectRatio="none">
                    <path
                        d="M321.39,56.44c58-10.79,114.16-30.13,172-41.86,82.39-16.72,168.19-17.73,250.45-.39C823.78,31,
                        906.67,72,985.66,92.83c70.05,18.48,146.53,26.09,214.34,3V0H0V27.35A600.21,600.21,0,0,0,321.39,56.44Z"
                        className="shape-fill"></path>
                </svg>
            </div>

            {/*background design for screens >= 930px*/}
            <div className="custom-shape-divider-top-1718536910">
                <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120"
                     preserveAspectRatio="none">
                    <path
                        d="M321.39,56.44c58-10.79,114.16-30.13,172-41.86,82.39-16.72,168.19-17.73,250.45-.39C823.78,31,
                        906.67,72,985.66,92.83c70.05,18.48,146.53,26.09,214.34,3V0H0V27.35A600.21,600.21,0,0,0,321.39,56.44Z"
                        className="shape-fill"></path>
                </svg>
            </div>
        </div>
    );
}