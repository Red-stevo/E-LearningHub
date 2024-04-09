import {Form, FormLabel} from "react-bootstrap";
import {useState} from "react";
import register from "../DataSource/BackEndConnection.js";
import {useDispatch} from "react-redux";
import {setRegFormState, setVerifyBar, setVerifyFormState} from "../DataStore/RegistrationPageData.js";

const RegistrationForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [email, setEmail] = useState('');
    const dispatch = useDispatch();

    const handleSubmit = (e) => {
        e.defaultPrevented();

        const userData = {
            username:username,
            password:password,
            email:email
        }


        //register(userData)

        //increase the bar
        dispatch(setVerifyBar(50));

        //change the component to the next
        dispatch(setRegFormState(false));
        dispatch(setVerifyFormState(true));

    }



    return(
        <div className={'reg-form'}>
            <Form onSubmit={handleSubmit}>
                <legend className={'reg'}><FormLabel>REGISTER</FormLabel></legend>

                <Form.Group className={'username'}>
                    <Form.Label htmlFor={'username'}>USERNAME</Form.Label>
                    <Form.Control type={'text'} placeholder={'mike-meta'} id={'username'}
                    value={username} onChange={(e) => setUsername(e.target.value)}
                    maxLength={50}/>
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

                <Form.Group className={'email'}>
                    <Form.Label htmlFor={'email'}>EMAIL</Form.Label>
                    <Form.Control type={'email'} placeholder={'example@gmail.com'} id={'email'}
                    value={email} onChange={(e) => setEmail(e.target.value)}
                    maxLength={50}/>
                </Form.Group>
                <button className={'reg-btn'} type={"submit"}>Register</button>
            </Form>
        </div>
    );
}

export default RegistrationForm;