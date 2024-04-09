import {ProgressBar} from "react-bootstrap";
import './../Styles/RegistrationPage.css'
// eslint-disable-next-line react/prop-types
const Progress = ( { register, verify, profile } ) => {
    return(
        <div className={"bar"}>
            <ProgressBar className={'bg-dark'} >
                <ProgressBar
                    striped={false} now={register} className={'register'} variant={'danger'}
                    label={"register"}/>
                <ProgressBar
                    striped={false} now={verify} className={'verify-email'} variant={'success'}
                    label={"verify email"}/>
                <ProgressBar
                    striped={false} now={profile} className={'profile'} variant={'info'}
                    label={"(Optional)user profile"}/>
            </ProgressBar>
        </div>
    );
}

export default Progress;