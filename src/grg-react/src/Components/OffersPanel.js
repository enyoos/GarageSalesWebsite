import Offer from "./Offer"
import ButtonNavigate from "./ButtonNavigate";

export default function OffersPanel ( { offers, handleDeletePost, navigate} )
{
    return (
        <>
            { offers.map( ( obj ) => <><Offer offerObj = {obj}/> <button onClick={handleDeletePost} id={obj.id} className="button">Delete</button>
                <ButtonNavigate content = {"Modify some offer"} navigate={navigate}/>
            </>)}
        </>
    )
}