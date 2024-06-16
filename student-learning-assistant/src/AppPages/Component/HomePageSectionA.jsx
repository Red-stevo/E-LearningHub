import "./../Styles/HomePageStyles.css"
import mainText from "./../Images/homeTitle.svg"
import {Image} from "react-bootstrap";
import HomeDesign from "./HomeDesign.jsx";
import {useEffect} from "react";


const HomePageSectionA = () => {

    useEffect(() => {
        window.addEventListener("screenResize", updateHomePage);
    }, []);

    const updateHomePage = () => {

    }


    return(<div className={"home-page"}>
        <div className={"main-text"}><Image src={mainText}/></div>
        <div className={"home-design"}><HomeDesign/></div> {/*removed at 810px.*/}
    </div>)
}

export default HomePageSectionA;