import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import {NotFound} from "../RoutedPages/NotFound.jsx";



export default function AppRoutes() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={ <NotFound /> } />
            </Routes>
        </Router>
    );
}