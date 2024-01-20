import React from "react"

export const RootLayout = ({ children }: React.PropsWithChildren) => {
    return <main className="min-h-screen max-h-screen h-screen w-full flex items-center justify-center bg-gray-500">{children}</main>
}
