import { useState } from "react";
import ErrorDialog from "./ErrorDialog";
import Input from "./Input";
import ButtonShow from "./ButtonShow";

export default function Login()
{

    // the err from the axios api, is an js object
    const [show, setShow]   = useState(false);
    const [err, setErr]     = useState("");
    const [email, setEmail] = useState("");
    const [passw, setPassw] = useState("");

    function handleSubmit( e )
    {
        e.preventDefault();

    }


    return (
        <>
            <h1>
                Enter your credentials, ⌨️
            </h1>
            <form autoComplete="off" onSubmit={(e) => handleSubmit( e )}>

                {Object.keys ( err ).length === 0 ? "" : <ErrorDialog err={err}/> }

                <label> Enter the email </label>
                {/* <input  type="text" name="email" required /> */}
                <Input type="email" value={email} setValue={setEmail} />

                <br/>
                <label> Enter the password</label>
                {/* <input type="text"  name="password" required /> */}
                <Input type={show ? "text" : "password"} value={passw} setValue={setPassw} />
                <br/>
                <ButtonShow setShow={setShow} show = {show}/>
                <br/>
                <input className="button" type="submit" value="submit"/>
            </form>
        </>
    )
}