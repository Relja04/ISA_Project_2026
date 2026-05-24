import axios from "axios"

const auth = axios.create({
    baseURL: "http://localhost:8080/api/auth",
    headers: {
        "Content-Type": "application/json",
        "Accept": "application/json",
    }
})

export class AuthService {
    static async login(username: string, password: string) {
        return auth.post("/login", { username, password })
    }
    static async register(username: string, email: string, password: string){
        return auth.post("/register",{username,email,password})
    }
}