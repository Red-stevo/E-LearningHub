import Progress from "../Component/Progress.jsx";
import './../Styles/RegistrationPage.css'
import {Outlet} from "react-router";
import {ProgressBarUpdates} from "../DataStore/ProgressBarUpdates.js";
import {useState} from "react";


export const RegistrationPage = () => {
   const [bar, setBar] = useState({regBar:0, verBar:0});

    return(
        <ProgressBarUpdates.Provider value={{bar, setBar}}>
            <div className={"page1"}>
                <Progress />
            </div>
                <Outlet />
        </ProgressBarUpdates.Provider>
    );
}

