import "./Input.css";

export default function Input ( {value, setValue, placeHolder="", type="text", isRequired = true} )
{
    return (
        <input 
            className="input" 
            type={type} 
            autoComplete="off" 
            placeholder={placeHolder} 
            onChange={ ( e ) => setValue ( e.target.value ) } 
            required={isRequired}
        />
    )
}