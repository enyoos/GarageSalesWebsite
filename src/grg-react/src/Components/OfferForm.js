import { useEffect, useState } from "react"
import Input from "./Input";
import "./Button.css";
import TextArea from "./TextArea";
import { printR, spitDateString } from "../Utils";
import axios from "axios";
import DialogBox from "./DialogBox";

// PUT THAT INTO THE UTILS FILE
// NEED TO SPECIFY THE ID AND /POSTS AT THE END
const CREATE_POST_URL_API = "http://localhost:8080/api/vendor/" 


export default function OfferForm( {navigate} )
{

    const [ statusRequest, setStatusRequest ] = useState({ content : "" , isErr : false } );
    const [title, setTitle] = useState ("");
    const [desc, setDesc ]  = useState("");
    // this blob is just a file
    const [blob, setBlob]   = useState(null);


    async function handleUpload( e )
    {
        let image = e.target.files[0];
        const buffer = await image.arrayBuffer();
        let byteArray = new Int8Array( buffer );
        setBlob(Array.prototype.slice.call(byteArray));
    } 

    function handleSubmit( e )
    {
        e.preventDefault();

        const date = spitDateString( new Date() );
        const id   = sessionStorage.getItem( "userid" );
        console.log ( "the id : " + id );
        console.log ( "the date : " + date )

        // CONTINUE HERE..
        const OfferEntity = {
            datepub : date,
            title   : title,
            description : desc,
            image : blob
        }

        axios.post ( CREATE_POST_URL_API + id + "/posts", OfferEntity ).then ( r => {
            printR( r );
            setStatusRequest( { content : "Posted the offer ✅", isErr : false });
            // need to reset the form
        }).catch ( e => {
            printR ( e );
            setStatusRequest( { content : e.message, isErr : true } );
        })
    }

    return (
        <>
            <h1>
                Ready to be a chicken 🐔?
            </h1>

            <form onSubmit={handleSubmit}>

                <DialogBox isErr={statusRequest.isErr} content={statusRequest.content}/>

                <label> Enter the title </label>
                {/* <input type="text" name="username" required /> */}
                <Input value={title} setValue={setTitle} />
                <br/>
                <label> Enter the description of the Offer</label>
                <br/>
                {/* <input  type="text" name="email" required /> */}
                {/* <Input type="text" value={desc} setValue={setDesc} /> */}
                <TextArea setValue={setDesc}/>
                <br/>
                {/* <input type="text"  name="password" required /> */}
                <Input type={"file"} 
                    required={false}
                    setValue={handleUpload} 
                    placeHolder="Load an image" 
                    title="Your image should display your Offer"/>
                <br/>

                <input className="button" type="submit" value="submit"/>
 
            </form>
        </>
    )
}