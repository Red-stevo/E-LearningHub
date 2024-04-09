import {Form, FormLabel} from "react-bootstrap";
import './../Styles/RegistrationPage.css'
const RegistrationForm = () => {
    return(
        <div className={'reg-form'}>
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
        </div>
    );
}

export default RegistrationForm;