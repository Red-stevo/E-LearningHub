import image from "../Images/learn2.jpeg";


export const ProfileImage = () => {
    return(<>
        <div className={"profile-image"}>
            <img src={image} height={"50px"} width={"50px"} alt={"user profile image"} className={"image"}/>

            <span className={"profile-title"}>Profile</span>
        </div>
    </>)
}