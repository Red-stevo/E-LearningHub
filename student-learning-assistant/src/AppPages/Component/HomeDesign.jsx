import {Col, Image, Row} from "react-bootstrap";
import image1 from "./../Images/learn7.jpeg"
import image2 from "./../Images/learn9.jpeg"
import image3 from "./../Images/learn4.jpeg"
import image4 from "./../Images/learn2.jpeg"
import image5 from "./../Images/learn3.jpeg"
import image6 from "./../Images/bookstore.jpg"
import image7 from "./../Images/perfect.jpg"
import image8 from "./../Images/theone.jpg"
const HomeDesign = () => {
    return(
        <>
            <Row>
                <Col><Image src={image1} height={180} width={180} /></Col>
                <Col><Image src={image2} height={180} width={180} /></Col>
                <Col><Image src={image3} height={180} width={180} /></Col>
                <Col><Image src={image4} height={180} width={180} /></Col>
                <Col><Image src={image5} height={180} width={180} /></Col>
                <Col><Image src={image6} height={180} width={180} /></Col>
                <Col><Image src={image7} height={180} width={180} /></Col>
                <Col><Image src={image8} height={180} width={180} /></Col>
            </Row>
        </>);
}

export default HomeDesign;