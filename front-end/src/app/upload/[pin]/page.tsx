import React from 'react'
import { useParams } from 'react-router';
import { BackToMain } from '../../../interface/back.tsx';

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
        <div>
            <div className="left-[520px] bg-[#e8fafd] absolute w-[62px] h-[62px] top-[181px]" />
            <div className="left-[529px] absolute w-[43px] h-[34px] top-[194px] [font-family:'Inter-Bold',Helvetica] font-bold text-black text-[30px] text-center tracking-[0] leading-[normal] whitespace-nowrap">
                {firstPin}
            </div>
            <div className="left-[600px] bg-[#e8fafd] absolute w-[62px] h-[62px] top-[181px]" />
            <div className="left-[609px] absolute w-[43px] h-[34px] top-[194px] [font-family:'Inter-Bold',Helvetica] font-bold text-black text-[30px] text-center tracking-[0] leading-[normal] whitespace-nowrap">
                {secondPin}
            </div>
            <div className="left-[680px] bg-[#e8fafd] absolute w-[62px] h-[62px] top-[181px]" />
            <div className="left-[689px] absolute w-[43px] h-[34px] top-[194px] [font-family:'Inter-Bold',Helvetica] font-bold text-black text-[30px] text-center tracking-[0] leading-[normal] whitespace-nowrap">
                {thirdPin}
            </div>
            <div className="left-[760px] bg-[#e8fafd] absolute w-[62px] h-[62px] top-[181px]" />
            <div className="left-[769px] absolute w-[43px] h-[34px] top-[194px] [font-family:'Inter-Bold',Helvetica] font-bold text-black text-[30px] text-center tracking-[0] leading-[normal] whitespace-nowrap">
                {fourthPin} 
            </div>
            <div className="left-[840px] bg-[#e8fafd] absolute w-[62px] h-[62px] top-[181px]" />
            <div className="left-[849px] absolute w-[43px] h-[34px] top-[194px] [font-family:'Inter-Bold',Helvetica] font-bold text-black text-[30px] text-center tracking-[0] leading-[normal] whitespace-nowrap">
                {fifthPin}
            </div>
            <div className="left-[920px] bg-[#e8fafd] absolute w-[62px] h-[62px] top-[181px]" />
            <div className="left-[929px] absolute w-[43px] h-[34px] top-[194px] [font-family:'Inter-Bold',Helvetica] font-bold text-black text-[30px] text-center tracking-[0] leading-[normal] whitespace-nowrap">
                {sixthPin}
            </div>
        </div>
    )
}

export const AfterUploadPage:React.FC=()=>{
    const { pin } = useParams<AfterUploadPageParams>()
    const realPin = '123456'; //백이랑 연결할 때 수정해야 함

    return (
        <div className="bg-white flex flex-row justify-center w-full">
            <div className="bg-white overflow-auto w-[1512px] h-[982px] relative">
                {/* title */}
                <div className="w-[514px] h-[80px] top-[8px] left-[497px] font-bold text-[50px] text-center absolute [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">
                    FISH가 만들어졌어요!
                </div>

                <div className="w-[514px] h-[60px] top-[100px] left-[497px] font-bold text-[30px] text-center absolute [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">
                    핀번호나 QR코드로 공유해요
                </div>

                {/* 핀번호 */}
                <PinNumber pin={realPin} />
                
                {/* QR코드 자리 */}
                <div className="flex items-center justify-center absolute w-[357px] h-[318px] top-[260px] left-[575px] rounded-[50px] border border-7 border-black">
                    <div className="flex items-center justify-center absolute w-[210px] h-[220px] [font-family:'Inter-Bold',Helvetica] font-bold text-black text-[100px] text-center tracking-[0] leading-[normal] whitespace-nowrap">
                        QR
                        <br />
                        코드
                    </div>
                </div>

                <button className="absolute w-[151px] h-[67px] top-[607px] left-[590px] bg-[#e8fafd] rounded-[25px]">
                    <div className="w-[112px] h-[64px] top-[12px] left-[20px] text-[#27416dcc] text-[25px] absolute [font-family:'Inter-Bold',Helvetica] font-bold text-center tracking-[0] leading-[normal]">
                        다운로드
                    </div>
                </button>
                <button className="absolute w-[151px] h-[67px] top-[607px] left-[760px] bg-[#e8fafd] rounded-[25px]">
                    <div className="w-[112px] h-[64px] top-[12px] left-[20px] text-[#27416dcc] text-[25px] absolute [font-family:'Inter-Bold',Helvetica] font-bold text-center tracking-[0] leading-[normal]">
                        공유하기
                    </div>
                </button>

                <div className="w-[286px] h-[57px] top-[703px] left-[608px] text-[#27416d] text-[30px] absolute [font-family:'Inter-Bold',Helvetica] font-bold text-center tracking-[0] leading-[normal]">
                    혹은
                    <br />
                    링크를 공유해요
                </div>

                <div className="absolute w-[445px] h-[63px] top-[802px] left-[530px] bg-[#e8fafd] rounded-[25px]">
                    <button className="absolute w-[445px] h-[62px] top-[5px] left-0 [font-family:'Inter-Bold',Helvetica] font-bold text-[#27416d] text-[30px] text-center tracking-[0] leading-[normal]">
                        https://fish.me/123456
                    </button>
                </div>
                
                {/* 좌측 상단 - 돌아가기 */}
                <BackToMain />
            </div>
        </div>
    )
}