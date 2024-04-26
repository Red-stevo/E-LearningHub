import {Button, Form, FormLabel} from "react-bootstrap";
import './../Styles/RegistrationPage.css';
import './../Styles/LoginPage.css';

export const LoginForm = () =>{
    return(
        <Form className={"login-form"}>
            <legend><FormLabel>LOGIN</FormLabel></legend>
            <Form.Group>
                <Form.Label htmlFor={"login-username"}>USERNAME </Form.Label>
                <Form.Control id={"login-username"} type={"text"} required={true}/>
            </Form.Group>

            <Form.Group>
                <Form.Label htmlFor={"login-password"}>PASSWORD</Form.Label>
                <Form.Control id={"login-password"} type={"password"} required={true}/>
            </Form.Group>

            <Button type={"submit"} className={'reg-btn'}>Login</Button>
        </Form>);
}
