import {Nav, Navbar} from "react-bootstrap";


export const MainPage = () => {

    return (
        <div>
            <Navbar expand="lg">
                    <Navbar.Toggle aria-controls="navbarScroll"/>
                    <Navbar.Collapse id="navbarScroll" >
                        <Nav navbarScroll>
                            <Nav.Link href={"/student-assistant/main"}>Home</Nav.Link>
                            <Nav.Link href={"/student-assistant/main"}>Create Collection</Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
            </Navbar>
        </div>);
}