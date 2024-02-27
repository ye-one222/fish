import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import { BackToMain } from '../interface/back.tsx'

export const LoginPage:React.FC=()=>{
    const [ id, setId ] = useState<string|null>(null);
    const [ pw, setPw ] = useState<string|null>(null);

    const handleIDInput = (e) => {
        setId(e.target.value);
    }
    const handlePwInput = (e) => {
        setPw(e.target.value);
    }

    const submitHandler = () => {
        fetch('http://localhost:8080/users/login', {
            method: 'post',
            headers: {
                "Content-Type":"application/json; charset=utf-8"
            },
            body: JSON.stringify({
                id: id,
                password: pw,
            })
        })
        .then((response) => {
            response.text();
            /*for(let [key,value] of response.headers){
                console.log(`${key} : ${value}`);
            }
            response.headers.forEach(([key, value]) => console.log(`${key} : ${value}`));*/
            //console.log(Headers.headers.get("Authorization"));
        })
        .then((data) => {
            //if(data)
              //  console.log(data);
            /*if(data.id){
                //login success Let's get token
                console.log(data.token)
                //토큰 받아서 로컬 스트리지 or 세션 스토리지에 저장 + 로그아웃할때 취소
                localStorage.setItem("login-token",data.token)
                localStorage.setItem("user-id", data.id)
                //setLoginFin(true)
            }
            else{
                alert(data.message);
            }*/
        });
    }


    return (
        <div className="bg-white h-full flex flex-col items-center justify-center">
            {/* title */}
            <div className="flex items-center [font-family:'Inter-SemiBold',Helvetica] font-semibold text-[#27416d] text-[70px] text-center tracking-[0] leading-[normal]">
                Let's go catch fish
            </div>

            {/* login form */}
            <div className="flex flex-col w-full items-center justify-center">
                <input
                    required
                    name="id"
                    type="text"
                    placeholder="ID"
                    className="w-1/3 mt-20 [font-family:'Inter-Regular',Helvetica] font-normal text-[#818da2] text-[20px] tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] px-6 py-5"
                    onChange={ handleIDInput }
                />
                <input
                    required
                    name="password"
                    type="password"
                    placeholder="Password"
                    className="w-1/3 mt-8 [font-family:'Inter-Regular',Helvetica] font-normal text-[#818da2] text-[20px] tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] px-6 py-5"
                    onChange={ handlePwInput }
                />

                <button
                    className="mt-10 [font-family:'Inter-Medium',Helvetica] font-medium text-[#27416d] text-[30px] text-center tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] px-20 py-4"
                    onClick={ submitHandler }
                >
                    Log In
                </button>
            </div>
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