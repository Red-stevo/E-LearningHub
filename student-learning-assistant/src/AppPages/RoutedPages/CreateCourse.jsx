import {Alert, Button, Form, FormLabel} from "react-bootstrap";
import './../Styles/CreateCourse.css'
import {useEffect, useState} from "react";
import {createCollection} from "../DataSource/BackEndConnection.js";
import {useNavigate} from "react-router";
export const CreateCourse = () => {
    const [courseName, setCourseName] = useState("");
    const [descriptionFile, setDescriptionFile] = useState(false);
    const userId = sessionStorage.getItem("id");
    const navigate = useNavigate();
    const [error, setError] = useState("");
    const [alertClassNames, setAlertClassName] = useState("");

    const handleCreateCourse = (e) => {
        e.preventDefault();

        if(courseName === ''){
            return;
        }
        //call the function  to send the request to the backend.
        createCollection(userId, courseName, descriptionFile).then((res) => {
           if(res.status === 201){
               navigate("/student-assistant/learn/main");
           }
        }).catch(error => {
            setError(error.response.data.message);
            console.log(error);

            setTimeout(() => {
                setError("");
            }, 5000)
        })
    }

    useEffect(() => {
        if(error){
            setAlertClassName("error-alert text-style");
        }else {
            setAlertClassName("");
        }

    }, [error]);


    return(<div className={"collection-form"}>

        <Form className={"create-collection"}>
            {error && <Alert className={`${alertClassNames}`}>{error}</Alert>}

            <FormLabel className={"form-title"}>Create Topic Collection</FormLabel>

            <Form.Group className={"collection-name"}>
                <Form.Label htmlFor={"course"}>Course Name </Form.Label>
                <Form.Control id={"course"} type={"text"}
                              onChange={(e) => setCourseName(e.target.value)}/>
            </Form.Group>

            <Form.Group>
                <input id={"description-file"} type={"checkbox"}
                       onClick={() => setDescriptionFile(!descriptionFile)}/>
                <Form.Label htmlFor={"description-file"} className={"description-label"}>Add Description File
                </Form.Label>
            </Form.Group>

            <div className={"create-button"}><Button onClick={handleCreateCourse}>create</Button></div>
        </Form>
    </div>)
}
