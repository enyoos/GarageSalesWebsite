import "./ErrorDialog.css";

export default function ErrorDialog ( { err } )
{
    return ( 
        <>
            <span className="span"> {err} </span><br/>
        </>
    )
}