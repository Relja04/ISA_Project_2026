import "./NavBar.css"
import { Link } from "react-router-dom"

export default function NavBar() {
    return (
        <nav className="navigation_bar">
            <ul className="navigation_list">
                <Link to="/home/appointments" className="appointmentsLink">Appointments</Link>
                <li>Log out</li>
            </ul>
        </nav>
    )
}