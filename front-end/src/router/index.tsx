import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import React from "react";
import { MainPage } from "../app/main.tsx"
import { DownLoadPage } from '../app/download.tsx';
import { LoginPage } from '../app/login.tsx';
import { SignupPage } from '../app/signup.tsx';
import { UploadPage } from '../app/upload/upload.tsx';
import { AfterUploadPage } from '../app/upload/[pin]/page.tsx';
//import "/node_modules/react-grid-layout/css/styles.css";
//import "/node_modules/react-resizable/css/styles.css";
//import { RouterProvider } from "react-router-dom";

export type FishRouterPath = '/' | '/download' 
const fishRouter = createBrowserRouter([
    {
        path: '/',
        element: <MainPage />,
    },
    {
        path:'/download',
        element: <DownLoadPage />,
    },
    {
        path:'/login',
        element: <LoginPage />,
    },
    {
        path:'/signup',
        element: <SignupPage />,
    },
    {
        path:'/upload',
        element: <UploadPage />,
    },
    {
        path:'/upload/pin', //임시로
        element: <AfterUploadPage />,
    },
])

// eslint-disable-next-line react/jsx-no-undef
export const FishRouter = () => <RouterProvider router={fishRouter} />

