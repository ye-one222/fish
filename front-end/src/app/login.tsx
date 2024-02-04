import React from 'react'
import { Link } from 'react-router-dom'
import { BackToMain } from '../interface/back.tsx'

export const LoginPage:React.FC=()=>{
    return (
        <div className="bg-white h-full flex flex-col items-center justify-center">
            {/* title */}
            <div className="flex items-center [font-family:'Inter-SemiBold',Helvetica] font-semibold text-[#27416d] text-[70px] text-center tracking-[0] leading-[normal]">
                Let's go catch fish
            </div>

            {/* login form */}
            <form className="flex flex-col w-full items-center justify-center">
                <input
                    required
                    type="text"
                    placeholder="ID"
                    className="w-1/3 mt-20 [font-family:'Inter-Regular',Helvetica] font-normal text-[#818da2] text-[20px] tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] px-6 py-5"
                />
                <input
                    required
                    type="password"
                    placeholder="Password"
                    className="w-1/3 mt-8 [font-family:'Inter-Regular',Helvetica] font-normal text-[#818da2] text-[20px] tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] px-6 py-5"
                />

                <button
                    type="submit" 
                    className="mt-10 [font-family:'Inter-Medium',Helvetica] font-medium text-[#27416d] text-[30px] text-center tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] px-20 py-4"
                >
                    Log In
                </button>
            </form>
            <Link to="/signup" className="mt-8">
                <div className="[font-family:'Inter-Regular',Helvetica] font-normal text-[#27416d] text-[18px] text-center tracking-[0] leading-[normal]">
                    회원가입
                </div>
            </Link>
            
            {/* 좌측 상단 - 돌아가기 */}
            <BackToMain />
        </div>
    )
}