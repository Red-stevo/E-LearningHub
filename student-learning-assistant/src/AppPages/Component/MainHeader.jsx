import {Nav, Navbar} from "react-bootstrap";
import './../Styles/mainPage.css'
import {Outlet} from "react-router";
import "./../Styles/mainPage.css"
import image from './../Images/logo.svg'
import {ProfileImage} from "./ProfileImage.jsx";
export const MainHeader = () => {
    return (<>
        <div className={"main-header"}>

            <div className={"main-title"}>Learning Assistant</div>

            <div className={"nav-holder"}>
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
            </div>
            <ProfileImage />
        </div>
        <div>
            <Outlet/>
        </div>
    </>);
}