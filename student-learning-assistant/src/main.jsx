import React from "react";
import ReactDom from 'react-dom/client'
import App from "./App.jsx";
import 'bootstrap/dist/css/bootstrap.min.css';
import store from "./AppPages/DataStore/store.js";
import {Provider} from "react-redux";


ReactDom.createRoot(document.getElementById("root")).render(
    <React.StrictMode>
        <Provider store={store}>
            <App />
        </Provider>
    </React.StrictMode>
)