import { useState } from "react";
import { useSearchParams, useNavigate } from "react-router-dom";
import residentApi from "../../services/residentApi";

export default function CreateResident() {
    const navigate = useNavigate();
    const [searchParams] = useSearchParams();

    const apartmentId = searchParams.get("apartmentId");

    const [form, setForm] = useState({
        fullName: "",
        phone: ""
    });

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await residentApi.create({
                fullname: form.fullName,
                phone: form.phone,
                apartmentId: Number(apartmentId)
            });

            alert("Thêm cư dân thành công");
            navigate(`/apartments/${apartmentId}`);
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="container py-4">
            <div className="card shadow">
                <div className="card-body">
                    <h3>Thêm cư dân</h3>

                    <form onSubmit={handleSubmit}>
                        <input
                            name="fullName"
                            placeholder="Tên"
                            className="form-control mb-2"
                            onChange={handleChange}
                        />

                        <input
                            name="phone"
                            placeholder="SĐT"
                            className="form-control mb-2"
                            onChange={handleChange}
                        />

                        <button className="btn btn-success">
                            Thêm
                        </button>
                    </form>
                    <button className="btn btn-secondary" onClick={() => navigate("/home")}>Trang chủ</button>
                </div>
            </div>
        </div>
    );
}