import { useEffect, useState } from "react"
import { printR, toArray } from "../Utils";
import DialogBox from "./DialogBox";
import "./Button.css";
import Offer from "./Offer";
import axios from "axios";

const GIVE_ALL_POSTS = "http://localhost:8080/api/posts";
const PAGE           = 15; 

export default function Offers( {navigate} )
{
    // empty array of offers ...
    const [err, setErr] = useState("");
    const [offers, setOffers] = useState([]);

    function loadMore ( )
    {
        axios.get ( GIVE_ALL_POSTS +  "?page=" + PAGE ).then ( r => {
            setOffers( offer => [...offer, ...r.data] );
        }).catch( e => {
            setErr( e.message );
        })
    }


    // // on load, make the request
    useEffect( () => {
        axios.get ( GIVE_ALL_POSTS +  "?page=" + PAGE ).then ( r => {
            setOffers( offer => [...offer, ...r.data] );
        }).catch( e => {
            setErr( e.message );
        })
    }, []);


    return ( 
        <>
            <h1>
                Look at all those chickens 🐔!
            </h1>
            <DialogBox content={err} isErr={true}/>

            <ul>
                { offers.map( ( obj ) => <Offer offerObj = {obj}/>)}
            </ul>

            <button onClick={loadMore} className="button">
                Show more ❓
            </button>
        </>
    )
}