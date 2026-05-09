import { Routes, Route } from "react-router-dom";

import Login from "../pages/auth/Login";
import Register from "../pages/auth/Register";
import Home from "../pages/home/Home";
import Profile from "../pages/profile/profile";
import ProtectedRoute from "./ProtectedRoute";
import Apartments from "../pages/apartment/Apartment";
import ApartmentDetail from "../pages/apartment/apartmentDetail";
import CreateApartment from "../pages/apartment/CreateApartment";
import EditApartment from "../pages/apartment/EditApartment";
import ResidentList from "../pages/resident/ResidentList";
import ResidentDetail from "../pages/resident/ResidentDetail";
import CreateResident from "../pages/resident/CreateResident";
import UpdateResident from "../pages/resident/UpdateResident";
import FeedbackPage from "../pages/feedback/Feedback";
import Notification from "../pages/notification/notification";

export default function AppRoutes() {
    return (
        <Routes>

            <Route path="/" element={<Login />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />

            <Route element={<ProtectedRoute />}>

                <Route path="/home" element={<Home />} />
                <Route path="/profile" element={<Profile />} />

                <Route path="/apartments" element={<Apartments />} />
                <Route path="/apartments/:id" element={<ApartmentDetail />} />
                <Route path="/apartments/create" element={<CreateApartment />} />
                <Route path="/apartments/edit/:id" element={<EditApartment />} />

                <Route path="/residents" element={<ResidentList />} />
                <Route path="/residents/:id" element={<ResidentDetail />} />
                <Route path="/residents/create" element={<CreateResident />} />
                <Route path="/residents/edit/:id" element={<UpdateResident />} />
                <Route path="/feedback" element={<FeedbackPage />} />
                <Route path="/notification" element={<Notification />} />

            </Route>

        </Routes>
    );
}