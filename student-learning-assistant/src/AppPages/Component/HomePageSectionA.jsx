import "./../Styles/HomePageStyles.css"
import mainText from "./../Images/homeTitle.svg"
import {Image} from "react-bootstrap";
import HomeDesign from "./HomeDesign.jsx";
import {useEffect} from "react";
import useScreenSize from "../../CustomeHooks/useScreenSize.js";

const HomePageSectionA = () => {
    const size = useScreenSize();
    const updateHomePage = () => {

    }

    useEffect(() => {
        if(size === 810){
            updateHomePage();
        }
    }, [size]);


    return(<div className={"home-page"}>
        <div className={"main-text"}><Image src={mainText}/></div>
        <div className={"home-design"}><HomeDesign/></div> {/*removed at 810px.*/}
    </div>)
}

export default HomePageSectionA;