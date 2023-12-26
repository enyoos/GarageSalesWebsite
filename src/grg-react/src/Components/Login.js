import { useState } from "react";
import Input from "./Input";
import ButtonShow from "./ButtonShow";
import axios from "axios";
import { printR, saveCookies } from "../Utils";
import DialogBox from "./DialogBox";

const GET_VENDOR_BY_NAME_URL_API = "http://localhost:8080/api/vendor/";

export default function Login({navigate})
{

    // the err from the axios api, is an js object
    const [show, setShow]   = useState(false);
    const [username, setUsername] = useState("");
    const [ statusRequest, setStatusRequest ] = useState({});
    const [passw, setPassw] = useState("");

    function handleSubmit( e )
    {
        e.preventDefault();

        axios.get( GET_VENDOR_BY_NAME_URL_API + "name?name=" + username).then( resp  => {
            if ( resp.data.password === passw.trim() )
            {
                saveCookies( username, resp.data.id );
                navigate( "/User" );
            }
            else{ setStatusRequest( { content: "The password is incorrect" , isErr : true } ); }
        }).catch(  err  => {
            const msg = `The vendor with the name ${username} doesn't exist ...`;
            setStatusRequest( { content : msg, isErr: true } );
        } )
    }


    return (
        <>
            <h1>
                Enter your credentials, ⌨️
            </h1>
            <form autoComplete="off" onSubmit={(e) => handleSubmit( e )}>

                <DialogBox content={statusRequest.content} isErr={statusRequest.isErr}/>

                <label> Enter the username</label>
                {/* <input  type="text" name="email" required /> */}
                <Input type="text" value={username} setValue={setUsername} />

                <br/>
                <label> Enter the password</label>
                {/* <input type="text"  name="password" required /> */}
                <Input type={show ? "text" : "password"} value={passw} setValue={setPassw} />
                <ButtonShow setShow={setShow} show = {show}/>
                <br/>
                <input className="button" type="submit" value="submit"/>
            </form>
        </>
    )
}