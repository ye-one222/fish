import React, { useEffect, useState } from 'react'
import { BackToMain } from '../interface/back.tsx'

export const SignupPage:React.FC=()=>{
    const [ firstName, setFirstName ] = useState<string|null>(null);
    const [ lastName, setLastName ] = useState<string|null>(null);
    const [ id, setId ] = useState<string|null>(null);
    const [ pw, setPw ] = useState<string|null>(null);
    const [ isValid, setIsValid ] = useState<boolean>(false);

    const handleFirstNameInput = (e) => {
        setFirstName(e.target.value);
    }
    const handleLastNameInput = (e) => {
        setLastName(e.target.value);
    }
    const handleIDInput = (e) => {
        setId(e.target.value);
    }
    const handlePwInput = (e) => {
        if(e.target.value.length > 7 && e.target.value.length < 16){
            setPw(e.target.value);
        }
        else{
            setPw(null);
        }
    }

    useEffect(()=>{
        if(firstName!==null && lastName !==null && id !== null && pw !== null){
            setIsValid(true);
        }else{
            setIsValid(false);
        }
    },[firstName, lastName, id, pw])

    const submitHandler = () => {
        fetch('http://localhost:8080/users/signup', {
            method: 'post',
            headers: {
                "Content-Type":"application/json; charset=utf-8"
            },
            body: JSON.stringify({
                id: id,
                password: pw,
                firstName: firstName,
                lastName: lastName,
            })
        })
        .then((response) => response.json())
        .then((data) => {console.log(data);});
    }

    return (
        <div className="bg-white h-full flex flex-col items-center justify-center">
            {/* title */}
            <div className="mb-[10px] flex items-center [font-family:'Inter-SemiBold',Helvetica] font-semibold text-[#27416d] text-[50px] text-center tracking-[0] leading-[normal]">
                정보를 입력해 주세요
            </div>

            {/* signup form */}
            <div className="flex flex-col items-center justify-center">
                <div className="flex flex-row">
                    {/* first name */}
                    <div className="flex flex-col items-center">
                        <div className="mt-[35px] mr-2 font-medium text-[20px] [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">First Name</div>
                        <input
                            required
                            name="firstName"
                            type="text"
                            className="mr-2 [font-family:'Inter-Regular',Helvetica] font-normal text-[#818da2] text-[20px] tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] px-5 py-4"
                            onChange={ handleFirstNameInput }
                        />
                    </div>

                    {/* last name */}
                    <div className="flex flex-col items-center">
                        <div className="mt-[35px] ml-2 font-left text-[20px] [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">Last Name</div>
                        <input
                            required
                            name="lastName"
                            type="text"
                            className="ml-2 [font-family:'Inter-Regular',Helvetica] font-normal text-[#818da2] text-[20px] tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] px-5 py-4"
                            onChange={ handleLastNameInput }
                        />
                    </div>
                </div>

                {/* ID - 중복 확인 필요*/}
                <div className="w-full mt-[25px] ml-[40px] font-left text-[20px] [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">ID</div>
                <input
                    required
                    name="id"
                    type="text"
                    className="w-full [font-family:'Inter-Regular',Helvetica] font-normal text-[#818da2] text-[20px] tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] px-5 py-4"
                    onChange={ handleIDInput }
                />

                {/* PW */}
                <div className="flex flex-row justify-between w-full mt-[25px] ml-[40px] pr-8 font-left [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">
                    <div className="text-[20px]">Password</div>
                    <div className="text-[17px]">{pw===null?"(8자리 이상, 15자리 이하)":"OK!"}</div>
                </div>
                <input
                    required
                    name="password"
                    type="password"
                    className="w-full [font-family:'Inter-Regular',Helvetica] font-normal text-[#818da2] text-[20px] tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] px-5 py-4"
                    onChange={ handlePwInput }
                />

                <button
                    className={`mt-[40px] [font-family:'Inter-Medium',Helvetica] font-medium text-[30px] text-center tracking-[0] leading-[normal] rounded-[50px] px-20 py-4 border border-transparent ${isValid?"bg-[#E8FAFD] hover:border-[#27416D] text-[#27416d] ":"bg-gray-100 hover:border-gray-600 text-gray-600 "}`}
                    disabled={!isValid}
                    onClick={ submitHandler }
                >
                    Sign Up
                </button>
            </div>

            
            {/* 좌측 상단 - 돌아가기 */}
            <BackToMain />
        </div>
    )
}