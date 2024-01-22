import React from 'react'

export const LoginPage:React.FC=()=>{
    return (
        <div className="bg-white flex flex-row justify-center w-full">
            <div className="bg-white overflow-hidden w-[1512px] h-[982px] relative">
                {/* title */}
                <div className="absolute h-[85px] top-[220px] left-[447px] [font-family:'Inter-SemiBold',Helvetica] font-semibold text-[#27416d] text-[70px] text-center tracking-[0] leading-[normal]">
                    Let's go catch fish
                </div>

                {/* login form */}
                <form className="absolute w-[556px] h-[207px] top-[357px] left-[478px]">
                    <input
                        required
                        type="text"
                        placeholder="ID"
                        className="absolute w-[556px] h-[66px] top-[23px] left-0 [font-family:'Inter-Regular',Helvetica] font-normal text-[#818da2] text-[30px] tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] p-6"
                    />
                    <input
                        required
                        type="text"
                        placeholder="Password"
                        className="absolute w-[556px] h-[66px] top-[120px] left-0 [font-family:'Inter-Regular',Helvetica] font-normal text-[#818da2] text-[30px] tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] p-6"
                    />

                    <button
                        type="submit" 
                        className="absolute w-[292px] h-[67px] top-[240px] left-[132px] [font-family:'Inter-Medium',Helvetica] font-medium text-[#818da2] text-[30px] text-center tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px]"
                    >
                        Log In
                    </button>
                </form>

                <div className="absolute w-[309px] h-[40px] top-[700px] left-[601px] [font-family:'Inter-Regular',Helvetica] font-normal text-[#27416d] text-[20px] text-center tracking-[0] leading-[normal]">
                    회원가입
                </div>

                
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
                
            </div>
        </div>
    )
}