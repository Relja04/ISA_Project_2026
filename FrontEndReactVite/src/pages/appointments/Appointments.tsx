import { useEffect, useState } from "react"
import { AppointmentService } from "../../services/AppointmentService"
import { type AppointmentResponse } from "../../model/AppointmentResponseModel"
import NavBar from "../../components/navbar/NavBar"
import SideBar from "../../components/sidebar/SideBar"
import "./Appointments.css"
import type { EditList } from "../../model/EditListModel"
import { useNavigate } from "react-router-dom"

export default function Appointments() {
    const [appointments, setAppointments] = useState<AppointmentResponse[]>([])
    const [loading, setLoading] = useState(true)
    const [sidebarOpen, setSidebarOpen] = useState(false)
    const [overlayShown, setOverlayShown] = useState(false)
    const [appointmentCurrentlyEditing, setAppointmentCurrentlyEditing] = useState<number>(0)
    const [editList, setEditList] = useState<EditList[]>([])
    const navigate = useNavigate()

    const toggleOverlay = () => {
        setOverlayShown(!overlayShown)
    }

    const handleApiError = (error: any) => {
        if (error?.response?.status === 401 || error?.status === 401) {
            navigate("/login")
            return true
        }
        console.error("API Error occurred:", error)
        return false;
    }

    const getAllAppointments = async () => {
        try {
            const response = await AppointmentService.getAllAppointment()
            setAppointments(response.data)
        } catch (error) {
            handleApiError(error)
        } finally {
            setLoading(false)
        }
    }

    const handleEdit = async (doctorId: number, oldDasId: number) => {
        try {
            toggleOverlay()
            setAppointmentCurrentlyEditing(oldDasId)
            const response = await AppointmentService.listEdit(doctorId)
            setEditList(response.data)
        } catch (error) {
            handleApiError(error)
        }
    }

    const handleUpdate = async (oldAppointmentId: number, newAppointmentId: number, userId: number) => {
        try {
            await AppointmentService.edit(oldAppointmentId, newAppointmentId, userId)
            toggleOverlay()
            await getAllAppointments()
        } catch (error) {
            handleApiError(error)
        }
    }

    const handleCancel = async (id: number, dasId: number) => {
        try {
            await AppointmentService.cancel(id, dasId)
            setAppointments((prevAppointments) =>
                prevAppointments.filter(appointment => appointment.id !== id)
            )
        } catch (error) {
            handleApiError(error)
        }
    }

    useEffect(() => {
        getAllAppointments()
    }, [])

    if (loading) {
        return <div>Loading...</div>
    }

    return (
        <>
            <NavBar />
            <SideBar isOpen={sidebarOpen} setIsOpen={setSidebarOpen} />
            <div className={`main ${sidebarOpen ? 'sidebar-active' : ''}`}>
                {appointments.map((appointment) => (
                    <div className="appointment" key={appointment.id}>
                        <div className="appointment-info">
                            <h3>{appointment.doctorName}</h3>
                            <p className="practice">{appointment.medicalPractice}</p>
                            <p className="time">From: {new Date(appointment.slotStart).toLocaleTimeString("sr-rs", { hour: '2-digit', minute: '2-digit' })}</p>
                            <p className="time">To: {new Date(appointment.slotEnd).toLocaleTimeString("sr-rs", { hour: '2-digit', minute: '2-digit' })}</p>
                        </div>
                        <button onClick={() => handleEdit(appointment.doctorId, appointment.dasId)}>Edit</button>
                        <button onClick={() => handleCancel(appointment.id, appointment.dasId)}>Cancel</button>
                    </div>
                ))}
                
                <div className="overlay" style={{ display: overlayShown ? "flex" : "none" }} onClick={toggleOverlay}>
                    <div className="detail" onClick={(e) => e.stopPropagation()}>
                        <header>
                            <h1>Edit appointment</h1>
                        </header>
                        <table>
                            <thead>
                                <tr>
                                    <th>From</th>
                                    <th>To</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                {editList.map((appointment) => (
                                    <tr key={appointment.dasId}>
                                        <td style={{ borderRight: "2px solid black", width: "195px" }}>
                                            {new Date(appointment.slotStart).toLocaleDateString("sr-rs", { year: 'numeric', month: '2-digit', day: "2-digit", hour: '2-digit', minute: '2-digit' })}
                                        </td>
                                        <td>
                                            {new Date(appointment.slotEnd).toLocaleDateString("sr-rs", { year: 'numeric', month: '2-digit', day: "2-digit", hour: '2-digit', minute: '2-digit' })}
                                        </td>
                                        <td>
                                            <button onClick={() => handleUpdate(appointmentCurrentlyEditing, appointment.dasId, Number(localStorage.getItem("userId")))}>
                                                Change time
                                            </button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </>
    )
}