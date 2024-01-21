import React, { useState } from 'react'
import '../tailwind.css';

const FileUploadInput:React.FC=()=>{
    return <div className='flex flex-col justify-center bg-white w-1/3 max-w-[521px] h-[557px] rounded-[50px]'>
        <h1 className='text-[50px] font-semibold flex justify-center mt-4 '>FISH 보내기</h1>
        <label className='bg-[#F7FDFF] mt-3 m-9 h-full border border-[#879DB4] border-dashed rounded-[50px] flex flex-col justify-center items-center'>
            <input type='file' className='hidden'></input>
            <h1 className='text-[100px] '>🐟</h1>
            <h1 className='text-[30px]'>공유할 파일을</h1><h1 className='text-[30px]'>끌어주세요</h1>
        </label>
    </div>
   
}
export const MainPage:React.FC=()=>{
    const [userID,] = useState(null)

    return <main className='bg-[#E8FAFD] flex flex-col items-center justify-center'>
        <div className='flex flex-row items-end '>
            <h1 className='text-[75px] font-bold'>FISH</h1>
            <div className='flex flex-row m-4 font-sans text-[30px] font-medium'>
                <h1>:FI</h1><h1 className='mr-2 text-[#879DB4]'>le</h1>
                <h1>SH</h1><h1 className='text-[#879DB4]'>are</h1>
            </div>
        </div>
        <h1 className='text-[28px]'>당신의 파일을 책임질 파일 공유 서비스</h1>
        <div className='ml-auto text-[30px] mt-4 mr-10 font-semibold'>
            {userID ? <button className='rounded-3xl bg-white pl-3 pr-3 border border-white hover:border-[#27416D] transition-all'>LOG OUT</button>:
                <div className='flex flex-row gap-4'>
                    <button className='rounded-3xl bg-white pl-3 pr-3 border border-white hover:border-[#27416D] transition-all'>LOG IN</button>
                    <button className='rounded-3xl pl-3 pr-3 border border-[#E8FAFD] hover:border-[#27416D] transition-all'>SIGN UP</button>
                </div>
            }
        </div>
        <div className='flex flex-row gap-4 mt-7 w-full justify-center'>
            <FileUploadInput/>
            <button className='text-[50px] font-semibold flex justify-center items-center bg-white rounded-3xl w-1/3 max-w-[430px] h-[107px]'>FISH 받기</button>
        </div>
        
    </main>
}