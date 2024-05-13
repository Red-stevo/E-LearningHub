import {useState} from "react";
import Progress from "../Component/Progress.jsx";
import './../Styles/RegistrationPage.css'
import {Outlet} from "react-router";


export const RegistrationPage = () => {
    const [register] = useState(50);
    const [verify, setVerify] = useState(0);

    return(
        <>
            <div className={"page1"}>
                <Progress register={register} verify={verify} />

            </div>

            <div>
                <Outlet />
            </div>
        </>
    );
}

