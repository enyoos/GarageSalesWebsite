import "./Input.css";

export default function Input ( {value, setValue, placeHolder="", type="text", isRequired = true, title=""} )
{
    return (
        <input 
            className="input" 
            type={type} 
            autoComplete="off" 
            placeholder={placeHolder} 
            onChange={ type==="text" ? ( e ) => setValue ( e.target.value ) : ( e ) => setValue ( e ) } 
            required={isRequired}
        />
    )
}