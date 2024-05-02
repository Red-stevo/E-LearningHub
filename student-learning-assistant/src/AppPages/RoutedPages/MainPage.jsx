import {Nav, Navbar} from "react-bootstrap";


export const MainPage = () => {

    return (
        <div>
            <Navbar expand="lg">
                    <Navbar.Toggle aria-controls="navbarScroll"/>
                    <Navbar.Collapse id="navbarScroll" >
                        <Nav navbarScroll>
                            <Nav.Link >Home</Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
            </Navbar>
        </div>);
}