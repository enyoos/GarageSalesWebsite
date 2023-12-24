import "./Input.css";

export default function TextArea({setValue})
{
    return (
        <textarea className="input" onChange={( e ) => setValue ( e.target.value)}/>
    )
}