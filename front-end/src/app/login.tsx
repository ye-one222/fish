import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { BackToMain } from '../interface/back.tsx'

export const LoginPage:React.FC=()=>{
    const [ id, setId ] = useState<string|null>(null);
    const [ pw, setPw ] = useState<string|null>(null);
    const [ isValid, setIsValid ] = useState<boolean>(false);
    const [ isLogin, setIsLogin ] = useState<boolean>(false);
    const navigate = useNavigate();

    const handleIDInput = (e) => {
        setId(e.target.value);
    }
    const handlePwInput = (e) => {
        setPw(e.target.value);
    }

    useEffect(() => {
        if(id!==null && pw!== null) {
            setIsValid(true);
        }else {
            setIsValid(false);
        }
    }, [id, pw])

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
            if(response.status===200) {
                response.json().then((data) => {
                console.log(data);
                localStorage.setItem("fish-login-token",data.Authorization);
                })
                setIsLogin(true);
                alert(`Hi ${id}`);
                navigate(-1);
            }
            else {
                alert("잘못된 ID 또는 비밀번호를 입력하였습니다!");
            }
        })
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
                    disabled={!isValid}
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