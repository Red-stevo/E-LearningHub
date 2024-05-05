import image from "../Images/learn2.jpeg";


export const ProfileImage = () => {
    return(<>
        <div className={"profile-image"}>
            <img src={image} height={"96%"} width={"100%"} alt={"user profile image"} className={"image"}/>
            <span className={"profile-title"}>Profile</span>
        </div>
    </>)
}