import {Image, Nav, Navbar, NavbarBrand, NavLink} from "react-bootstrap";
import logo from "../Images/logo.jpg"

const NavBar = () => {
    return(<>
        <Navbar expand={"md"} className={"home-nav"}>
            <NavbarBrand><Image src={logo}  height={90} width={130} id={"logo"}/></NavbarBrand>
            <Nav>
                <NavLink>Login</NavLink>
                <NavLink>Create Account</NavLink>
            </Nav>
        </Navbar>
    </>);
}

export default NavBar;