import {Button, Form, FormLabel} from "react-bootstrap";
import './../Styles/CreateCourse.css'
import {useState} from "react";
import {createCollection} from "../DataSource/BackEndConnection.js";
import {useNavigate} from "react-router";
export const CreateCourse = () => {
    const [courseName, setCourseName] = useState("");
    const [descriptionFile, setDescriptionFile] = useState(false);
    const userId = sessionStorage.getItem("id");
    const navigate = useNavigate();
    const handleCreateCourse = (e) => {
        e.preventDefault();

        //call the function  to send the request to the backend.
        createCollection(userId, courseName, descriptionFile).then((res) => {
           if(res.status === 201){
               navigate("/student-assistant/learn/main");
           }
        });
    }


    return(<div className={"collection-form"}>
        <Form className={"create-collection"}>
            <FormLabel className={"form-title"}>Create Topic Collection</FormLabel>

            <Form.Group className={"collection-name"}>
                <Form.Label htmlFor={"course"}>Course Name </Form.Label>
                <Form.Control id={"course"} type={"text"}/>
            </Form.Group>

            <Form.Group>
                <input id={"description-file"} type={"checkbox"} />
                <Form.Label htmlFor={"description-file"} className={"description-label"}>Add Description File
                </Form.Label>
            </Form.Group>

            <div className={"create-button"}><Button onClick={() => handleCreateCourse(e)}>create</Button></div>
        </Form>
    </div>)
}