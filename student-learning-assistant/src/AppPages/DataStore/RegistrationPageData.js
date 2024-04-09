import {createSlice} from "@reduxjs/toolkit";

export const RegistrationPageData = createSlice({
    name:"reg-page-data",
    initialState:{
        regFormState:true,
        verifyFormState:false,
        verifyBar:0
    },
    reducers:{
        setRegFormState:(state, value) => {state.regFormState = value.payload},
        setVerifyFormState:(state, value) => {state.verifyFormState = value.payload},
        setVerifyBar:(state, value) => {state.verifyBar = value.payload}
    }
});

export const {
    setRegFormState,
    setVerifyFormState,
    setVerifyBar
} = RegistrationPageData.actions
