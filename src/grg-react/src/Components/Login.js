import { useState } from "react";
import ErrorDialog from "./ErrorDialog";
import Input from "./Input";
import ButtonShow from "./ButtonShow";
import axios from "axios";
import { printR, saveCookies } from "../Utils";

const GET_API_URL = "http://localhost:8080/api/vendors/";

export default function Login({navigate})
{

    // the err from the axios api, is an js object
    const [show, setShow]   = useState(false);
    const [err, setErr]     = useState("");
    const [username, setUsername] = useState("");
    const [passw, setPassw] = useState("");

    function handleSubmit( e )
    {
        e.preventDefault();

        axios.get(GET_API_URL + "name?name=" + username).then( resp  => {

            printR( resp.data );

            if ( resp.data.password === passw.trim() )
            {
                saveCookies( username, resp.id );
                navigate( "/User" );
            }
            else{ setErr ( "The password is incorrect" ); }
        }).catch(  err  => {
            const msg = `The vendor with the name ${username}`;
            setErr( msg );

        } )
    }


    return (
        <>
            <h1>
                Enter your credentials, ⌨️
            </h1>
            <form autoComplete="off" onSubmit={(e) => handleSubmit( e )}>

                <ErrorDialog err={err}/>

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