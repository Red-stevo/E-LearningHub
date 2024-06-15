import {ProgressBar} from "react-bootstrap";
import './../Styles/RegistrationPage.css'
import {useContext} from "react";
import {ProgressBarUpdates} from "../DataStore/ProgressBarUpdates.js";
const Progress = () => {
    const {regBar, verBar} = useContext(ProgressBarUpdates);


    return(
        <div className={"bar"}>
            <ProgressBar className={'bg-dark'} >
                <ProgressBar
                    striped={false} now={regBar} className={'register '} variant={'danger'}
                    label={"REGISTER"}/>
                <ProgressBar
                    striped={true} now={verBar} className={'verify-email'} variant={'success'}
                    label={"VERIFY EMAIL"}/>
            </ProgressBar>
        </div>
    );
}

export default Progress;