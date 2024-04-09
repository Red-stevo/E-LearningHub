import {Container} from "react-bootstrap";
import {useEffect, useState} from "react";
import Progress from "../Component/Progress.jsx";
import RegistrationForm from "../Component/RegistrationForm.jsx";
import VerifyEmailForm from "../Component/VerifyEmailForm.jsx";
import './../Styles/RegistrationPage.css'
import {useSelector} from "react-redux";


export const RegistrationPage = () => {
    const verifyBar = useSelector(state => state.RegStore.verifyBar);
    const verifyDisplayState = useSelector(state => state.RegStore.verifyFormState);
    const regDisplayState = useSelector(state => state.RegStore.regFormState);
    const [register, setRegister] = useState(50);
    const [verify, setVerify] = useState(0);
    const [registrationPage, setRegistrationPage] = useState(regDisplayState);
    const [verifyEmail, setVerifyEmail] = useState(verifyDisplayState);



    useEffect(() => {
        setVerify(verifyBar);
        setRegistrationPage(regDisplayState);
        setVerifyEmail(verifyDisplayState);

    }, [verifyBar, verifyDisplayState, regDisplayState]);


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

