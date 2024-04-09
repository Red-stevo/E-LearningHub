import {Container} from "react-bootstrap";
import {useState} from "react";
import Progress from "../Component/Progress.jsx";
import RegistrationForm from "../Component/RegistrationForm.jsx";
import VerifyEmailForm from "../Component/VerifyEmailForm.jsx";
import './../Styles/RegistrationPage.css'


export const RegistrationPage = () => {
    const [register, setRegister] = useState(50);
    const [verify, setVerify] = useState(0);
    const [registrationPage, setRegistrationPage] = useState(true);
    const [verifyEmail, setVerifyEmail] = useState(false)

    return(
        <Container fluid={"xl"}>
            <Progress register={register} verify={verify} />
            <hr />
            <div>
                {registrationPage && <RegistrationForm />}
                {verifyEmail && <VerifyEmailForm />}
            </div>
        </Container>
    );
}

