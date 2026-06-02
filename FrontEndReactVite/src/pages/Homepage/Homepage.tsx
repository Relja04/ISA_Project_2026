import { useState, useEffect } from "react";
import { REVIEWS_DATA } from "../../model/ReviewsModel"
import NavBar from "../../components/navbar/NavBar"
import "./Homepage.css"
import SideBar from "../../components/sidebar/SideBar"

export default function Home() {
    const [currentReviewIndex, setCurrentReviewIndex] = useState(0);
    const [sidebarOpen, setSidebarOpen] = useState(false)

    useEffect(() => {
        const interval = setInterval(() => {
            setCurrentReviewIndex((prev) => (prev === REVIEWS_DATA.length - 1 ? 0 : prev + 1));
        }, 10000);
        return () => clearInterval(interval);
    }, []);

    const currentReview = REVIEWS_DATA[currentReviewIndex];


    return (
        <>
            <NavBar />
            <SideBar isOpen={sidebarOpen} setIsOpen={setSidebarOpen} />
            <div className={`main ${sidebarOpen ? 'sidebar-active' : ''}`}>
                <div className="mainContainer">
                    <div className="mainContainerContent">
                        <h1>Your Health,<br />Our Priority</h1>
                        <p>Trusted care with advanced solutions to support<br /> your health and wellbeing</p>
                        <button className="book" onClick={()=>setSidebarOpen(true)}>Book appointment</button>
                        <button className="learn">Learn more</button>
                    </div>
                    <div className="imageContainer">
                        <img src="/main-background.png"></img>
                        <img src="/main-background-1.png"></img>
                    </div>
                </div>
                <div className="reviews" key={currentReviewIndex}>
                    {currentReview ? (
                        <>
                            <img src={currentReview.img} alt={`${currentReview.name}'s review`} />
                            <h1>{currentReview.name}</h1>
                            <p>{currentReview.text}</p>
                            <p className="stars">{currentReview.stars}</p>
                        </>
                    ) : (
                        <p>No reviews available.</p>
                    )}
                </div>
            </div>
        </>
    )
}