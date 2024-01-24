import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import React from "react";
import { MainPage } from "../app/main.tsx"
import { DownLoadPage } from '../app/download.tsx';
import { LoginPage } from '../app/login.tsx';
//import "/node_modules/react-grid-layout/css/styles.css";
//import "/node_modules/react-resizable/css/styles.css";

export type FishRouterPath = '/' | '/download' 
const fishRouter = createBrowserRouter([
    /*{
        path: '/',
        element: <MainPage />,
    },*/
    {
        path:'/download',
        element: <DownLoadPage />,
    },
    {
        path:'/login',
        element: <LoginPage />,
    },
])

// eslint-disable-next-line react/jsx-no-undef
export const FishRouter = () => <RouterProvider router={fishRouter} />

