import {Form, FormLabel, InputGroup} from "react-bootstrap";

const UserProfileForm = () => {
    return(<div>
        <Form>
            <FormLabel>User Profile</FormLabel>
            <InputGroup className="mb-3">
                <InputGroup.Text>First and last name</InputGroup.Text>
                <Form.Control aria-label="First name" />
                <Form.Control aria-label="Last name" />
            </InputGroup>
        </Form>
    </div>);
}

export default UserProfileForm;