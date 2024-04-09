import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import {NotFound} from "../RoutedPages/NotFound.jsx";
import {RegistrationPage} from "../RoutedPages/RegistrationPage.jsx";



export default function AppRoutes() {
    return (
        <Router>
            <Routes>
                <Route path={"/student-assistant/register"} element={ <RegistrationPage /> } />
                <Route path="/*" element={ <NotFound /> } />
            </Routes>
        </Router>
    );
}