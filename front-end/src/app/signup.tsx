import React from 'react'
import { BackToMain } from '../interface/back.tsx'

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

                
                {/* 좌측 상단 - 돌아가기 */}
                <BackToMain />
            </div>
        </div>
    )
}