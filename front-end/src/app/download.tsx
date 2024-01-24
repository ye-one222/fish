import React, { useState } from "react"
import '../tailwind.css';

export const DownLoadPage:React.FC = ()=>{
    const [pinClick, setPinClick] = useState<boolean>(false)
    const [pinInput,setPinInput] = useState<number|null>(null)
    const fishImages = ['🐠', '🐋','🐟' , '🐡', '🐬', '🐳']

    function handlePinClick(){
        setPinInput(null)
    }
    const EachPinInput:React.FC<string> = (props) => {
        return <div className="bg-[#E8FAFD] rounded-[25px]">
            <input type="text" className="text-black focus: outline-none w-5 "></input>
            <button onClick = {handlePinClick} className="text-[50px]">{props}</button>
        </div>
    }


    return <div className="flex flex-col items-center justify-center h-full">
        
        {/* 좌측 상단 - 돌아가기 */}
        <div className="absolute w-[604px] h-[124px] top-[-17px] left-[-142px]">
            <div className="absolute w-[29px] h-[50px] top-[40px] left-[163px]">
                <div className="h-[50px]">
                    <div className="w-[29px] h-[50px]">
                        <div className="h-[50px]">
                        <div className="relative w-[29px] h-[53px] top-[-3px]">
                            <img className="h-[30px] top-0 absolute w-[29px] left-0" alt="Line" src="/img/line-1.svg" />
                            <img className="h-[29px] top-[24px] absolute w-[29px] left-0" alt="Line" src="/img/line-2.svg" />
                        </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="w-[604px] h-[124px] top-7 left-0 font-bold text-[60px] text-center absolute [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">
                FISH
            </div>
            <p className="absolute w-[136px] h-[67px] top-[66px] left-[382px] [font-family:'Inter',Helvetica] font-medium text-transparent text-[20px] tracking-[0] leading-[normal]">
                <span className="text-[#27416d]">: FI</span>
                <span className="text-[#27416d80]">le</span>
                <span className="text-[#27416d]"> SH</span>
                <span className="text-[#27416d80]">are</span>
            </p>
        </div>{/*예원이꺼 복사! 나중에 컴포넌트로 바꾸자고 */}
        
        <h1 className="text-[50px]">FISH 받기</h1>
        <h1 className="text-[30px]">핀번호 6자리를 입력하세요</h1>
        <div className="flex flex-row">
            {fishImages.map((each)=>(EachPinInput(each)))}
            
        </div>
    </div>
}