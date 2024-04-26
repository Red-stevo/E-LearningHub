import AppRoutes from "./AppPages/Routing/AppRoutes.jsx";
import {Container} from "react-bootstrap";

const App = () => {
    return (
        <Container fluid>
            <AppRoutes />
        </Container>
    );
};

export default App;