import React from 'react'
import { useParams } from 'react-router';
import { BackToMain } from '../../../interface/back.tsx';
import QRCode from 'qrcode.react';
import { CopyToClipboard } from "react-copy-to-clipboard";

// 핀번호를 파라미터로 받아와야 하는데 아직 라우터가 안 돼서.. 나중에 해야할듯

interface PinNumberProps {
    pin: string;
}  

type AfterUploadPageParams = {
    pin: string;
}

const PinNumber: React.FC<PinNumberProps> = ({pin}) => {
    const firstPin = pin[0];
    const secondPin = pin[1];
    const thirdPin = pin[2];
    const fourthPin = pin[3];
    const fifthPin = pin[4];
    const sixthPin = pin[5];

    return (
        <div className="flex gap-2">
            <div className="flex items-center justify-center w-[43px] h-[34px] [font-family:'Inter-Bold',Helvetica] font-bold text-black text-[30px] text-center tracking-[0] leading-[normal] whitespace-nowrap bg-[#e8fafd] p-7">
                {firstPin}
            </div>
            <div className="flex items-center justify-center w-[43px] h-[34px] [font-family:'Inter-Bold',Helvetica] font-bold text-black text-[30px] text-center tracking-[0] leading-[normal] whitespace-nowrap bg-[#e8fafd] p-7">
                {secondPin}
            </div>
            <div className="flex items-center justify-center w-[43px] h-[34px] [font-family:'Inter-Bold',Helvetica] font-bold text-black text-[30px] text-center tracking-[0] leading-[normal] whitespace-nowrap bg-[#e8fafd] p-7">
                {thirdPin}
            </div>
            <div className="flex items-center justify-center w-[43px] h-[34px] [font-family:'Inter-Bold',Helvetica] font-bold text-black text-[30px] text-center tracking-[0] leading-[normal] whitespace-nowrap bg-[#e8fafd] p-7">
                {fourthPin}
            </div>
            <div className="flex items-center justify-center w-[43px] h-[34px] [font-family:'Inter-Bold',Helvetica] font-bold text-black text-[30px] text-center tracking-[0] leading-[normal] whitespace-nowrap bg-[#e8fafd] p-7">
                {fifthPin}
            </div>
            <div className="flex items-center justify-center w-[43px] h-[34px] [font-family:'Inter-Bold',Helvetica] font-bold text-black text-[30px] text-center tracking-[0] leading-[normal] whitespace-nowrap bg-[#e8fafd] p-7">
                {sixthPin}
            </div>
        </div>
    )
}

export const AfterUploadPage:React.FC=()=>{
    const { pin } = useParams<AfterUploadPageParams>()
    const realPin = '123456'; //백이랑 연결할 때 수정해야 함
    const url = 'https://fish.me/123456';

    return (
        <div className="bg-white h-full flex flex-col items-center overflow-y-auto">
            {/* title */}
            <div className="w-[514px] h-[80px] flex justify-center mt-8 font-bold text-[50px] text-center [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">
                FISH가 만들어졌어요!
            </div>

            <div className="w-[514px] h-[60px] flex justify-center font-bold text-[30px] text-center [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">
                핀번호나 QR코드로 공유해요
            </div>

            {/* 핀번호 */}
            <div className="mt-4">
                <PinNumber pin={realPin} />
            </div>
            
            {/* QR코드 자리 */}
            <div className="flex items-center justify-center min-w-[200px] min-h-[200px] rounded-[25px] mt-4 border-4 border-[#e8fafd]">
                <QRCode value={JSON.stringify(url)}/>
            </div>

            <div className="flex mt-2 gap-4">
                {/*<button className="w-[151px] h-[67px] bg-[#e8fafd] rounded-[25px] [font-family:'Inter-Bold',Helvetica] font-bold text-center tracking-[0] leading-[normal] text-[#27416dcc] text-[25px]">
                    다운로드
                </button>*/}
            </div>

            <div className="mt-6 w-[286px] h-[57px] text-[#27416d] text-[30px] [font-family:'Inter-Bold',Helvetica] font-bold text-center tracking-[0] leading-[normal]">
                혹은
                <br />
                링크를 공유해요
            </div>
            
            {/* 이걸 누르면 바로 copy 되게 구현? */}
            <CopyToClipboard text={url} onCopy={()=>alert("주소가 복사되었습니다!")}>
                <button className="mt-10 w-[445px] h-[62px] bg-[#e8fafd] [font-family:'Inter-Bold',Helvetica] font-bold text-[#27416d] text-[30px] text-center tracking-[0] leading-[normal] rounded-[25px] border border-transparent hover:border-[#27416d]">
                    {url}
                </button>
            </CopyToClipboard>

            {/* 좌측 상단 - 돌아가기 */}
            <BackToMain />
        </div>
    )
}