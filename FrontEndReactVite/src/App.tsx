import './App.css'
import { Routes, Route, Link, Navigate } from 'react-router-dom'
import Login from './pages/Login/Login'
import Register from './pages/Register/Register'
import Home from "./pages/Homepage/Homepage"
import { redirect } from "react-router";


function App() {
    redirect("/home")
    return (
        <>
                <Link to="/home">Home</Link>
                {" | "}
                <Link to="/login">Login</Link>
                {" | "}
                <Link to="/register">Register</Link>

            <Routes>
                <Route path="/" element={<Navigate to="/home" replace />} />
                <Route path="/home" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
            </Routes>
            
        </>
    )
}

export default App
