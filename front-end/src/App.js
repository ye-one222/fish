import React from 'react';
import { MainPage } from './app/main.tsx';
import { UploadPage } from './app/upload.tsx';
import { LoginPage } from './app/login.tsx';
import { SignupPage } from './app/signup.tsx';

import './tailwind.css';

function App() {
  return (
    <div className="App">
        <MainPage/>
    </div>
  );
}

export default App;