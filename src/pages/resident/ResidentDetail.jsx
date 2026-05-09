import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import residentApi from "../../services/residentApi";

export default function ResidentDetail() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [resident, setResident] = useState(null);

    useEffect(() => {
        fetchResident();
    }, []);

    const fetchResident = async () => {
        try {
            const res = await residentApi.getById(id);
            setResident(res.data);
        } catch (err) {
            console.error(err);
        }
    };

    if (!resident) {
        return <h4 className="text-center mt-5">Đang tải...</h4>;
    }

    return (
        <div className="container py-4">

            <div className="card shadow">
                <div className="card-body">
                    <h3>Chi tiết cư dân</h3>
                    <hr />

                    <p><b>Họ tên:</b> {resident.fullName}</p>
                    <p><b>SĐT:</b> {resident.phone}</p>
                    <p><b>Username:</b> {resident.username}</p>

                    <button
                        className="btn btn-secondary"
                        onClick={() => navigate("/residents")}
                    >
                        Quay lại
                    </button>
                </div>
            </div>

        </div>
    );
}