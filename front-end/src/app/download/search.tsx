import React, { useState } from "react"
import { BackToMain } from "../../interface/back.tsx"
import { DownLoadPage } from "./[pin]/page.tsx"
//import { hover } from "@testing-library/user-event/dist/hover";

export const SearchPage:React.FC = ()=>{
    const fishImages = ['🐠', '🐋','🐟' , '🐡', '🐬', '🐳']
    const [pinNum, setPinNum] = useState<string>('')
    const [isPin, setIsPin] = useState<boolean>(false) 
    const [ clickBtn, setClickBtn ] = useState<boolean>(false)

    const EachPadBtn:React.FC<number> = ( props ) => {
        
        const handleBackBtn = () => {
            setPinNum(pinNum.slice(0,-1));
            setIsPin(false);
        }
        const handlePinInput = () => {
            setPinNum(pinNum+(props))

            if( pinNum.length === 5 ){
                setIsPin(true);
            }
        }
        if( props === 11 )
        {
            return <button onClick={handleBackBtn} className="bg-[#E8FAFD] rounded-[20px] w-[67px] h-[67px] text-[20px]">back</button>
        }
        else if( props === 9 )
        {
            return <h1 className=" w-[67px] h-[67px] text-[30px] text-white">0</h1>
        }
        else if( props === 10 ) { props = props - 1}

        props = ( props + 1 ) % 10

        return <button 
            onClick={ handlePinInput } 
            className="bg-[#EFFCFE] rounded-[20px] w-[67px] h-[67px] text-[30px]">
                {props}
            </button>
    }

    if(clickBtn){
        return <DownLoadPage pinNum={ pinNum } />
    }else{
        return <div className="flex flex-col gap-5 items-center justify-center h-full">
            <BackToMain/>
            <h1 className="text-[50px]">FISH 받기</h1>
            <h1 className="text-[30px]">핀번호 6자리를 입력하세요</h1>
            <div className="flex flex-row gap-2 ">
                {fishImages.map((each, index)=>{
                    return <div className="flex bg-[#EFFCFE] rounded-[25px] w-[76px] h-[76px]">
                        { index < pinNum.length ? 
                            <h1 className="[font-family:'Inter',Helvetica] flex items-center text-[40px] ml-[27px]">{pinNum.at(index)}</h1>
                            :<button disabled={true} className="text-[50px]">{each}</button>
                        }
                    </div>
                    })
                }
            </div>
            <div className=" items-center grid grid-cols-3 gap-3">
                {[...Array(12)].map((each, index) => (EachPadBtn(index)))}
            </div>
            <button 
            disabled={!isPin} 
            onClick={ () => { setClickBtn(true) } }
            className={`bg-[#E8FAFD] rounded-[50px] text-[30px] w-[196px] h-[90px] border border-[#EFFCFE] hover:border-[#27416d] 
                ${!isPin ? 'text-zinc-400 hover:border-zinc-200'
                    :{}} transition-all`}>
                        받기</button>
        </div>
    }
}