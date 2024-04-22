import {Image} from "react-bootstrap";
import image from './../Images/pageNotFoundImage.png';

export const NotFound = () =>{
    return(
        <div className={'404'}>
            <h1><b>404Page Not Found</b></h1>
                <Image src={image} height={300}/>
        </div>
    )
}