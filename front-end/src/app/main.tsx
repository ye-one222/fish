import React, { useEffect, useState } from 'react'
import '../tailwind.css';
import { Link, useNavigate } from 'react-router-dom';
import { UploadPage } from './upload/upload.tsx';

/*interface OneFishFile {
    oneFish: File;
    setOneFish: React.Dispatch<SetStateAction<File>>;
}*/
const EachFileShow:React.FC = (props) => {
    return <div>
        { props[1]<5 ? <button className='text-[20px]'>🐟</button>
            :(props[1] <10) ? <button className='text-[30px]'>🐠</button>
                :<button className='text-[50px]'>🐳</button>}
        <h1 className='text-[15px]'>{props[0]}</h1>
    </div>
}

const MyFishCom:React.FC<{id: string} > = ({ id }) => {
    const myFile = [["file1",8], ['file2',15], ['file3',1], ['file4',2]]
    
    return <div className='h-full bg-white rounded-[50px] mt-10'>
        <h1 className='text-[40px] font-semibold p-4 flex justify-center'>{`${id}의 FISH`}</h1>
        <div className=''>
            {myFile.map((each)=>(EachFileShow(each)))}
            <button>{}</button>
        </div>
    </div>
}

const FileUploadInput:React.FC<{ setOneFish:React.Dispatch<React.SetStateAction<File | undefined|null>> }>=({ setOneFish })=>{
    const [active, setActive] = useState(false)
    const navi = useNavigate();
    //const [ oneFish, setOneFish ] = useState<File>();

    const handleDragOn = (e) => {
        e.preventDefault();
        e.stopPropagation();
        setActive(true) 
    }
    const handleDragEnd = (e) =>{ 
        e.preventDefault();
        e.stopPropagation();
        setActive(false) 
    }
    
    const handleDrop = (e) => {
        e?.preventDefault();
        e.stopPropagation();
        console.log(e.dataTransfer.files[0])
        setOneFish(e.dataTransfer.files[0])
        
        setActive(false);
        //(<UploadPage file={e.dataTransfer.files[0]}/>)
    }
    const handleFileClick = (e) => {
        console.log(e.target.files[0])
        setOneFish(e.target.files[0])
    }

    return <div className='h-full flex flex-col justify-center bg-[#E8FAFD] w-1/3 max-w-[521px] rounded-[50px]'>
        <h1 className='text-[40px] flex justify-center m-4 '>FISH 보내기</h1>
        <label htmlFor="file"
        onDragEnter={ handleDragOn } //들어올 때
        onDragOver={ handleDragOn } //위에 있을 때
        onDragLeave={ handleDragEnd } 
        onDrop={ handleDrop }
        className={`mt-3 m-9 h-full border-2 border-dashed rounded-[50px] hover:border-[#27416D] flex flex-col
            ${active? 'border-[#27416D]':'border-[#879DB4]'} bg-[#F7FDFF] justify-center items-center`}> 
                <h1 className='text-[100px]'>🐟</h1>
                <h1 className='text-[30px]'>파일을 여기에</h1>
                <h1 className='text-[30px]'>끌어주세요</h1>
        </label>
        <input id="file" type='file' onChange={ handleFileClick } className='w-full hidden'/>         
    </div>
}

const DownButton:React.FC= () => {
    return (<div>
        <Link to={'/download'}>
            <button 
            className='text-[40px] flex w-full  h-[107px] justify-center items-center 
            bg-[#E8FAFD] rounded-[50px] border border-[#E8FAFD] hover:border-[#27416D] transition-all p-3'>
                FISH 받기</button>
        </Link>
    </div>)
}

export const MainPage:React.FC=()=>{
    const [ userID,setUserID ] = useState<string|null>('')
    const [ oneFish, setOneFish ] = useState<File|null>()
    
    const LOGINKEY = "fish-login-token";

    useEffect(() => {
        const token = localStorage.getItem(LOGINKEY);
        if (token) {
            const decodedToken = JSON.parse(atob(token.split('.')[1]));
            const userIdFromToken = decodedToken.id;
            setUserID(userIdFromToken);
        }
    }, []);

    function handleLogOutBtn() {
        localStorage.setItem(LOGINKEY, '');
        setUserID('');
    }
    
    if(oneFish){
        return <UploadPage file={oneFish}/>
    }else{
        return <div className='MainPageCSS'>
                <div className='flex flex-row items-end'>
                    <h1 className='text-[75px] font-bold'>FISH</h1>
                    <div className='flex flex-row m-4 font-sans text-[30px] font-medium'>
                        <h1>:FI</h1><h1 className='mr-2 text-[#879DB4]'>le</h1>
                        <h1>SH</h1><h1 className='text-[#879DB4]'>are</h1>
                    </div>
                </div>
                <h1 className='text-[28px]'>당신의 파일을 책임질 파일 공유 서비스</h1>
                <div className='font-semibold flex flex-row items-center justify-between w-full mb-32'>
                    {userID ? 
                        <button onClick={handleLogOutBtn} className='flex absolute left-3/4 text-[30px] rounded-3xl bg-[#E8FAFD] pl-3 pr-3 
                            border border-[#E8FAFD] hover:border-[#27416D] transition-all'>
                                LOG OUT</button>:
                            <div className=' grid-flow-row text-[30px] absolute left-3/4'>
                                <Link to={'/login'}>
                                    <button className='rounded-3xl bg-[#E8FAFD] mb-2 mr-2 pl-3 pr-3 
                                        border border-[#E8FAFD] hover:border-[#27416D] transition-all'>
                                            LOG IN</button>
                                </Link>
                                <Link to={'/signup'}>
                                    <button className='rounded-3xl pl-3 pr-3 border border-white hover:border-[#27416D] transition-all'>
                                    SIGN UP</button>
                                </Link>
                            </div>
                    }
                </div>
                <div className='flex flex-row gap-4 w-full justify-center h-[55%]'>
                    <FileUploadInput setOneFish={setOneFish} />
                    <div className='h-full flex flex-col w-1/3 max-w-[430px]'>
                        <DownButton/>
                        { userID && <MyFishCom id=''/> }

                    </div>
                </div>
                <h1 className='text-[150px] absolute bottom-0 right-3/4 -z-10'><img src="/img/coral.png" className="w-[350px]" alt='coral'/></h1>
                <h1 className='text-[100px] absolute bottom-0 left-3/4 -z-10'><img src="/img/coral.png" className="w-[200px]" alt='coral'/></h1>
            </div>
    }
    
}
