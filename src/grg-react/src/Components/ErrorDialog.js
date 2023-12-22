import "./ErrorDialog.css";

export default function ErrorDialog ( { err } )
{
    if ( err === "" ) { return (<></>) }
    return ( 
        <>
            <span className="span"> {err} </span><br/>
        </>
    )
}