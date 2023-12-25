export function OK( status ) { return status > 199 && status < 300; }
// export function dummyValidate ( status ) { return true; }
export function printR ( obj )
{
    console.log ("The keys : " + Object.keys ( obj ));
    console.log ("The values : " + Object.values( obj ));
}

export function saveCookies ( username, id )
{
    sessionStorage.setItem( "username", username);
    sessionStorage.setItem( "userid", id );
}

export function spitDateString ( date ) { return `${date.getDate()}/${date.getMonth()}/${date.getFullYear()}`; }
export function constructSource ( byteArray ) {
    // transform the byte array to base64
    return  "data:image/png;base64," + tob64( byteArray );
}

function tob64 ( barray )
{
    return btoa( 
        new Uint8Array( barray ).reduce ( ( dt, byte ) => dt + String.fromCharCode(byte), '')
    );
}