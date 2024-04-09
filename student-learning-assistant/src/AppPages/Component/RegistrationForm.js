import {Form, FormLabel} from "react-bootstrap";

const RegistrationForm = () => {
    return(
        <Form>
            <FormLabel>REGISTER</FormLabel>
            <Form.Group>
                <Form.Label>USERNAME</Form.Label>
                <Form.Control type={'text'} placeholder={'mike-meta'}/>
            </Form.Group>
            <Form.Group>
                <Form.Label>PASSWORD</Form.Label>
                <Form.Control type={'text'} placeholder={'CREATE A PASSWORD'}/>
            </Form.Group>
            <Form.Group>
                <Form.Label>CONFIRM PASSWORD</Form.Label>
                <Form.Control type={'text'} placeholder={'mike-meta'}/>
            </Form.Group>
            <Form.Group>
                <Form.Label>EMAIL</Form.Label>
                <Form.Control type={'text'} placeholder={'mike-meta'}/>
            </Form.Group>
        </Form>
    );
}

export default RegistrationForm;