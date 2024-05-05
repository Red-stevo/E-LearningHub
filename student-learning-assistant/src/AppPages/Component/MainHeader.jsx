import {Nav, Navbar} from "react-bootstrap";
import './../Styles/mainPage.css'
import {Outlet} from "react-router";
import "./../Styles/mainPage.css"
import {ProfileImage} from "./ProfileImage.jsx";
import {useEffect, useState} from "react";

export const MainHeader = () => {
    const [mainHeader, setMainHeader] = useState([]);
    const [counter, setCounter] = useState(0);
    const header = ['L', 'e', 'a', 'r', 'n', 'i', 'n', 'g', ' ', 'A', 's', 's', 'i', 's', 't', 'a', 'n', 't'];

    useEffect(() => {

        setInterval(() => {
            setCounter(counter + 1);
            console.log(counter);
        }, 2000)
    }, []);


    return (<>
        <div className={"main-header"}>

            <div className={"main-title"}>{mainHeader}</div>

            <div className={"nav-profile"}>

            <div className={"nav-holder"}>
                <Navbar expand="sm">
                    <Navbar.Toggle aria-controls="navbarScroll"/>
                    <Navbar.Collapse id="navbarScroll">
                        <Nav navbarScroll className={"main-navbar"}>
                            <Nav.Link href={"/student-assistant/learn/main"}>Home</Nav.Link>
                            <Nav.Link href={"/student-assistant/learn/add/course"}> Course</Nav.Link>
                            <Nav.Link href={"/student-assistant/learn/statistics"}> Statistics</Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                </Navbar>
            </div>

                <ProfileImage />

            </div>
        </div>
        <div>
            <Outlet/>
        </div>
    </>);
}