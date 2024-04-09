import {ProgressBar} from "react-bootstrap";
import {useState} from "react";

export const RegistrationPage = () => {
    const [progress, setProgress] = useState(0);


    return(
        <div>
            <ProgressBar>
                <ProgressBar striped={true} now={progress} />
            </ProgressBar>

        </div>
    );
}