import {Navigate, Outlet, useLocation} from "react-router";

function ProtectedRoutes({redirectPath="/login", children} : any){

    const location = useLocation();

    if(!sessionStorage.getItem("isLoggedIn")){
        return <Navigate to={redirectPath} replace state={{from:location}} />
    }
return children || <Outlet />
}

export default ProtectedRoutes;