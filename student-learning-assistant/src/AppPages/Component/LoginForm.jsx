import {Button, Form, FormLabel} from "react-bootstrap";
import './../Styles/LoginPage.css';
import {useState} from "react";

export const LoginForm = () =>{
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");




    return(
        <Form>
            <FormLabel>LOGIN</FormLabel>
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
