import {Button} from "react-bootstrap";
import {test} from "../DataSource/BackEndConnection.js";

export const MainPage = () => {

    const handleClick = (e) => {
        e.preventDefault();
        console.log("onclick")
        test().then((res) => {
            console.log(res.data);
        }).catch((error => {
            console.error(error);
        }))
    }


    return(
        <div className={"justify-content-center align-content-center"}>
            <Button onClick={handleClick}>RefreshTheToken</Button>
        </div>);
}