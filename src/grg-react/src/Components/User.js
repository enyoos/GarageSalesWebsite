import ButtonNavigate from "./ButtonNavigate";

export default function User( {navigate} )
{
    const username = sessionStorage.getItem("username");
    console.log ( username );

    if ( username != undefined )
    {
        return (
            <>
                <h1>
                    Welcome {username}, &#128075;
                </h1>
                <ButtonNavigate content={"Check some offers"} navigate={navigate}/>
                <ButtonNavigate content={"Post an Offer"} navigate={navigate}/>
                <ButtonNavigate content={"Check all of my Offers"} navigate={navigate}/>
            </>
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