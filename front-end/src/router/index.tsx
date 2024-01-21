import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import React from "react";
import { MainPage } from "../app/main"
//import { RouterProvider } from "react-router-dom";

export type DiaryRouterPath = '/' | `/detail/${string}` | '/emotions' | `/emotions/${string}`
const diaryRouter = createBrowserRouter([
    {
        path: '/',
        element: <MainPage />,
    },
])

// eslint-disable-next-line react/jsx-no-undef
export const DiaryRouter = () => <RouterProvider router={diaryRouter} />

