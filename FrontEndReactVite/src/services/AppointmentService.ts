import axios from "axios"

const appointment = axios.create({
    baseURL: "http://localhost:8080/home/appointment",
    headers: {
        "Content-Type": "application/json",
        "Accept": "application/json",
    }
})

appointment.interceptors.request.use(
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


export class AppointmentService {
    static async getAllAppointment(){
        return appointment.get("/list")
    }
    static async cancel(id:number,dasId:number){
        return appointment.put(`/cancel/${id}/${dasId}`);
    }
    static async listEdit(doctorId:number){
        return appointment.get(`/editList/${doctorId}`)
    }
    static async edit(oldAppointmentId:number,newAppointmentId:number,userId:number){
        return appointment.post("/edit",{oldAppointmentId,newAppointmentId,userId})
    }
}