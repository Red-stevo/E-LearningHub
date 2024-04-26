import {Button, Form, FormLabel} from "react-bootstrap";
import './../Styles/LoginPage.css';

export const LoginForm = () =>{
    return(
        <Form>
            <FormLabel>LOGIN</FormLabel>
            <Form.Group className={"login-username"}>
                <Form.Label htmlFor={"login-username"}>USERNAME </Form.Label>
                <Form.Control id={"login-username"} type={"text"} required={true}/>
            </Form.Group>

            <Form.Group className={"login-password"}>
                <Form.Label htmlFor={"login-password"}>PASSWORD</Form.Label>
                <Form.Control id={"login-password"} type={"password"} required={true}/>
            </Form.Group>

            <Button type={"submit"} className={'reg-btn'}>Login</Button>

        </Form>);
}
