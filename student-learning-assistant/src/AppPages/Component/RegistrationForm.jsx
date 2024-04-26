import {Form, FormLabel} from "react-bootstrap";
import {useEffect, useState} from "react";
import {emailCheckAvailable, registerUser, userNameCheck} from "../DataSource/BackEndConnection.js";

// eslint-disable-next-line react/prop-types
const RegistrationForm = ({verify, verificationCode, register}) => {
    const [username, setUsername] = useState(''); //used to hold the username enter by the user.
    const [password, setPassword] = useState(''); //used to hold the password enter by the user.
    const [confirmPassword, setConfirmPassword] = useState(''); //user to hadle user input for confirm password.
    const [email, setEmail] = useState(''); //hold user input for the email field.
    const [usernameCheck, setUsernameCheck] = useState(false); //Helps in checking whether the username is valid.
    const [emailCheck, setEmailCheck] = useState(false); //helps in checking whether the email enter is valid.
    const [regError, setRegError] = useState(""); //help log errors from the backend
    const [passwordCheck , setPasswordCheck] = useState(""); //help log password miss match errors.


    /*
    * This method is called when the user clicks on register
    * It checks if there are any unresolved errors and inhibit request from being made until all error are resolved.
    * It fills in the user registration request object that is passed to the backend
    * Makes the backend request
    * If the request is successful the jwt and username a saved to the session storage and clean up the input fields.
    * If an error occurs the error is set to error state that and displayed to the user.*/
    const handleSubmit = (e) => {
        e.preventDefault();

        if(passwordCheck || regError || !emailCheck || !usernameCheck){
            return;
        }

        const userData = {
            username:username,
            password:password,
            email:email
        }

        registerUser(userData).then((res) => {
            verify(50);
            register(false);
            verificationCode(true);

            console.log(res.data)
            console.log(res.data.message)
            console.log(res)
            //setting session storage.
            sessionStorage.setItem("isLoggedIn", "true");
            sessionStorage.setItem("username", username);

            //cleaning up the form.
            setEmail('');
            setUsername('');
            setPassword('');
            setConfirmPassword('');

        }).catch((error) => {
            setRegError(error.response.data.message);
        });
    }

    //sends request to the backend to check whether the username is valid.
    useEffect(() => {
        userNameCheck(username).then(res => setUsernameCheck(res.data));

    }, [username]);

    //sends requests to the backend to check whether the email is valid.
    useEffect(() => {
        emailCheckAvailable(email).then(res => setEmailCheck(res.data)).catch((error) => {
            setRegError(error.response.data.message);
        });
    }, [email]);


    //check whether the passwords enter by the user match.
    useEffect(() => {
        if(password !== confirmPassword){
            setPasswordCheck("The passwords do not match!!");
        }else{
            setPasswordCheck('');
        }
    }, [confirmPassword, password])

    //cleans up the backend errors when the user starts to solve them.
    useEffect(() => {
        setRegError("");
    }, [username, email, password, confirmPassword]);

    /*jsx to return the user registration form.*/
    return(
        <div className={'reg-form'} >
            <Form className={"input-form"}>
                <legend className={'reg'}><FormLabel>REGISTER</FormLabel></legend>
                {regError && <div className={"error"}>{regError}</div>}
                <Form.Group className={'username'}>
                    <Form.Label htmlFor={'username'}>USERNAME</Form.Label>
                    <Form.Control type={'text'} placeholder={'mike-meta'} id={'username'}
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    maxLength={50} required={true}/>
                    {usernameCheck? <Form.Text style={{color:"lime"}}>username accepted.</Form.Text>:
                        <Form.Text style={{color:"red"}} className={"invalid"}>username already used or invalid.</Form.Text>}
                </Form.Group>

                <Form.Group className={'email'}>
                    <Form.Label htmlFor={'email'}>EMAIL</Form.Label>
                    <Form.Control type={'email'} placeholder={'example@gmail.com'} id={'email'}
                                  value={email} onChange={(e) => setEmail(e.target.value)}
                                  maxLength={50} required={true}/>
                    {emailCheck? <Form.Text style={{color:"lime"}}>email accepted.</Form.Text>:
                        <Form.Text style={{color:"red"}} className={"invalid"}>email already used or invalid.</Form.Text>}
                </Form.Group>

                <Form.Group className={'password'}>
                    <Form.Label htmlFor={'password'}>PASSWORD</Form.Label>
                    <Form.Control type={'password'} placeholder={'create a password'} id={'password'}
                    value={password} onChange={(e) => setPassword(e.target.value)}
                    maxLength={20} required={true}/>
                </Form.Group>

                <Form.Group className={'confirm'}>
                    <Form.Label htmlFor={'confirm'}>CONFIRM PASSWORD</Form.Label>
                    <Form.Control type={'password'} placeholder={'re-enter the password'} id={'confirm'}
                    value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)}
                    maxLength={50} required={true}/>
                    {passwordCheck && <Form.Text style={{color:"red"}} className={"invalid"}>{passwordCheck}</Form.Text>}
                </Form.Group>

                <button className={'reg-btn'} onClick={handleSubmit}>Register</button>
            </Form>
        </div>
    );
}

export default RegistrationForm;