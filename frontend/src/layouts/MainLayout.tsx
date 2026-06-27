import { Outlet } from "react-router-dom";
import Navbar from "../components/Navbar";

function MainLayout() {
  return (
    <>
      <Navbar />
      <main className="container mx-auto p-6">
        <Outlet />
      </main>
    </>
  );
}

export default MainLayout;
