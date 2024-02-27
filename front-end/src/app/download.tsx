import React, { useState } from "react"
import '../tailwind.css';
import { BackToMain } from "../interface/back";
//import { Link } from "react-router-dom";

export const DownLoadPage:React.FC = ()=>{
    const [pinClick, setPinClick] = useState<boolean>(false)
    const fishImages = ['🐠', '🐋','🐟' , '🐡', '🐬', '🐳']

    
    const EachPinInput: React.FC<{ props: string }> = ({ props })=> {
        const handlePinInput = (event) => {
            const pinNum = event.target.value.replace(/[^0-9]/g,'') //문자열들어오면 입력은 되지만 핀번호로는 무시함
            console.log(pinNum) 
        }
        const handlePinClick = () => {
                if(props==='🐠'){
                    //버튼 숨기기
                    setPinClick(true)
        
                }else{
                    setPinClick(false)
                }
            }

        return <div onClick = {handlePinClick} className="flex bg-[#E8FAFD] rounded-[25px] w-[70px]">
            { pinClick&&props==='🐠' ? 
                <input type="text" onChange={handlePinInput} className="flex items-center rounded-[25px] text-[#27416d] bg-[#E8FAFD] focus: outline-none max-w-full"></input>
                    :<button  className="text-[50px]">{props}</button>
            }
        </div>
    }

    return <div className="flex flex-col items-center justify-center h-full">
        
        <BackToMain/>
        
        <h1 className="text-[50px]">FISH 받기</h1>
        <h1 className="text-[30px]">핀번호 6자리를 입력하세요</h1>
        <div className="flex flex-row">
            {fishImages.map((each)=>(<EachPinInput key={each} props={each}/>))}
        </div>
    </div>
}