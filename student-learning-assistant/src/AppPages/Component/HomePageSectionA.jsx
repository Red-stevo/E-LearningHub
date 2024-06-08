import "./../Styles/HomePageStyles.css"
import mainText from "./../Images/homeTitle.svg"
import {Image} from "react-bootstrap";
import HomeDesign from "./HomeDesign.jsx";

const HomePageSectionA = () => {
    return(<div className={"home-page"}>
            <div className={"main-text"}><Image src={mainText} /></div>
            <div className={"home-design"}><HomeDesign /></div>
        </div>)
}

export default HomePageSectionA;