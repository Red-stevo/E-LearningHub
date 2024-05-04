import {Nav, Navbar, NavbarBrand} from "react-bootstrap";
import './../Styles/mainPage.css'

export const MainPage = () => {

    return (
            <Navbar expand="sm" className={"main-header"}>
                <NavbarBrand>Learning Assistant</NavbarBrand>
                <Navbar.Toggle aria-controls="navbarScroll"/>
                <Navbar.Collapse id="navbarScroll" >
                    <Nav navbarScroll className={"main-navbar"}>
                        <Nav.Link href={"/student-assistant/main"}>Home</Nav.Link>
                        <Nav.Link href={"/student-assistant/main"}>Collection</Nav.Link>
                        <Nav.Link href={"/student-assistant/main"}>User Profile</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>);
}