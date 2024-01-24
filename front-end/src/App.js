
import './App.css';

import React from 'react';
import { MainPage } from './app/main.tsx';
import { UploadPage } from './app/upload/upload.tsx';
import { LoginPage } from './app/login.tsx';
import { SignupPage } from './app/signup.tsx';
import { AfterUploadPage } from './app/upload/[pin]/page.tsx';

import './tailwind.css';

function App() {
  return (
    <div className="MainCSS">
        <MainPage/>
    </div>
  );
}

export default App;