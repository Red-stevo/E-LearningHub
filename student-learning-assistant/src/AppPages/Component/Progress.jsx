import {ProgressBar} from "react-bootstrap";
import './../Styles/RegistrationPage.css'
// eslint-disable-next-line react/prop-types
const Progress = ( { register, verify} ) => {
    return(
        <div className={"bar"}>
            <ProgressBar className={'bg-dark'} >
                <ProgressBar
                    striped={false} now={register} className={'register '} variant={'danger'}
                    label={"REGISTER"}/>
                <ProgressBar
                    striped={true} now={verify} className={'verify-email'} variant={'success'}
                    label={"VERIFY EMAIL"}/>
            </ProgressBar>
        </div>
    );
}

export default Progress;