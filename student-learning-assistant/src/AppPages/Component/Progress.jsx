import {ProgressBar} from "react-bootstrap";

// eslint-disable-next-line react/prop-types
export const Progress = ( { register, verify, profile } ) => {
    return(
        <ProgressBar className={"progress"}>
            <ProgressBar striped={true} now={register} className={'register'}/>
            <ProgressBar striped={true} now={verify} className={'verify-email'}/>
            <ProgressBar striped={true} now={profile} className={'profile'}/>
        </ProgressBar>
    )
}