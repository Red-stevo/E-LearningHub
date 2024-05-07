import {Form, FormLabel} from "react-bootstrap";
import './../Styles/CreateCourse.css'
export const CreateCourse = () => {
    return(<div>
        <Form>
            <FormLabel className={"form-title"}>Create Topic Collection</FormLabel>

            <Form.Group>
                <Form.Label htmlFor={"course"}>Course Name </Form.Label>
                <Form.Control id={"course"} type={"text"}/>
            </Form.Group>

            <Form.Group>
                <Form.Label htmlFor={"description-file"}>Description File</Form.Label>
                <Form.Control id={"description-file"} type={"checked"} />
            </Form.Group>
        </Form>
    </div>)
}