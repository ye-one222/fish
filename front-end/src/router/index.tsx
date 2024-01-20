import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import React from "react";
import {MainPage} from "../app/page"
//import { RouterProvider } from "react-router-dom";

export type FishRouterPath = '/' | `/detail/${string}` | '/emotions' | `/emotions/${string}`
const fishRouter = createBrowserRouter([
    {
        path: '/',
        element: <MainPage />,
    },
])

// eslint-disable-next-line react/jsx-no-undef
export const FishRouter = () => <RouterProvider router={fishRouter} />


