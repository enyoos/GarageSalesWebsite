export default function User( {navigate} )
{
    const username = sessionStorage.getItem("username");
    console.log ( username );

    if ( username != undefined )
    {
        return (
            <h1>
                Welcome {username}, &#128075;
            </h1>
        ) 
    }
    // you need to login
    // else { navigate("/Login"); }
    return (
        <h1>
            useNavigate call how to ?
        </h1>
    )
}