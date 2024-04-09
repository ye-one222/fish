import React, { useEffect, useState } from "react"
import { BackToMain } from "../../../interface/back.tsx"
import { SearchPage } from "../search.tsx"
import { fishesType } from "../../../interface/fishesType.tsx"

interface PinNumType {
    pinNum: string,
}

export const DownLoadPage:React.FC<PinNumType> = ({ pinNum }) => {
    const [ pinState, setPinState ] = useState<string>('PWERROR')
    const [ fishPw, setFishPw ] = useState<string>('')
    const [ theFish, setTheFish ] = useState<fishesType>({
        fishName: '',
        fileDTOList: [],
        fileCount: 0,
        userId: null
    })  //초기화

    useEffect(()=> {
        setTheFish({...theFish, fishName:'내 이미지들이야', 
            fileDTOList:[{originalFileName: '첫번째 사진.jpg', fileSize: 500000},
            {originalFileName: '두번째 사진.png', fileSize:100000}],
            fileCount: 2})
    },[theFish])
    
    const stateCheckFunc = () => {
        /*fetch(`http://localhost:8080/fishes/${pinNum}`, {
            method: 'get',
            headers: {
                //"Authorization":`Bearer ${localStorage.getItem("login-token")}`,
                "Content-Type":"application/json; charset=utf-8"
            },
            body: JSON.stringify({
                "fishPassword": fishPw
            })
        })
        .then(res => { 
            switch(res.status){
                case 200:
                    setPinState('SUCESS')
                    break;
                case 404:
                    setPinState('NOTFIND')
                    alert("핀 번호에 해당하는 FISH가 없습니다")
                    break;
                case 401:
                    setPinState('PWERROR')
                    break;
                case 400:
                    console.log("리퀴오류임 니가고치셈")
                    break;
            }
            return res.json()
        })
        .then(data => { console.log('data = '+ data); 
            if( pinState === 'SUCESS' ){
                //???이거 되는지 확인!!!!!!!! --- 핀번호로 fish검색api
                setTheFish({...theFish, fishName: data.fileName, 
                    fileDTOList:{  },
                    fileCount: data.fileCount});
                console.log('theFish = '+ theFish);
            }
        })*/
    }

    useEffect(() => { stateCheckFunc() })
   
    const handlePwInput = (e) => {
        setFishPw(e.target.value);
    }

/*///////////////////
 return 시작
//////////////////*/

    if( pinState === 'SUCESS' ){

        return <main className="flex flex-col justify-center items-center">
            <header>
                <BackToMain />
                <h1 className=" m-[5rem] font-bold text-[50px] ">{theFish.fishName}</h1>
            </header>
            <div className="grid grid-cols-2 w-full justify-center">
                {theFish.fileDTOList.map((each)=>{
                    return <section className="flex flex-col justify-center items-center">
                        <p >{each.originalFileName}</p>
                        <p className="">{each.fileSize}</p>
                    </section>
                })}
            </div>
        </main>
    }

    if( pinState === 'NOTFIND' ){
        return <SearchPage />
    }
    
    if( pinState === 'PWERROR'){
        return <main className="flex flex-col justify-center items-center h-full">
        <header className="h-1/3">
            <BackToMain />
            <h1 className="m-[50px] font-bold text-[50px] ">FISH 받기</h1>
        </header>
        <section className="flex flex-col items-center justify-between w-full h-2/3 pt-5 pb-[200px]">
            <div className="flex flex-col items-center w-full">
                <h1 className="text-[30px]">그물 암호를 입력하세요</h1>
                <input 
                type="text" 
                onChange={ handlePwInput }
                placeholder='그물 암호' 
                className="placeholder:text-zinc-400 text-xl bg-[#E8FAFD] p-3 rounded-[50px] w-1/3 h-[66px] focus: outline-none"/>
            </div>
            <button 
            onClick={ () => { /*stateCheckFunc()*/ console.log(fishPw); setPinState('SUCESS'); }}
            className='bg-[#E8FAFD] rounded-[50px] text-[30px] w-[196px] h-[90px] border border-[#EFFCFE] hover:border-[#27416d] transition-all'>
                받기</button>
        </section>
    </main>
    }
    
}