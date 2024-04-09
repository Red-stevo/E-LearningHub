import {Form, FormLabel} from "react-bootstrap";
import './../Styles/RegistrationPage.css'
const RegistrationForm = () => {
    return(
        <div className={'reg-form'}>
            <Form>
                <legend><FormLabel>REGISTER</FormLabel></legend>
                <Form.Group className={'username'}>
                    <Form.Label>USERNAME</Form.Label>
                    <Form.Control type={'text'} placeholder={'mike-meta'}/>
                </Form.Group>
                <Form.Group className={'password'}>
                    <Form.Label>PASSWORD</Form.Label>
                    <Form.Control type={'text'} placeholder={'create a password'}/>
                </Form.Group>
                <Form.Group className={'confirm'}>
                    <Form.Label>CONFIRM PASSWORD</Form.Label>
                    <Form.Control type={'text'} placeholder={'re-enter the password'}/>
                </Form.Group>
                <Form.Group className={'email'}>
                    <Form.Label>EMAIL</Form.Label>
                    <Form.Control type={'text'} placeholder={'example@gmail.com'}/>
                </Form.Group>

                <button className={'reg-btn'} >Register</button>
            </Form>
        </div>
    );
}

export default RegistrationForm;