import "./Offer.css";

export default function Offer ( { offerObj } )
{

    const { title, datepub, description, image } = offerObj;

    return ( 
        <>
            <li>
                title : {title}, date publication : {datepub}, 
                descriptiond : {description} and the image : 

            </li>
        </>
    )
}