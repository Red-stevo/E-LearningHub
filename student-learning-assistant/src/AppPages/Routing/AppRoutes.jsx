import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import {NotFound} from "../RoutedPages/NotFound.jsx";
import {RegistrationPage} from "../RoutedPages/RegistrationPage.jsx";
import {LoginPage} from "../RoutedPages/LoginPage.jsx";
import {MainPage} from "../RoutedPages/MainPage.jsx";
import ProtectedRoutes from "../Component/ProtectedRoutes.jsx";



export default function AppRoutes() {
    return (
        <Router>
            <Routes>
                <Route path={"/student-assistant/register"} element={ <RegistrationPage /> } />
                <Route path={"/student-assistant/login"} element={<LoginPage />} />
                <Route path={"/student-assistant/main"} element={(<ProtectedRoutes>
                    <MainPage />
                </ProtectedRoutes>)} />
                <Route path="/*" element={ <NotFound /> } />
            </Routes>
        </Router>
    );
}