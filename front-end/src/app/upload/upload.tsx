import React, { useCallback, useRef, useState } from 'react'
import '../../tailwind.css';
import { BackToMain } from '../../interface/back.tsx';
import { Link } from 'react-router-dom';

interface UploadFileProps {
    name: string;
}

// 한 번만 사용되는데 빼는게 나으려나
const UploadedFile:React.FC<UploadFileProps> = ( {name} )=>{
    return (
        <div className="flex items-center justify-between w-full h-[40px] p-2 bg-[#cdf1f7] rounded-[50px]">
            <div className='flex items-center'>
                <div className='text-[25px] mr-1'>🐟</div>
                <div className='font-bold text-[#27416d]'>{name}</div>
            </div>
            <button className="text-[23px] font-bold text-[#27416d] hover:text-red-600">X</button>
        </div>
    )
}

{/*
// 아마 안쓸듯
const CreateFishForm = () => {
    const durations = ['30분', '1시간', '2시간', '4시간', '6시간', '12시간'];
    return (
        <form action="http://localhost:8080/gmool/" method="post" encType="multipart/form-data" className="flex flex-col">
            <div className="flex flex-row justify-between mt-12">
                <div className="[font-family:'Inter',Helvetica] font-bold text-[#27416d] text-[28px] tracking-[0] leading-[normal]">
                    FISH 명
                </div>
                <div className="mt-4 font-medium text-[15px] [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">
                    선택사항
                </div>
            </div>
            <input
            required
            type="text"
            name="gmoolName"
            className="w-full m-2 [font-family:'Inter-Regular',Helvetica] font-normal text-[#818da2] text-[20px] tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] px-6 py-5"
            />

            <div className="flex flex-row justify-between mt-8">
                <div className="[font-family:'Inter',Helvetica] font-bold text-[#27416d] text-[28px] tracking-[0] leading-[normal]">
                    암호 설정
                </div>
                <div className="mt-4 font-medium text-[15px] [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">
                    선택사항
                </div>
            </div>
            <input
            required
            type="password"
            name="password"
            className="w-full m-2 [font-family:'Inter-Regular',Helvetica] font-normal text-[#818da2] text-[20px] tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] px-6 py-5"
            />

            <div className="flex flex-row items-start mt-20 gap-10">
                <div className="flex flex-col">
                    <div className="[font-family:'Inter',Helvetica] font-bold text-[#27416d] text-[20px] tracking-[0] leading-[normal]">
                        유효기간
                    </div>
                    <select name="dueMinute" className="w-full bg-[#E8FAFD] m-2 [font-family:'Inter-Regular',Helvetica] font-normal text-[#818da2] text-[20px] rounded-[50px] px-6 py-5">
                        {durations.map((duration, index) => {
                            return (
                                <option key={index} value={index}>{duration}</option>
                            )
                        })}
                    </select>
                </div>
                
                <Link to="/upload/pin">
                <button
                    type="submit" 
                    className="mt-[32px] [font-family:'Inter-Medium',Helvetica] font-bold text-[#27416d] text-[30px] text-center tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] px-9 py-3"
                >
                    만들기
                </button>
                </Link>
            </div>
        </form>
    )
}
*/}

interface UploadProps {
    file: File;
}

export const UploadPage:React.FC<UploadProps> = ( {file} )=>{
    //const firstFileName = file.name;
    const firstFileName = 'pull받아서 위에걸로 하면됨'
    const fileInput = useRef<HTMLInputElement | null>(null);
    const [ fileList, setFileList ] = useState<File[]>([]); //main에서 넘어온 파일정보는 배열에 추가 안함 -> 수정 필요
    const fileCnt = useRef(1);
    const durations = [30, 60, 120, 240, 360, 720]; //분 기준 / 나중에 1시간->60분으로 변환하는 함수 만들면 더 좋을듯
    const [ active, setActive ] = useState(false);
    const dataTransfer = new DataTransfer();
  
    const handleButtonClick = e => {
        if (fileInput.current) {
            fileInput.current.click();
        }
    }

    const handleChange = e => {
        setFileList(prevFileList => {
            const filesArray: File[] = Array.from(e.target.files); // 새로운 파일 목록을 배열로 변환
            return [...prevFileList, ...filesArray]; // 이전 파일 목록과 새로운 파일들을 합친 새로운 배열 반환
        });
        fileCnt.current++;

        for (let i=0; i<fileList.length; i++) {
            dataTransfer.items.add(fileList[i]);
        }

        const files = e.target.files;
        for (let i = 0; i < files.length; i++) {
            dataTransfer.items.add(files[i]);
        }
        console.log(dataTransfer);
    }

    const handleDelete = ( name ) => {
        setFileList(fileList.filter(file => file.name !== name));
        fileCnt.current--;
    }

    const onDropFiles = (e) => {
        //console.log({ e }, e.dataTransfer.files);
        e.preventDefault();
        setActive(false);

        setFileList(prevFileList => {
            const filesArray: File[] = Array.from(e.dataTransfer.files);
            return [...prevFileList, ...filesArray];
        });
        fileCnt.current++;

        for (let i=0; i<fileList.length; i++) {
            dataTransfer.items.add(fileList[i]);
        }

        const files = e.dataTransfer.files;
        for (let i = 0; i < files.length; i++) {
            dataTransfer.items.add(files[i]);
        }
        console.log(dataTransfer);
    };

    const handleDragOn = (e) => {
        e.preventDefault();
        e.stopPropagation();
        setActive(true);
    }

    return (
        <div className="bg-white h-full flex flex-col items-center">
            {/* title */}
            <div className="flex justify-center mt-8 font-bold text-[50px] text-center [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">
                새로운 FISH 만들기
            </div>
            
            <form action="http://localhost:8080/fishes" method="post" encType="multipart/form-data" className="flex flex-row w-full h-full justify-center gap-20">
                {/* 왼쪽 박스 */}
                <div className="flex flex-col items-center h-2/3 w-1/3 max-w-[521px] max-h-[592px] mt-10 p-6 bg-[#e7fafc] rounded-[50px] gap-5">
                    <div className={`w-full h-3/4 flex flex-col items-center bg-[#f7fdff] rounded-[50px] border-2 border-dashed ${active?"border-[#27416D]":"border-[#879DB4]"}`}
                        onDragEnter={ handleDragOn }
                        onDragOver={ handleDragOn }
                        onDrop={ onDropFiles }
                        >
                        <div className="w-full p-5 overflow-y-auto">
                            {/* 파일들 - 일단 최소한 1개는 고정으로 있음(main에서 넘어온거) */}
                            <UploadedFile name={firstFileName}/>
                            {fileList.map((file, index) => {
                                return (
                                    <div key={index} className="flex items-center justify-between w-full h-[40px] p-2 bg-[#cdf1f7] rounded-[50px] mt-2">
                                        <div className='flex items-center'>
                                            <div className='text-[25px] mr-1'>🐟</div>
                                            <div className='font-bold text-[#27416d]'>{file.name}</div>
                                        </div>
                                        <button className="text-[23px] font-bold text-[#27416d] hover:text-red-600" onClick={ () => {handleDelete(file.name)} }>X</button>
                                    </div>
                                )
                            })}
                        </div>
                        {fileCnt.current <= 1 && (
                            <div className="flex flex-col items-center justify-center">
                                <img className="w-[128px] h-[128px]" alt="Image" src="/img/image.png" />
                                <div className="font-normal text-[#27416d5e] text-[18px] text-center [font-family:'Inter',Helvetica] tracking-[0] leading-[normal]">
                                    공유할 파일을 <br />
                                    끌어주세요
                                </div>
                            </div>
                        )}
                    </div>
                    <button 
                        className="flex w-1/2 max-w-[500px] items-center justify-center bg-[#f7fdff] font-medium text-[#27416d5e] text-[28px] text-center [font-family:'Inter',Helvetica] tracking-[0] leading-[normal] rounded-[50px] p-5 border border-transparent hover:border-[#27416d] hover:text-[#27416d]"
                        onClick={handleButtonClick}>
                        파일 선택
                    </button> 
                    <input
                        multiple
                        type = "file"
                        name="files"
                        className='hidden'
                        ref={fileInput}
                        onChange={handleChange}/>      
                </div>

                {/* 오른쪽 박스 */}
                <div className="flex flex-col">
                    <div className="flex flex-row justify-between mt-12">
                        <div className="[font-family:'Inter',Helvetica] font-bold text-[#27416d] text-[28px] tracking-[0] leading-[normal]">
                            FISH 명
                        </div>
                        <div className="mt-4 font-medium text-[15px] [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">
                            선택사항
                        </div>
                    </div>
                    <input
                        required
                        type="text"
                        name="fishName"
                        className="InputBoxCSS"
                    />

                    <div className="flex flex-row justify-between mt-8">
                        <div className="[font-family:'Inter',Helvetica] font-bold text-[#27416d] text-[28px] tracking-[0] leading-[normal]">
                            암호 설정
                        </div>
                        <div className="mt-4 font-medium text-[15px] [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">
                            선택사항
                        </div>
                    </div>
                    <input
                        required
                        type="password"
                        name="password"
                        className="InputBoxCSS"
                    />

                    <div className="flex flex-row items-start mt-20 gap-1">
                        <div className="flex flex-col">
                            <div className="[font-family:'Inter',Helvetica] font-bold text-[#27416d] text-[20px] tracking-[0] leading-[normal]">
                                유효시간 (분)
                            </div>
                            <input
                                required
                                type="number"
                                name="dueMinute"
                                className="w-1/2 InputBoxCSS"
                                />
                            {/*<select name="dueMinute" className="w-full bg-[#E8FAFD] m-2 [font-family:'Inter-Regular',Helvetica] font-normal text-[#818da2] text-[20px] rounded-[50px] px-6 py-5">
                                {durations.map((duration, index) => {
                                    return (
                                        <option key={index} value={index}>{duration}</option>
                                    )
                                })}
                            </select>*/}
                        </div>
            
                        <button
                            type="submit" 
                            className="mt-[32px] [font-family:'Inter-Medium',Helvetica] font-bold text-[#27416d] text-[30px] text-center tracking-[0] leading-[normal] bg-[#E8FAFD] rounded-[50px] px-12 py-5 border border-transparent hover:border-[#27416d]"
                            value="보내기"
                        >
                            만들기
                        </button>
                    </div>
                </div>
            </form>
            {/* 좌측 상단 - 돌아가기 */}
            <BackToMain />
        </div>
    )
}