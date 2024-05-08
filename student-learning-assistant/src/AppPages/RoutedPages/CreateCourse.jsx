import {Button, Form, FormLabel} from "react-bootstrap";
import './../Styles/CreateCourse.css'
export const CreateCourse = () => {
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

            <div className={"create-button"}><Button>create</Button></div>
        </Form>
    </div>)
}