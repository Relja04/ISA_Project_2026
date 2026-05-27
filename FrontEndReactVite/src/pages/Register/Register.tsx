import "./Register.css"
import { useState } from "react"
import { Link } from "react-router-dom"
import { AuthService } from "../../services/AuthService"
export default function Register() {
    const [username, setUsername] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [passwordConf, setPasswordConf] = useState('')
    const [error, setError] = useState('')
    const [message, setMessage] = useState('')

    const [showToast, setShowToast] = useState(false)
    const [passwordShown, setPasswordShown] = useState(false)

    const toggle = () => {
        setPasswordShown(!passwordShown)
    }

    const handleRegister = async (e: React.SubmitEvent) => {
        e.preventDefault()
        if (password !== passwordConf) {
            setError("Passwords do not match!")
            return
        }
        try {
            const response=AuthService.register(username,email,password)
            setMessage((await response).data)
            setMessage("Success")

            setShowToast(true)
            setTimeout(() => {
                setShowToast(false)
            }, 7000)
        } catch (err) {
            setError("Registration failed. Please try again.")
        }
    }

    return (
        <div className="registerContainer">
            <form onSubmit={handleRegister}>
                <h1>Register</h1>
                {error && <p className="errorMessage" style={{ color: "red" }}>{error}</p>}
                {message && <p className="message" style={{ color: "green" }}>{message}</p>}
                <label htmlFor="username">Username:</label>
                <input type="text" id="username" name="username" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)}></input>
                <label htmlFor="email">Email:</label>
                <input type="email" id="email" name="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)}></input>
                <label htmlFor="password">Password:</label>
                <input type={passwordShown ? "text" : "password"} id="password" name="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)}></input>
                <label htmlFor="password">Confirm password:</label>
                <input type={passwordShown ? "text" : "password"} id="passwordConf" name="passwordConf" placeholder="Confirm Password" value={passwordConf} onChange={(e) => setPasswordConf(e.target.value)}></input>
                <label htmlFor="showPassword">Show Password</label>
                <input type="checkbox" id="showPassword" name="showPassword" checked={passwordShown} onChange={toggle}></input>
                <button type="submit">Register</button>
                <p>Already have an account? <Link to="/Login"> Sign in</Link></p>
            </form>
            {showToast && (
                <div className="glide-toast">
                    {message}
                </div>
            )}
        </div>
    )
}