import {Navigate, Outlet, useLocation} from "react-router";

// eslint-disable-next-line react/prop-types
function ProtectedRoutes({redirectPath="/student-assistant/login", children}){

    const location = useLocation();

    if(!sessionStorage.getItem("isLoggedIn")){
        return <Navigate to={redirectPath} replace state={{from:location}} />
    }
return children || <Outlet />
}

export default ProtectedRoutes;