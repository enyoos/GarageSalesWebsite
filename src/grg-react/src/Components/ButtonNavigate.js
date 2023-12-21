import "./Button.css"; 

export default function ButtonNavigate ( {content, navigate} )
{

    function handleClick()
    {
        if ( content === "Login" )
        { navigate("Login"); }

        else if ( content === "Sign up")
        { navigate ( "Sign" ); }
    }

    return (
        <button className="button" onClick={handleClick}>
            {content}
        </button>
    )
}