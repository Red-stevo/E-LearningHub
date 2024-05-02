import {Button, Form, FormLabel} from "react-bootstrap";
import './../Styles/LoginPage.css';
import {useEffect, useState} from "react";
import {userLogin} from "../DataSource/BackEndConnection.js";
import {useNavigate} from "react-router";

export const LoginForm = () =>{
    const [username, setUsername] = useState(""); //hold the username for login
    const [password, setPassword] = useState(""); //hold the password for login
    const [loginError, setLoginError] = useState("") //hold any login error that occurs.
    const navigate = useNavigate();

    const  handleLogin = (e) => {
        e.preventDefault();

        const loginModel = {
            username:username,
            password:password
        }

        userLogin(loginModel).then(res => {

            //setting session storage.
            sessionStorage.setItem("isLoggedIn", [true]);
            sessionStorage.setItem("token", res.data.jwt);
            sessionStorage.setItem("username", res.data.username);
            sessionStorage.setItem("id", res.data.id);

            //cleanup the storage
            setUsername("");
            setPassword("");

            //navigate to the main page.
            navigate("/student-assistant/main")

        }).catch(error => {
            setLoginError(error.response.data.message);
        })
    }


    useEffect(() => {
        setLoginError("");
    }, [username, password]);

    //return the responsive login form to the routed login page.
    return(
        <Form onSubmit={handleLogin}>
            <FormLabel>LOGIN</FormLabel>
            {loginError && <div className={"error"}>{loginError}</div>}
            <Form.Group className={"login-username"}>
                <Form.Label htmlFor={"login-username"}>USERNAME </Form.Label>
                <Form.Control id={"login-username"} type={"text"} required={true}
                value={username} onChange={(e) => setUsername(e.target.value)}/>
            </Form.Group>

            <Form.Group className={"login-password"}>
                <Form.Label htmlFor={"login-password"}>PASSWORD</Form.Label>
                <Form.Control id={"login-password"} type={"password"} required={true}
                value={password} onChange={(e) => setPassword(e.target.value)}/>
            </Form.Group>

            <Button type={"submit"} className={'reg-btn'}>Login</Button>

        </Form>);
}
