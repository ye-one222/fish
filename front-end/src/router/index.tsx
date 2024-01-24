import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import React from "react";
import { MainPage } from "../app/main"
import { DownLoadPage } from '../app/download';
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
])

// eslint-disable-next-line react/jsx-no-undef
export const FishRouter = () => <RouterProvider router={fishRouter} />

