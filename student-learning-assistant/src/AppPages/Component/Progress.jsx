import {ProgressBar} from "react-bootstrap";
import './../Styles/RegistrationPage.css'
// eslint-disable-next-line react/prop-types
const Progress = ( { register, verify, profile } ) => {
    return(
        <div className={"bar"}>
            <ProgressBar className={'bg-dark'} >
                <ProgressBar
                    striped={false} now={register} className={'register '} variant={'danger'}
                    label={"REGISTER"}/>
                <ProgressBar
                    striped={false} now={verify} className={'verify-email'} variant={'success'}
                    label={"VERIFY EMAIL"}/>
                <ProgressBar
                    striped={false} now={profile} className={'profile'} variant={'info'}
                    label={"(OPTIONAL)USER PROFILE"}/>
            </ProgressBar>
        </div>
    );
}

export default Progress;