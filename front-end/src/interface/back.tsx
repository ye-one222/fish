import React from 'react'
import { Link } from 'react-router-dom'

{/* 좌측 상단 - 돌아가기 (나중에 컴포넌트 새로 하나 만들어서 재활용 ㄱ) */}
export const BackToMain:React.FC=()=>{
return (
    <div className="absolute w-[500px] h-[100px] top-[-17px] left-[-142px]">
        <Link to="/">
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
        </Link>
        <div className="w-[200px] top-7 left-[230px] font-bold text-[60px] text-left absolute [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">
            FISH
        </div>
        <p className="absolute w-[136px] h-[67px] top-[66px] left-[382px] [font-family:'Inter',Helvetica] font-medium text-transparent text-[20px] tracking-[0] leading-[normal]">
            <span className="text-[#27416d]">: FI</span>
            <span className="text-[#27416d80]">le</span>
            <span className="text-[#27416d]"> SH</span>
            <span className="text-[#27416d80]">are</span>
        </p>
    </div>
    )
}