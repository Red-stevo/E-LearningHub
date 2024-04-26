import {LoginForm} from "../Component/LoginForm.jsx";
import './../Styles/RegistrationPage.css';
import './../Styles/LoginPage.css';


export const LoginPage = () => {
    return(
        <div className={"login-page"}>
            <div className={"login-form"}>
                <LoginForm />
            </div>
        </div>
    );
}