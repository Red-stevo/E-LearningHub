import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import {NotFound} from "../RoutedPages/NotFound.jsx";
import {RegistrationPage} from "../RoutedPages/RegistrationPage.jsx";
import {LoginPage} from "../RoutedPages/LoginPage.jsx";
import {MainPage} from "../RoutedPages/MainPage.jsx";
import ProtectedRoutes from "../Component/ProtectedRoutes.jsx";
import {IndexPage} from "../RoutedPages/IndexPage.jsx";
import {MainHeader} from "../Component/MainHeader.jsx";



export default function AppRoutes() {
    return (
        <Router>
            <Routes>
                <Route path={"/"} element={<IndexPage /> } />
                <Route path={"/student-assistant/register"} element={ <RegistrationPage /> } />
                <Route path={"/student-assistant/login"} element={<LoginPage />} />
                <Route path={"/student-assistant/learn"} element={(<ProtectedRoutes><MainHeader /></ProtectedRoutes>)}>
                    <Route path={"main"}/>
                </Route>
                <Route path="*" element={ <NotFound /> } />
            </Routes>
        </Router>
    );
}