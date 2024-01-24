import React from 'react'

export const SignupPage:React.FC=()=>{
    return (
        <div className="bg-white flex flex-row justify-center w-full">
            <div className="bg-white overflow-hidden w-[1512px] h-[982px] relative">
                {/* title */}
                <div className="absolute h-[85px] top-[185px] left-[529px] [font-family:'Inter-SemiBold',Helvetica] font-semibold text-[#27416d] text-[50px] text-center tracking-[0] leading-[normal]">
                    정보를 입력해 주세요
                </div>

                {/* signup form */}
                <form className="absolute w-[556px] h-[210px] top-[357px] left-[478px]">
                    {/* first name */}
                    <div className="absolute w-[230px] h-[60px] top-0 left-[20px] font-medium text-[25px] [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">First Name</div>
                    <input
                        required
                        type="text"
                        className="absolute w-[255px] h-[60px] top-[30px] left-0 [font-family:'Inter-Regular',Helvetica] font-normal text-[#818da2] text-[30px] tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] p-6"
                    />

                    {/* last name */}
                    <div className="absolute w-[230px] h-[60px] top-0 left-[320px] font-medium text-[25px] [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">Last Name</div>
                    <input
                        required
                        type="text"
                        className="absolute w-[255px] h-[60px] top-[30px] left-[300px] [font-family:'Inter-Regular',Helvetica] font-normal text-[#818da2] text-[30px] tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] p-6"
                    />

                    {/* ID - 중복 확인 필요*/}
                    <div className="absolute w-[230px] h-[60px] top-[130px] left-[20px] font-medium text-[25px] [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">ID</div>
                    <input
                        required
                        type="text"
                        className="absolute w-[556px] h-[60px] top-[160px] left-0 [font-family:'Inter-Regular',Helvetica] font-normal text-[#818da2] text-[30px] tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] p-6"
                    />

                    {/* PW */}
                    <div className="absolute w-[230px] h-[60px] top-[250px] left-[20px] font-medium text-[25px] [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">Password</div>
                    <input
                        required
                        type="text"
                        className="absolute w-[556px] h-[60px] top-[280px] left-0 [font-family:'Inter-Regular',Helvetica] font-normal text-[#818da2] text-[30px] tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] p-6"
                    />

                    <button
                        type="submit" 
                        className="absolute w-[292px] h-[67px] top-[400px] left-[132px] [font-family:'Inter-Medium',Helvetica] font-medium text-[#818da2] text-[30px] text-center tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px]"
                    >
                        Sign Up
                    </button>
                </form>

                
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