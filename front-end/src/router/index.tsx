import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import React from "react";
import { MainPage } from "../app/main.tsx"
import { SearchPage } from '../app/download/search.tsx';
import { LoginPage } from '../app/login.tsx';
import { SignupPage } from '../app/signup.tsx';
import { UploadPage } from '../app/upload/upload.tsx';
import { AfterUploadPage } from '../app/upload/[pin]/page.tsx';
import { DownLoadPage } from '../app/download/[pin]/page.tsx';

//import { RouterProvider } from "react-router-dom";

export type FishRouterPath = '/' | '/download'| '/fishpassword' |'/login'|'/signup'|'/upload'
const fishRouter = createBrowserRouter([
    {
        path: '/',
        element: <MainPage />,
    },
    {
        path:'/download',
        element: <SearchPage />,
    },
    /*{
        path:`/download/pin`,
        element: <DownLoadPage  />,
    },*/
    {
        path:'/login',
        element: <LoginPage />,
    },
    {
        path:'/signup',
        element: <SignupPage />,
    },
    /*{
        path: '/upload',
        element: <UploadPage />,
    },*/
    {
        //path: `/upload/${pin}`,
        path: '/upload/pin',
        element: <AfterUploadPage/>
    }
])

// eslint-disable-next-line react/jsx-no-undef
export const FishRouter = () => <RouterProvider router={fishRouter} />

