import { useEffect, useState } from "react"
import { printR } from "../Utils";
import DialogBox from "./DialogBox";
import Offer from "./Offer";
import axios from "axios";


const GIVE_ALL_POSTS = "http://localhost:8080/api/vendors/posts";
const PAGE           = 15; 

export default function Offers( {navigate} )
{
    // // empty array of object
    // const [err, setErr] = useState("");
    // const [offers, setOffers] = useState([]);

    // // on load, make the request
    // useEffect( () => {
    //     axios.get ( GIVE_ALL_POSTS +  "?page=" + PAGE ).then ( r => {

    //         printR( r );
    //         printR( r.data );
            
    //     }).catch( e => {
    //         console.log ( e );
    //         // printR( e );
    //         // setErr( e.message );
    //     })
    // }, []);


    return ( 
        <>
            <h1>
                Look at all those chickens 🐔!
            </h1>
            {/* <DialogBox content={err} isErr={true}/> */}

            {/* <ul>
                { offers.map( ( obj ) => <Offer offerObj = {obj}/>)}
            </ul> */}
        </>
    )
}