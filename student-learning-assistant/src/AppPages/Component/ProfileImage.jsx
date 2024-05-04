import image from "../Images/logo.svg";


export const ProfileImage = () => {
    return(<>
        <div className={"profile-component"}>
            <div className={"profile-image"}>
                <img src={image} height={"50%"} width={"50%"}/>
            </div>
            <span className={"profile-title"}>Profile</span>
        </div>
    </>)
}