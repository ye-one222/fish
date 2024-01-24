import React from "react"
import '../tailwind.css';
import { Link } from "react-router-dom";

export const DownLoadPage:React.FC = ()=>{
    return <div>
        <h1>hello this is Down load page</h1>
        <Link to="/login"><button>btn</button></Link>
    </div>
}