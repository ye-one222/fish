import React, { useState } from "react"
//import { hover } from "@testing-library/user-event/dist/hover";

export const SearchPage:React.FC = ()=>{
    const fishImages = ['🐠', '🐋','🐟' , '🐡', '🐬', '🐳']
    const [pinNum, setPinNum] = useState<string>('')
    const [isPin, setIsPin] = useState<boolean>(false)  //핀하고 그물암호 있으면 false로 변경
    const [hasGmool, setHasGmool] = useState<boolean>(false)

    const EachPadBtn:React.FC<number> = ( props ) => {
        
        const handleBackBtn = () => {
            setPinNum(pinNum.slice(0,-1))
        }
        
        const handlePinInput = () => {
            setPinNum(pinNum+(props))

            if( pinNum.length === 5 ){
                //pinNum으로 디비에서 파일 찾고
                //그물암호가 있다면
                setHasGmool(true)
                setIsPin(true)  //그물암호가 없다면!
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
        else if( props === 10 ) 
        { props = props - 1}

        props = ( props + 1 ) % 10

        return <button 
            onClick={ handlePinInput } 
            className="bg-[#EFFCFE] rounded-[20px] w-[67px] h-[67px] text-[30px]">
                {props}
            </button>
    }

    return <div className="flex flex-col gap-5 items-center justify-center h-full">
        
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
        {!hasGmool ? 
            <div className=" items-center grid grid-cols-3 gap-3">
                {[...Array(12)].map((each, index) => (EachPadBtn(index)))}
            </div>
            :<div className="flex flex-col items-center justify-center h-[300px] w-full gap-5 ">
                <h1 className="text-[30px]">그물 암호를 입력하세요</h1>
                <input type="text" placeholder='그물 암호' className="placeholder:text-zinc-400 text-xl bg-[#E8FAFD] p-3 rounded-[50px] w-1/3 h-[66px] focus: outline-none"/>
            </div>
            }
    
        
            
        
        <button disabled={!isPin} className={`bg-[#E8FAFD] rounded-[50px] text-[30px] w-[196px] h-[90px] border border-[#EFFCFE] hover:border-[#27416d] ${!isPin ? 'text-zinc-400 hover:border-zinc-200':{}} transition-all`}>받기</button>
    </div>
}