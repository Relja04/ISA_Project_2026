import { useState } from "react";
import { AuthService } from "../../services/AuthService";
import { type UserModel } from "../../model/UserModel";
import { Link, useNavigate } from "react-router-dom";
import "./Login.css"
import axios from "axios";

export default function Login() {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [error, setError] = useState('')
    const [passwordShown, setPasswordShown] = useState(false)
    const navigate=useNavigate()

    const toggle = () => {
        setPasswordShown(!passwordShown)
    }

    const handleLogin = async (e: React.SubmitEvent) => {
        e.preventDefault()

        try {
            const response = await AuthService.login(username, password)
            const data: UserModel = response.data
            setStorage(String(data.userId), data.username, data.role, data.token)
            navigate("/")
        } catch (error) {
            if (axios.isAxiosError(error)) {
                if (!error.response) {
                    // Network error (server is down, or user is offline)
                    setError("Network error. Please check your internet connection.");
                } else if (error.response.status === 401) {
                    // Wrong username or password
                    setError("Invalid username or password.");
                } else if (error.response.status === 400) {
                    // Bad Request
                    setError("Please fill out all fields correctly.");
                } else {
                    // Other server errors
                    setError(error.response.data?.message || "Something went wrong. Please try again.");
                }
            } else {
                setError("An unexpected error occurred.");
            }
        }
    }
    return (
        <div className="loginContainer">
            <form onSubmit={handleLogin}>
                <h1>Login</h1>
                {error && <p className="errorMessage" style={{ color: "red" }}>{error}</p>}
                <label htmlFor="username">Username:</label>
                <input type="text" id="username" name="username" placeholder="Username" value={username} onChange={(e)=>setUsername(e.target.value)}></input>
                <label htmlFor="password">Password:</label>
                <input type={passwordShown ? "text" : "password"} id="password" name="password" placeholder="Password" value={password} onChange={(e)=>setPassword(e.target.value)}></input>
                <label htmlFor="showPassword">Show Password</label>
                <input type="checkbox" id="showPassword" name="showPassword" checked={passwordShown} onChange={toggle}></input>
                <button type="submit">SIGN IN</button>
                <p>Don't have an account? <Link to="/Register"> Register</Link></p>
            </form>
        </div>
    )
}

function setStorage(id: string, username: string, role: string, token: string): void {
    localStorage.setItem("userId", id)
    localStorage.setItem("username", username)
    localStorage.setItem("userRole", role)
    localStorage.setItem("token", token)
}