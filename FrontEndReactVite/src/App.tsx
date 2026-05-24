import './App.css'
import { Routes, Route, Link } from 'react-router-dom'
import Login from './pages/Login/Login'
import Register from './pages/Register/Register'

function Home() {
    return <h1>Hello World</h1>
}

function App() {
    return (
        <>
                <Link to="/">Home</Link>
                {" | "}
                <Link to="/login">Login</Link>
                {" | "}
                <Link to="/register">Register</Link>

            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
            </Routes>
        </>
    )
}

export default App
