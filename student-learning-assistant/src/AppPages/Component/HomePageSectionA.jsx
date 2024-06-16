import "./../Styles/HomePageStyles.css"
import mainText from "./../Images/homeTitle.svg"
import {Image} from "react-bootstrap";
import HomeDesign from "./HomeDesign.jsx";
import {useEffect} from "react";


const HomePageSectionA = () => {

    useEffect(() => {
        updateHomePage();
        window.addEventListener("resize", updateHomePage);
    }, []);

    const updateHomePage = () => {
       if(window.innerWidth === 810){
           const design = document.getElementsByClassName("home-design");
       }
    }

    return(<div className={"home-page"}>
        <div className={"main-text"}><Image src={mainText}/></div>
        <div className={"home-design"}><HomeDesign/></div> {/*removed at 810px.*/}
    </div>)
}

export default HomePageSectionA;