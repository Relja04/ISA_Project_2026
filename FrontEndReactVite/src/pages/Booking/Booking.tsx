import { useParams, Navigate } from "react-router-dom";
import { useState, useEffect } from "react";
import { DoctorService } from "../../services/DoctorService";
import { type DoctorResponseModel } from "../../model/DoctorResponseModel"
import SideBar from "../../components/sidebar/SideBar"
import NavBar from "../../components/navbar/NavBar"
import "./Booking.css"

interface PracticeModel {
    name: string;
}

export default function Booking() {
    const { practiceName } = useParams();

    const [practices, setPractices] = useState<PracticeModel[]>([]);
    const [loading, setLoading] = useState(true);
    const [doctors, setDoctors] = useState<DoctorResponseModel[]>([])
    const [sidebarOpen, setSidebarOpen] = useState(false)

    const findDoctor = async (practice: string) => {
        try {
            const response = await DoctorService.findDoctor(practice)
            const data: DoctorResponseModel[] = response.data
            setDoctors(data)
        } catch (error) {
            console.error("Error fetching doctors:", error)
        }
    }

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await DoctorService.getPractice();
                setPractices(response.data);
                if (practiceName) {
                    await findDoctor(practiceName);
                }
            } catch (error) {
                console.error(error);
            } finally {
                setLoading(false);
            }
        };
        fetchData();
    }, [practiceName]);

    const normalizedParam = practiceName?.toLowerCase();
    if (loading) {
        return <div>Loading...</div>;
    }

    const exists = practices.some(
        (p) => p.name.toLowerCase() === normalizedParam
    );

    if (!exists) {
        return <Navigate to="/not-found" replace />;
    }

    const findByDate = async (e: React.ChangeEvent<HTMLInputElement>) => {
        try {
            const isoDate = new Date(e.target.value).toISOString();

            const response = await DoctorService.findDoctorsByDate(practiceName!, isoDate);
            console.log(response.data);
            setDoctors(response.data);
        } catch (error) {
            console.error("Error fetching doctors by date:", error);
        }
    }

    return (
        <>
            <NavBar />
            <SideBar isOpen={sidebarOpen} setIsOpen={setSidebarOpen} />
            <div className={`main ${sidebarOpen ? 'sidebar-active' : ''}`}>
                <input type="date" className="calendar" onChange={findByDate}></input>
                <div className="appointments">
                    {doctors.map((doctor) => (
                        <div className="appointment">
                            <div className="appointment-info">
                                <h3>{doctor.name} {doctor.lastName}</h3>
                                <p className="practice">{practiceName}</p>
                                <p className="time">Available: {new Date(doctor.start).toLocaleDateString("sr-rs", { year: 'numeric', month: '2-digit', day: "2-digit", hour: '2-digit', minute: '2-digit' })}</p>
                            </div>
                            <button className="book-btn">Select Time</button>
                        </div>
                    ))}
                    {doctors.length === 0 && (
                        <p className="no-appointments">No available appointments for this practice today.</p>
                    )}
                </div>
            </div>
        </>
    );
}