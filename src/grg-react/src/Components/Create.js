import { useEffect, useState } from "react"
import axios from 'axios';
import Input from "./Input";
import ErrorDialog from "./ErrorDialog";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import { faEye } from '@fortawesome/fontawesome-free-solid'

// this import can be problematic
import "./Button.css"
import ButtonShow from "./ButtonShow";

const POST_API_URL = "http://localhost:8080/api/vendors/" 

export default function Create( {navigate} )
{
    const [err, setErr] = useState("");

    // for showing the password or na
    const [show, setShow] = useState(false);

    // eww this is gross
    const [username, setUsername] = useState("");
    const [passw, setPassw] = useState("");
    const [email, setEmail] = useState("");
    const [rPassw, setRPassw] = useState("");

    function handleSubmit( event )
    {
        event.preventDefault();

        //   console.log(event.target.username.value)          // or directly
        // const username = event.target.username.value;
        // const passw    = event.target.password.value;
        // const rpassw   = event.target.passwordd.value;
        // const email    = event.target.email.value;


        // check if the passw are matching
        if ( passw !== rPassw) {
            setErr("The passwords are not matching");
        }
        // make post request to the api
        else 
        {
            // create the vendor dict
            const entity = {
                name : username,
                password : passw,
                email : email, 
            }

            axios.post( POST_API_URL , entity).then( resp => {
                // post is succesfull ?
                const keys = Object.keys ( resp );
                const values = Object.values( resp );

                console.log ( "The keys : " + keys );
                console.log ( "The values : " + values );
                console.log ( "The id of the user : " + resp.values.id );

                // cache it
                // cache the id and the name
                sessionStorage.setItem( "username", username);
                sessionStorage.setItem( "userid", resp.id );
               
                // we should only redirect if the status code is OK

                // redirect to the user page
                // use interpolating strings
                navigate( "/User" );

            }).catch( error => {
                // is that message really helpful ?
                setErr( error.message );
            })
        }
    }

    return (
        <>
        <span>{err}</span>
            <form autoComplete="off" onSubmit={(e) => handleSubmit( e )}>

                {err === "" ? "" : <ErrorDialog err={err}/>}

                <label> Enter the username </label>
                {/* <input type="text" name="username" required /> */}
                <Input value={username} setValue={setUsername} />
                <br/>
                <label> Enter the email </label>
                {/* <input  type="text" name="email" required /> */}
                <Input type="email" value={email} setValue={setEmail} />

                <br/>
                <label> Enter the password</label>
                {/* <input type="text"  name="password" required /> */}
                <Input type={show ? "text" : "password"} value={passw} setValue={setPassw} />
                <br/>

                <label> Repeat the password</label>
                {/* <input type="text" name="passwordd" required /> */}
                <Input type={show ? "text" : "password"} value={rPassw} setValue={setRPassw} />
                <ButtonShow setShow={setShow} show = {show}/>

                <br/>

                <input className="button" type="submit" value="submit"/>
            </form>
        </>
    )
}