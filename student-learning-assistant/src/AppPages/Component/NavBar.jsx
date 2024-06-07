import {Image, Nav, Navbar, NavbarBrand, NavLink} from "react-bootstrap";
import logo from "../Images/logo.jpg"
import "./../Styles/HomePageStyles.css"

const NavBar = () => {
    return(<>
    <Navbar expand={"md"} className={"home-nav"}>
        <NavbarBrand id={"logo"}><Image src={logo}  height={80} width={130} /></NavbarBrand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
                <NavLink id={"login"} href={"/student-assistant/login"}>Login</NavLink>
                <NavLink id={"register"} href={"/student-assistant/register/user"}>Create Account</NavLink>
            </Nav>
        </Navbar.Collapse>
    </Navbar>
    </>);
}

export default NavBar;