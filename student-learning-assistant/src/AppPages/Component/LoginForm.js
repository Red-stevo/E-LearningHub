import {Form} from "react-bootstrap";

export const LoginForm = () =>{
    return(
        <Form>
            <Form.Group>
                <Form.Label htmlFor={"login-username"}>USERNAME </Form.Label>
                <Form.Control id={"login-username"} type={"text"} required={true}/>
            </Form.Group>

            <Form.Group>
                <Form.Label htmlFor={"login-password"}>PASSWORD</Form.Label>
                <Form.Control id={"login-password"} type={"password"} required={true}/>
            </Form.Group>
        </Form>);
}
