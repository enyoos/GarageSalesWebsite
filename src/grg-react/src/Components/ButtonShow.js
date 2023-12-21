import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import "./Button.css";
import { useState } from "react"
import { faEye, faEyeSlash } from "@fortawesome/fontawesome-free-solid";

export default function ButtonShow({ show, setShow})
{
    const [bool, setBool] = useState( true );
    const defaultJsx = <FontAwesomeIcon icon={faEye}/>;
    const closedEyeJsx = <FontAwesomeIcon icon={faEyeSlash}/>

    function handleClick( e ) { 
        e.preventDefault();
        setShow( !show );
        setBool( !bool );
    }

    return (
        <button className="button" onClick={handleClick}>{bool ? closedEyeJsx: defaultJsx}</button>
    )
}