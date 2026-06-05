import './App.css'
import { Routes, Route, Navigate } from 'react-router-dom'
import Login from './pages/Login/Login'
import Register from './pages/Register/Register'
import Home from "./pages/Homepage/Homepage"
import Booking from "./pages/Booking/Booking"
import { redirect } from "react-router";
import NotFound from './pages/404/NotFound'
import Appointments from './pages/appointments/Appointments'

function App() {
    redirect("/home")
    return (
        <>{/*}
                <Link to="/home">Home</Link>
                {" | "}
                <Link to="/login">Login</Link>
                {" | "}
                <Link to="/register">Register</Link>
                */}
            <Routes>
                <Route path="/" element={<Navigate to="/home" replace />} />
                <Route path="/not-found" element={<NotFound/>}/>
                <Route path="/home" element={<Home />} />
                <Route path="/home/:practiceName" element={<Booking />}/>
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/home/appointments" element={<Appointments/>}/>
            </Routes>
        </>
    )
}

export default App
