import {Nav, Navbar} from "react-bootstrap";
import './../Styles/mainPage.css'
import {Outlet} from "react-router";
export const MainHeader = () => {
    return (<>
        <div className={"main-header"}>
            <div className={"nav-title"}>Learning Assistant</div>
            <Navbar expand="sm">
                <Navbar.Toggle aria-controls="navbarScroll"/>
                <Navbar.Collapse id="navbarScroll">
                    <Nav navbarScroll className={"main-navbar"}>
                        <Nav.Link href={"/student-assistant/learn/main"}>Home</Nav.Link>
                        <Nav.Link href={"/student-assistant/learn/add/course"}> Create Course</Nav.Link>
                        <Nav.Link href={"/student-assistant/learn/statistics"}>Learning Statistics</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
            <div className={"mx-5 mx-md-3 mx-sm-3"} href={"/student-assistant/learn/main"}>User Profile</div>
        </div>
        <div>
            <Outlet/>
        </div>
    </>);
}