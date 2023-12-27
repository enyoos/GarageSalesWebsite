import { useEffect, useState } from "react"
import DialogBox from "./DialogBox";
import axios from "axios";
import "./Button.css";
import { printR } from "../Utils";
import OffersPanel from "./OffersPanel";

const GIVE_ALL_POST_OF_SPECEFIC_VENDOR = "http://localhost:8080/api/vendor/";
const DELETE_POST_BY_ID_URL            = "http://localhost:8080/api/posts/";
const DELETE_ALL_POST_FROM_VENDOR_ID   = "http://localhost:8080/api/vendor/";

export default function MyPosts( {navigate} )
{
    const [err, setErr] = useState("");
    const [offers, setOffers] = useState([]);
    const username = sessionStorage.getItem( "username" );
    const id       = sessionStorage.getItem ( "userid" ); 

    function handleDeletePost ( e )
    {
        let id = e.target.id;
        axios.delete( DELETE_POST_BY_ID_URL + id ).then ( r => {
        }).catch ( e => {
            setErr( e.message );
        });
        setOffers( offers => offers.filter ( obj => obj.id !== id ) );
    }

    function handleDeleteAllPost( e )
    {
        axios.delete( DELETE_ALL_POST_FROM_VENDOR_ID + id + "/posts" ).then ( r => {
        }).catch ( e => {
            setErr( e.message );
        });

        // add the code here...
    }

    useEffect( () => {
        axios.get ( GIVE_ALL_POST_OF_SPECEFIC_VENDOR + id + "/posts" ).then ( r => {
            setOffers( offer => [...offer, ...r.data] );
        }).catch( e => {
            setErr( e.message );
        })
    }, [])
    
    return (
        <>
            <h1>
                All of your 🎁, {username}.
            </h1>

            <DialogBox content={err} isErr={true}/>

            <ul>
                <OffersPanel offers={offers} handleDeletePost={handleDeletePost} navigate={navigate}/>
            </ul>

            { offers.length === 0 ? "No offers for now 😔..." : 
                <button onClick={handleDeleteAllPost} className="button-delete">
                    Delete all
                </button>
            }
        </>
    )
}