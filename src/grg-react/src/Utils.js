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

export function spitDateString ( date ) { return `${date.getFullYear()}-${date.getMonth()}-${date.getDay()}`;}
export function constructSource ( byteArray ) { return  "data:image/png;base64," + tob64( byteArray ); }
export function toArray ( obj ) { 
    var arr = [];
    return Object.keys(obj).map((key) => [...arr, obj[key]] ) ; 
}

function tob64 ( barray )
{
    return btoa( 
        new Uint8Array( barray ).reduce ( ( dt, byte ) => dt + String.fromCharCode(byte), '')
    );
}