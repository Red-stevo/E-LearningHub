import {Alert, Container, Form, FormLabel} from "react-bootstrap";
import {useEffect, useState} from "react";
import {resendVerificationCode, verifyEmailCode} from "../DataSource/BackEndConnection.js";

const VerifyEmailForm = () =>{
    const [code, setCode] = useState("");
    const [codeError, setCodeError] = useState("");
    const [info, setInfo] = useState("");
    const username = sessionStorage.getItem("username");

    useEffect(() => {

        if(code.length === 6){
            verifyEmailCode(code, username).then(res => {
                setCode("");
                sessionStorage.setItem("token", res.data.jwt);
                sessionStorage.setItem("username", res.data.username);
                sessionStorage.setItem("id", res.data.id);
                setInfo(res.data.message);
            }).catch(err => {
                setCodeError(err.response.data.message);
            });
        }
        setCodeError("");
        setInfo("");
    }, [code]);

    const  handleResend = (e) => {
        e.preventDefault();
        resendVerificationCode(username).then((res) => {
            setInfo(res.data.message);
        }).catch(error => {
            setCodeError(error.response.data.message);
        })
    }

    return(
        <Container>
            <div className={'verify-form'}>
                <Form className={'code-form'}>
                    {codeError && <div className={"error"}>{codeError}</div>}
                    {info && <Alert>{info}</Alert>}
                    <legend><FormLabel>Verify Your Email</FormLabel></legend>
                    <Form.Text className={'text'}>
                        Please check your email for a 6 digit verification code.
                    </Form.Text>

                    <Form.Group className={'code-input'}>
                        <Form.Label className={'label'}>CODE</Form.Label>
                        <div className={'input-control'}>
                            <Form.Control maxLength={6} type="text" value={code}
                            onChange={(e) => {setCode(e.target.value)}}/>
                        </div>
                    </Form.Group>
                    <legend>
                        <button className={'reg-btn resend'} onClick={handleResend}>Resend</button>
                    </legend>
                </Form>
            </div>
        </Container>
    );
}

export default VerifyEmailForm;