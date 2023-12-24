import "./ErrorDialog.css";
import "./NotifDialog.css";

export default function DialogBox ( { isErr, content })
{
    if ( isErr ){
        if ( content === "" )
        {
            return ( null );
        }
        return ( 
            <>
                <span className="span-err">
                    { content }
                </span>
                <br/>
            </>
        );
    }
    else {
        if ( content === "" )
        {
            return (null);
        }
        return (
            <>
                <span className="span-notif">
                    {content}
                </span>
                <br/>
            </>
        )
    }
}