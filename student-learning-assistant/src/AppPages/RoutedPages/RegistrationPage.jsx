import {Container} from "react-bootstrap";
import {useState} from "react";
import Progress from "../Component/Progress.jsx";


export const RegistrationPage = () => {
    const [register, setRegister] = useState(40);
    const [verify, setVerify] = useState(0);
    const [profile, setProfile] = useState(0);
    const [registrationPage, setRegistrationPage] = useState(false);

    return(
        <Container fluid={"xxl"}>
            <Progress register={register} verify={verify} profile={profile}/>

            <div>
                {registrationPage && <></>}
            </div>
        </Container>
    );
}