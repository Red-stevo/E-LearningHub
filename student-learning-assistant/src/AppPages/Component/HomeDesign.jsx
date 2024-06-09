import {Image} from "react-bootstrap";
import image1 from "./../Images/learn7.jpeg";
import image2 from "./../Images/learn9.jpeg";
import image3 from "./../Images/learn4.jpeg";
import image4 from "./../Images/learn2.jpeg";
import image5 from "./../Images/learn3.jpeg";
import image6 from "./../Images/bookstore.jpg";
import image7 from "./../Images/perfect.jpg";
import image8 from "./../Images/theone.jpg";
import image9 from "./../Images/bridge.jpg";
import "./../Styles/HomePageStyles.css"
const HomeDesign = () => {
    return (
        <div className={"image-design"}>
            <div>
                <Image id={"home-image1"} src={image1} height={130} width={180}/>
                <Image id={"home-image2"} src={image2} height={130} width={180}/>
                <Image id={"home-image3"} src={image3} height={130} width={180}/>
            </div>
            <div>
                <Image id={"home-image4"} src={image4} height={130} width={180}/>
                <Image id={"home-image5"} src={image5} height={130} width={180}/>
                <Image id={"home-image6"} src={image6} height={130} width={180}/>
            </div>
            <div>
                <Image id={"home-image7"} src={image7} height={130} width={180}/>
                <Image id={"home-image8"} src={image8} height={130} width={180}/>
                <Image id={"home-image9"} src={image9} height={130} width={180}/>
            </div>
        </div>);
}

export default HomeDesign;