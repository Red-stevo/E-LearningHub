import {ProgressBar} from "react-bootstrap";
import './../Styles/RegistrationPage.css'
// eslint-disable-next-line react/prop-types
const Progress = ( { register, verify, profile } ) => {
    return(
        <div className={"bar"}>
            <ProgressBar >
                <ProgressBar striped={true} now={register} className={'register'} variant={'danger'}/>
                <ProgressBar striped={true} now={verify} className={'verify-email'} variant={'success'}/>
                <ProgressBar striped={true} now={profile} className={'profile'} variant={'info'}/>
            </ProgressBar>
        </div>
    )
}

export default Progress;