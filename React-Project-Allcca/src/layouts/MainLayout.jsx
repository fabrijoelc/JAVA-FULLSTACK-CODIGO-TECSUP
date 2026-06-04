import Navbar from "../components/ui/Navbar";

export default function MainLayout({ children }) {
  return (
    <>
      <Navbar />  
      <main className="container my-4">{children}</main>
    </>
  );
}
