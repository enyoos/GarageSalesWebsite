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