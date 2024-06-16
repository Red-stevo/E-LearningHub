import {useEffect} from "react";

const useScreenSize = () => {
    useEffect(() => {
        window.addEventListener("screenResize", returnWidth);
    }, []);

    const returnWidth = () => {
        return window.innerWidth;
    }
}

export default useScreenSize;