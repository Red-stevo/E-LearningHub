import {useEffect, useState} from "react";

const useScreenSize = () => {
    const [screenSize, setScreenSize] = useState(0);

    useEffect(() => {
        window.addEventListener("screenResize", returnWidth);
    }, []);

    const returnWidth = () => {
        setScreenSize(window.innerWidth);
    }
    return screenSize;
}

export default useScreenSize;