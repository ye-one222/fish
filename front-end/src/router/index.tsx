import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import React from "react";
import { MainPage } from "../app/main"
import { UploadPage } from "../app/upload"
//import { RouterProvider } from "react-router-dom";

export type DiaryRouterPath = '/' | `/detail/${string}` | '/upload' | `/emotions/${string}`
const diaryRouter = createBrowserRouter([
    {
        path: '/',
        element: <MainPage />,
    },
    {
        path: '/upload',
        element: <UploadPage />,
    },
])

// eslint-disable-next-line react/jsx-no-undef
export const DiaryRouter = () => <RouterProvider router={diaryRouter} />

