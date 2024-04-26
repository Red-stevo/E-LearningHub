import {Row} from "react-bootstrap";
import {LoginForm} from "../Component/LoginForm.jsx";
import './../Styles/RegistrationPage.css';
import './../Styles/LoginPage.css';


export const LoginPage = () => {
    return(
        <Row className={"login-page"}>
            <LoginForm />
        </Row>
    )
}