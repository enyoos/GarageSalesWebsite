import "./Button.css"; 

export default function ButtonNavigate ( {content, navigate} )
{

    function handleClick()
    {
        if ( content === "Login" )
        { navigate("Login"); }

        else if ( content === "Sign up")
        { navigate ( "Sign" ); }
        
        else if ( content === "Check some offers" )
        { navigate( "/User/Offers" ); }
        
        else if ( content === "Post an Offer")
        { navigate( "/User/Post" ); }
    }

    return (
        <button className="button" onClick={handleClick}>
            {content}
        </button>
    )
}