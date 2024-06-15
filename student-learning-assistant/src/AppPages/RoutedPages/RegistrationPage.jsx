import Progress from "../Component/Progress.jsx";
import './../Styles/RegistrationPage.css'
import {Outlet} from "react-router";


export const RegistrationPage = () => {
    return(
        <>
            <div className={"page1"}>
                <Progress/>
            </div>
                <Outlet />
        </>
    );
}

