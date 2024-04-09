import {Form, FormLabel} from "react-bootstrap";

const VerifyEmailForm = () =>{
    return(
        <div className={'reg-form'}>
            <Form>
                <legend><FormLabel>Verify Your Email</FormLabel></legend>

                <Form.Text>Please check you email for a 6 digit verification code.</Form.Text>
            </Form>
        </div>
    );
}

export default VerifyEmailForm;