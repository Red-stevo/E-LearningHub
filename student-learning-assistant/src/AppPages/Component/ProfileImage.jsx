import image from "../Images/logo.svg";


export const ProfileImage = () => {
    return(<>
        <div className={"profile-component"}>
            <div className={"profile-image"}>
                {/*<img src={image} height={"90%"} width={"30%"} alt={"user profile image"}/>*/}
            </div>
            <span className={"profile-title"}>Profile</span>
        </div>
    </>)
}