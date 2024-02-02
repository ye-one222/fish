import React from "react"

export const DownLoadPage:React.FC = () =>{

    return <div className="flex flex-col items-center justify-center h-full">
    
        {/* 좌측 상단 - 돌아가기 (나중에 컴포넌트 새로 하나 만들어서 재활용 ㄱ) */}
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
        </div>

        <h1 className=" font-bold text-[50px] ">FISH 받기</h1>
        <div className="bg-[#E8FAFD] w-2/3 h-2/3 md:w-[508px] rounded-[80px] border border-dashed border-black">
            {/*file -> map -> fish imoji*/}
            <button className="bg-white rounded-[50px] h-[99px] w-[90%]">Download ALL</button>
        </div>
    </div>
}