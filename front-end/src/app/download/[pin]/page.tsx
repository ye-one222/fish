import React, { useState } from "react"
import { BackToMain } from "../../../interface/back.tsx"

export const DownLoadPage:React.FC = () =>{
    const [ files, setFiles ] = useState('')

    return <div className="flex flex-col items-center justify-center h-full gap-5">
    <BackToMain />
        <h1 className=" font-bold text-[50px] ">FISH 받기</h1>
        <div className="flex justify-center bg-[#E8FAFD] w-2/3 h-2/3 md:w-[508px] rounded-[80px] border-2 border-dashed border-[#27416D]">
            {/*file -> map -> fish img*/}
            
            <button className="fixed bottom-[15%] bg-white text-[40px] font-bold rounded-[50px] h-[99px] w-1/2 md:w-[432px] border border-white hover:border-[#27416D] transition-all">Download ALL</button>
        </div>
    </div>
}