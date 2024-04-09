import {Container} from "react-bootstrap";
import {useState} from "react";
import Progress from "../Component/Progress.jsx";
import RegistrationForm from "../Component/RegistrationForm.jsx";


export const RegistrationPage = () => {
    const [register, setRegister] = useState(40);
    const [verify, setVerify] = useState(0);
    const [profile, setProfile] = useState(0);
    const [registrationPage, setRegistrationPage] = useState(true);

    return(
        <Container fluid={"xxl"}>
            <Progress register={register} verify={verify} profile={profile}/>

            <div>
                {registrationPage && <RegistrationForm />}
            </div>
        </Container>
    );
}