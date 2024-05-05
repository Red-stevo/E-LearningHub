import image from "../Images/learn2.jpeg";


export const ProfileImage = () => {
    return(<>
        <div className={"profile-component"}>
            <div className={"profile-image"} >
                <img src={image} height={"100%"} width={"100%"} alt={"user profile image"}/>
            </div>
            <span className={"profile-title"}>Profile</span>
        </div>
    </>)
}