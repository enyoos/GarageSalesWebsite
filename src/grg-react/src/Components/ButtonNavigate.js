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
        
        else if ( content === "Post an Offer" || content === "Modify some offer" )
        { navigate( "/User/Post" ); }
        
        else if ( content === "Check all of my Offers" )
        { navigate( "/User/MyPosts" ) }
    }

    return (
        <button className="button" onClick={handleClick}>
            {content}
        </button>
    )
}