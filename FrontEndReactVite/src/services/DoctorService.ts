import axios from "axios"

const doctor = axios.create({
    baseURL: "http://localhost:8080/home",
    headers: {
        "Content-Type": "application/json",
        "Accept": "application/json",
    }
})

doctor.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("token");
        if (token) {
            config.headers.Authorization = token;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);


export class DoctorService {
    static async findDoctor(practice:string) {
        return doctor.post("doctors", {  practice })
    }
    static async bookDoctor(id:number){
        return doctor.post("doctors/book",{id})
    }
}