import React from 'react'
import '../tailwind.css';

export const MainPage:React.FC=()=>{

    
    return<main style={{backgroundColor:'#E8FAFD'}} className='min-h-screen max-h-screen h-screen w-full flex items-center justify-center'>
        <div>
            <h1 style={{color: '#27416D',
                textAlign: 'center',
                fontSize: '75px',
                }}>FISH</h1>
        </div>
    </main>
}