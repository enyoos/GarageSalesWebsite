import { constructSource } from "../Utils";
import "./Offer.css";

export default function Offer ( { offerObj } )
{

    const { id, title, datepub, description, image } = offerObj;

    return ( 
        <>
            <li key={id}>
                title : {title}, date publication : {datepub}, 
                description : {description}, with the following image : 
                <img className="image" alt="offer's image" src={constructSource( image )}/>
            </li>
        </>
    )
}