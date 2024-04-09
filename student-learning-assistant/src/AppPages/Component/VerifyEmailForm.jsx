import {Form, FormLabel} from "react-bootstrap";

const VerifyEmailForm = () =>{
    return(
        <div className={'verify-form'}>
            <Form className={'code-form'}>
                <legend><FormLabel>Verify Your Email</FormLabel></legend>
                <Form.Text className={'text'}>Please check you email for a 6 digit verification code.</Form.Text>

                <Form.Group>
                    <Form.Label>CODE</Form.Label>
                    <Form.Control maxLength={4} type="text"/>
                </Form.Group>
            </Form>
        </div>
    );
}

export default VerifyEmailForm;