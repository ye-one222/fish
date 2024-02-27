
import './App.css';

import React from 'react';
//import { MainPage } from './app/main.tsx';
//import { UploadPage } from './app/upload/upload.tsx';
//import { LoginPage } from './app/login.tsx';
//import { SignupPage } from './app/signup.tsx';
//import { AfterUploadPage } from './app/upload/[pin]/page.tsx';
//import { DownLoadPage } from './app/download.tsx';

import './tailwind.css';
import { FishRouter } from './router/index.tsx';
function App() {
/*
  useEffect(() => {
<<<<<<< HEAD
    fetch('http://localhost:8080/gmool/1/files')
=======
    fetch('http://localhost:8080/gmool')
>>>>>>> 7f394003702c05a2d082ec2c64de7021a01c7801
    .then((response) => response.json())
    .then((data) => console.log(data));
  }, []);
  //useEffect에 두번째 인자 안 넣으면 처음 한번만 실행 (나중에 리랜더링 되지않게)
*/
  return (
    <div className="MainCSS">
        <FishRouter />
    </div>
  );
}

export default App;