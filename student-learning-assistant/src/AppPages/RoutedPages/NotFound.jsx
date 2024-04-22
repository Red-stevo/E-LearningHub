import {Image} from "react-bootstrap";
import image from './../Images/pageNotFoundImage.png';
import './../Styles/PageNotFoundStyles.css';

export const NotFound = () =>{
    return(
        <div className={'not-found'}>
            <p><h1><b>404 Page Not Found</b></h1>
                <Image src={image} height={300}/>
            </p>
        </div>
    )
}