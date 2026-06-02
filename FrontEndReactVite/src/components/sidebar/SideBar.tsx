import { Link } from 'react-router-dom';
import './SideBar.css';
import { useState, useEffect } from 'react';
import { DoctorService } from '../../services/DoctorService';


interface SideBarProps {
    isOpen: boolean;
    setIsOpen: React.Dispatch<React.SetStateAction<boolean>>;
}
interface PracticeModel {
    name: string;
}

export default function SideBar({ isOpen, setIsOpen }: SideBarProps) {

    const [practices, setPractices] = useState<PracticeModel[]>([]);
    const [loading, setLoading] = useState(true)

    function toggleSidebar() {
        setIsOpen(!isOpen);
    }
    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await DoctorService.getPractice();
                setPractices(response.data);
            } catch (error) {
                console.error(error);
            } finally {
                setLoading(false);
            }
        };
        fetchData();
    }, []);

    return (
        <>
            <button
                onClick={toggleSidebar}
                className="menu-button"
                aria-label={isOpen ? "Close menu" : "Open menu"}
            >
                <img src='/burger-menu-svgrepo-com.svg' alt="Menu" />
            </button>

            <div className={`sidebar ${isOpen ? 'active' : ''}`} id="sidebar">
                <ul className='listPractices'>
                    <h1>Practices</h1>
                    {practices.map((practice) => (
                            <Link to={`/home/${practice.name}`} className='sideLink'>{practice.name}</Link>
                    ))}
                </ul>
            </div>
        </>
    );
}