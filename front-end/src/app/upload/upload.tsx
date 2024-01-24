import React from 'react'
import '../../tailwind.css';

export const UploadPage:React.FC=()=>{
    return (
    <div className="bg-white flex flex-row justify-center w-full">
        <div className="bg-white overflow-auto w-[1512px] h-[982px] relative">
            {/* title */}
            <div className="w-[514px] h-[138px] top-[8px] left-[497px] font-bold text-[50px] text-center absolute [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">
                새로운 FISH 만들기
            </div>

            {/* 왼쪽 박스 */}
            <div className="absolute w-[521px] h-[592px] top-[221px] left-[181px] bg-[#e7fafc] rounded-[50px]">
                <div className="absolute w-[451px] h-[414px] top-[33px] left-[35px] bg-[#f7fdff] rounded-[50px] border border-dashed border-[#27416d]">
                    <div className="absolute w-[400px] h-[61px] top-[39px] left-[28px]">
                        <div className="relative w-[394px] h-[61px] bg-[#cdf1f7] rounded-[50px]">
                            <div className="absolute w-[303px] h-[61px] top-0 left-[18px]">
                                <div className="w-[301px] h-[40px] top-3 left-[2px] font-bold text-[#000000cc] text-right absolute [font-family:'Inter',Helvetica] text-[30px] tracking-[0] leading-[normal]">
                                    Lorem Ipsum.jpg
                                </div>
                                <div className="w-[49px] h-[48px] top-[6px] left-0 bg-[#d9d9d9] absolute rounded-[50px]" />
                                <div className="absolute w-[47px] h-[21px] top-[20px] left-[2px] [font-family:'Inter',Helvetica] font-bold text-[#8a8282] text-[16px] text-center tracking-[0] leading-[normal]">
                                    jpg
                                </div>
                            </div>
                            <div className="absolute h-[36px] top-[13px] left-[354px] [font-family:'Inter',Helvetica] font-bold text-[#ff00004c] text-[30px] text-right tracking-[0] leading-[normal] whitespace-nowrap">
                                X
                            </div>
                        </div>
                    </div>
                    <div className="w-[232px] h-[57px] top-[310px] left-[107px] font-normal text-[#27416d5e] text-[30px] text-center absolute [font-family:'Inter',Helvetica] tracking-[0] leading-[normal]">
                        공유할 파일을 <br />
                        끌어주세요
                    </div>
                    <img className="absolute w-[158px] h-[158px] top-[132px] left-[146px]" alt="Image" src="/img/image.png" />
                </div>
                <button className="flex absolute w-[435px] h-[94px] top-[478px] left-[39px]">
                    <div className="w-[435px] h-[86px] top-[2px] left-0 bg-[#f7fdff] absolute rounded-[50px]" />
                    <div className="flex items-center justify-center w-[232px] h-[86px] top-0 left-[92px] font-medium text-[#27416d5e] text-[30px] text-center absolute [font-family:'Inter',Helvetica] tracking-[0] leading-[normal]">
                        파일 선택
                    </div>
                </button>                
            </div>

            {/* 오른쪽 박스 */}
            <div className="absolute w-[647px] h-[129px] top-[213px] left-[782px]">
                <div className="absolute w-[237px] h-[86px] top-5 left-[9px] [font-family:'Inter',Helvetica] font-medium text-[#27416d] text-[30px] tracking-[0] leading-[normal]">
                    FISH 명
                </div>
                <div className="w-[128px] h-[45px] top-[36px] left-[519px] font-medium text-[20px] absolute [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">
                    선택사항
                </div>
                <div className="w-[601px] h-[60px] top-[69px] left-0 bg-[#e8fafd] absolute rounded-[50px]" />
                <div className="w-[602px] h-[40px] top-[79px] left-[34px] font-medium text-[#0000005e] absolute [font-family:'Inter',Helvetica] text-[30px] tracking-[0] leading-[normal]">
                    Lorem Ipsum
                </div>
            </div>

            <div className="absolute w-[647px] h-[129px] top-[362px] left-[782px]">
                <div className="w-[128px] h-[45px] top-[36px] left-[519px] font-medium text-[20px] absolute [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">
                    선택사항
                </div>
                <div className="absolute w-[237px] h-[86px] top-5 left-[9px] [font-family:'Inter',Helvetica] font-medium text-[#27416d] text-[30px] tracking-[0] leading-[normal]">
                    암호 설정
                </div>
                <div className="w-[601px] h-[60px] top-[69px] left-0 bg-[#e8fafd] absolute rounded-[50px]" />
                <div className="w-[602px] h-[40px] top-[79px] left-[34px] font-medium text-[#0000005e] text-[30px] absolute [font-family:'Inter',Helvetica] tracking-[0] leading-[normal]">
                    ●●●●●
                </div>
            </div>

            <div className="absolute w-[184px] h-[123px] top-[690px] left-[791px]">
                <div className="w-[131px] h-[78px] top-5 left-[25px] font-medium text-[30px] absolute [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">
                    유효기간
                </div>
                <div className="w-[184px] h-[59px] top-[64px] left-0 bg-[#e8fafd] absolute rounded-[50px]" />
                <div className="absolute w-[112px] h-[42px] top-[71px] left-[35px] [font-family:'Inter',Helvetica] font-medium text-[#0000005e] text-[30px] tracking-[0] leading-[normal]">
                    1 시간
                </div>
                <img className="absolute w-[35px] h-[25px] top-[83px] left-[125px]" alt="Polygon" src="/img/polygon-1.svg" />
            </div>

            <button className="absolute w-[313px] h-[89px] top-[732px] left-[1064px]">
                <div className="flex justify-center items-center relative w-[311px] h-[89px] top-[2px] left-0 bg-[#E8FAFD] rounded-[50px]">
                    <div className="flex justify-center items-center h-[61px] top-[14px] left-[87px] font-bold text-[#27416dcc] text-[50px] text-center absolute [font-family:'Inter',Helvetica] tracking-[0] leading-[normal]">
                        만들기
                    </div>
                </div>
            </button>
            
            {/* 좌측 상단 - 돌아가기 */}
            <div className="absolute w-[604px] h-[124px] top-[-17px] left-[-142px]">
                <div className="absolute w-[29px] h-[50px] top-[40px] left-[163px]">
                    <div className="h-[50px]">
                        <div className="w-[29px] h-[50px]">
                            <div className="h-[50px]">
                            <div className="relative w-[29px] h-[53px] top-[-3px]">
                                <img className="h-[30px] top-0 absolute w-[29px] left-0" alt="Line" src="/img/line-1.svg" />
                                <img className="h-[29px] top-[24px] absolute w-[29px] left-0" alt="Line" src="/img/line-2.svg" />
                            </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="w-[604px] h-[124px] top-7 left-0 font-bold text-[60px] text-center absolute [font-family:'Inter',Helvetica] text-[#27416d] tracking-[0] leading-[normal]">
                    FISH
                </div>
                <p className="absolute w-[136px] h-[67px] top-[66px] left-[382px] [font-family:'Inter',Helvetica] font-medium text-transparent text-[20px] tracking-[0] leading-[normal]">
                    <span className="text-[#27416d]">: FI</span>
                    <span className="text-[#27416d80]">le</span>
                    <span className="text-[#27416d]"> SH</span>
                    <span className="text-[#27416d80]">are</span>
                </p>
            </div>
        </div>
    </div>
    )
}