import { useState } from "react"
import NavBar from "../../components/navbar/NavBar"
import "./Homepage.css"
import { DoctorService } from "../../services/DoctorService"
import { type DoctorResponseModel } from "../../model/DoctorResponseModel"


export default function Home() {
    const [overlayShown, setOverlayShown] = useState(false)
    const [doctors, setDoctors] = useState<DoctorResponseModel[]>([])

    const toggleOverlay = (ime: string = "") => {
        setOverlayShown(!overlayShown)
        if (ime) {
            findDoctor(ime)
        }
    }

    const findDoctor = async (practice: string) => {
        try {
            const response = await DoctorService.findDoctor(practice)
            console.log(response)
            const data: DoctorResponseModel[] = response.data
            setDoctors(data)
        } catch (error) {
            console.error("Error fetching doctors:", error)
        }
    }
    return (
        <>
            <NavBar />
            <div className="main">
                <div className="topLeft divs">
                    <h1>Cardiology</h1>
                    <img className="images" src="/images.jpg"></img>
                    <button className="buttons" onClick={() => toggleOverlay("Cardiology")}>Book now</button>
                </div>
                <div className="topRight divs">
                    <h1>Gastroenterology</h1>
                    <img className="images" src="/12106213.jpg"></img>
                    <button className="buttons" onClick={() => toggleOverlay("Gastroenterology")}>Book now</button>
                </div>
                <div className="bottomLeft divs">
                    <h1>Rheumatology</h1>
                    <img className="images" src="/rev.jpg"></img>
                    <button className="buttons" onClick={() => toggleOverlay("Rheumatology")}>Book now</button>
                </div>
                <div className="bottomRight divs">
                    <h1>Pediatrics</h1>
                    <img className="images" src="/ped.png"></img>
                    <button className="buttons" onClick={() => toggleOverlay("Pediatrics")}>Book now</button>
                </div>
                <div className="overlay" style={{ display: overlayShown ? "flex" : "none" }} onClick={() =>{ toggleOverlay(); setDoctors([]) }}>
                    <div className="detail" onClick={(e) => e.stopPropagation()}>
                        <header>
                            <h1>Booking</h1>
                            <input type="date" className="calendar"></input>
                        </header>
                        <table>
                            <thead>
                                <tr>
                                    <th>Ime</th>
                                    <th>Prezime</th>
                                    <th>Vreme</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                {doctors.map((doctor) => (
                                    <tr key={`${doctor.id}-${doctor.start}`}>
                                        <td>{doctor.name}</td>
                                        <td>{doctor.lastName}</td>
                                        <td>{(doctor.start)}</td>
                                        <td><button>Submit</button></td>
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