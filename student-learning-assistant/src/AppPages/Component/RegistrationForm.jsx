import {Form, FormLabel} from "react-bootstrap";
import {useEffect, useState} from "react";
import {emailCheckAvailable, registerUser, userNameCheck} from "../DataSource/BackEndConnection.js";

// eslint-disable-next-line react/prop-types
const RegistrationForm = ({verify, verificationCode, register}) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [email, setEmail] = useState('');
    const [usernameCheck, setUsernameCheck] = useState(true);
    const [emailCheck, setEmailCheck] = useState(false);
    const [regError, setRegError] = useState("");

    const handleSubmit = () => {

        console.log("hey there");

        const userData = {
            username:username,
            password:password,
            email:email
        }

        console.log(userData);

        registerUser(userData).then((res) => {
            verify(50);
            register(false);
            verificationCode(true);

            //setting session storage.
            sessionStorage.setItem("isLoggedIn", "true");
            sessionStorage.setItem("username", res.data.username);
            sessionStorage.setItem("token", res.data.jwt)

        }).catch((error) => {
            console.log(error.response.message());
        });
    }

    useEffect(() => {
        userNameCheck(username).then(res => setUsernameCheck(res.data));

    }, [username]);

    useEffect(() => {
        emailCheckAvailable(email).then(res => setEmailCheck(res.data))
    }, [email]);

    return(
        <div className={'reg-form'}>
            <Form>
                <legend className={'reg'}><FormLabel>REGISTER</FormLabel></legend>

                <Form.Group className={'username'}>
                    <Form.Label htmlFor={'username'}>USERNAME</Form.Label>
                    <Form.Control type={'text'} placeholder={'mike-meta'} id={'username'}
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    maxLength={50}/>
                    {usernameCheck? <Form.Text style={{color:"lime"}}>username accepted.</Form.Text>:
                        <Form.Text style={{color:"red"}} className={"invalid"}>username already used.</Form.Text>}
                </Form.Group>

                <Form.Group className={'email'}>
                    <Form.Label htmlFor={'email'}>EMAIL</Form.Label>
                    <Form.Control type={'email'} placeholder={'example@gmail.com'} id={'email'}
                                  value={email} onChange={(e) => setEmail(e.target.value)}
                                  maxLength={50}/>
                    {emailCheck? <Form.Text style={{color:"lime"}}>email accepted.</Form.Text>:
                        <Form.Text style={{color:"red"}} className={"invalid"}>email already used.</Form.Text>}
                </Form.Group>

                <Form.Group className={'password'}>
                    <Form.Label htmlFor={'password'}>PASSWORD</Form.Label>
                    <Form.Control type={'text'} placeholder={'create a password'} id={'password'}
                    value={password} onChange={(e) => setPassword(e.target.value)}
                    maxLength={20}/>
                </Form.Group>

                <Form.Group className={'confirm'}>
                    <Form.Label htmlFor={'confirm'}>CONFIRM PASSWORD</Form.Label>
                    <Form.Control type={'text'} placeholder={'re-enter the password'} id={'confirm'}
                    value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)}
                    maxLength={50}/>
                </Form.Group>

                <button className={'reg-btn'} type={"submit"}  onClick={handleSubmit}>Register</button>
            </Form>
        </div>
    );
}

export default RegistrationForm;