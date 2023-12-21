import { useState } from "react";
import Button from "./ButtonNavigate";
import Input from "./Input";

export default function Home({navigate})
{
    const [value, setValue] = useState("");

    return (
        <>
            <h1>
                Welcome to my Garage sales application, 🏡
            </h1>
            <Button content={"Login"} navigate={navigate}/>
            <Button content={"Sign up"} navigate={navigate}/>
        </>
    )
}