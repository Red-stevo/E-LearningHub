import {Navbar, NavbarBrand, NavLink} from "react-bootstrap";

const NavBar = () => {
    return(<>
        <Navbar expand={"md"}>
            <NavbarBrand></NavbarBrand>
            <NavLink>Login</NavLink>
            <NavLink>Create Account</NavLink>
        </Navbar>
    </>);
}

export default NavBar;