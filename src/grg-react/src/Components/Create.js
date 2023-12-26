import { useEffect, useState } from "react"
import axios from 'axios';
import Input from "./Input";
import DialogBox from "./DialogBox";

// this import can be problematic
import "./Button.css"
import ButtonShow from "./ButtonShow";
import { printR, saveCookies } from "../Utils";

const POST_API_URL = "http://localhost:8080/api/vendors/" 

export default function Create( {navigate} )
{
    const [ statusRequest, setStatusRequest ] = useState({});
    const [show, setShow] = useState(false);
    const [username, setUsername] = useState("");
    const [passw, setPassw] = useState("");
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
            setStatusRequest({content: "The passwords are not matching", isErr: true });
        }
        // make post request to the api
        else 
        {
            // create the vendor dict
            const entity = {
                name : username,
                password : passw,
            }

            axios.post( POST_API_URL , entity).then( resp => {
                // post is succesfull ?
                // cache the id and the name
                printR( resp );
                saveCookies( username, resp.data.id );
                // we don't need to check if the status code is OK
                // since axios is handling that for us already
                navigate( "/User" );

            }).catch( error => {
                // is that message really helpful ?
                setStatusRequest( { content : error.message, isErr : true } );
            })
        }
    }

    return (
        <>
            <form autoComplete="off" onSubmit={(e) => handleSubmit( e )}>
                <DialogBox isErr={statusRequest.isErr} content={statusRequest.content}/>

                <label> Enter the username </label>
                {/* <input type="text" name="username" required /> */}
                <Input value={username} setValue={setUsername} />
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