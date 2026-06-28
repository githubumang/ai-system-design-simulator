import { Outlet, useLocation } from "react-router-dom"
import { Navbar } from "../components/Navbar"

function MainLayout() {
  const { pathname } = useLocation()
  const isHome = pathname === "/"

  return (
    <div className="min-h-dvh bg-background">
      <Navbar />
      <main className={isHome ? undefined : "container mx-auto p-6"}>
        <Outlet />
      </main>
    </div>
  )
}

export default MainLayout
