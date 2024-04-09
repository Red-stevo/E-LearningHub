import {configureStore} from "@reduxjs/toolkit";
import { RegistrationPageData } from "./RegistrationPageData.js";

export default configureStore ({
    reducer:{
        RegStore: RegistrationPageData.reducer
    }
});