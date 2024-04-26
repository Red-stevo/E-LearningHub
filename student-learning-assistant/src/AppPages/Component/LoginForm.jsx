import {Button, Form, FormLabel} from "react-bootstrap";
import './../Styles/LoginPage.css';
import {useState} from "react";
import {handlers} from "@reduxjs/toolkit/src/query/tests/mocks/handlers.js";
import {userLogin} from "../DataSource/BackEndConnection.js";

export const LoginForm = () =>{
    const [username, setUsername] = useState(""); //hold the username for login
    const [password, setPassword] = useState(""); //hold the password for login
    const [loginError, setLoginError] = useState("") //hold any login error that occurs.


    const  handleLogin = (e) => {
        e.preventDefault();

        const loginModel = {
            username:username,
            password:password
        }

        userLogin(loginModel).then(res => {

            //setting session storage.
            sessionStorage.setItem("isLoggedIn", "true");
            sessionStorage.setItem("token", res.data.jwt);
            sessionStorage.setItem("username", res.data.username);
            sessionStorage.setItem("id", res.data.id);

            //cleanup the storage
            setUsername("");
            setPassword("");

        }).catch(error => {
            setLoginError(error.response.data.messages);
        })
    }

    //return the responsive login form to the routed login page.
    return(
        <Form onChange={handleLogin}>
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
