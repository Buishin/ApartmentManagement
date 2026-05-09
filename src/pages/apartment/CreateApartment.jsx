import { useState } from "react";
import { useNavigate } from "react-router-dom";
import apartmentApi from "../../services/apartmentApi";

export default function CreateApartment() {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        number: "",
        area: "",
        status: ""
    });

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await apartmentApi.create(formData);
            alert("Tạo căn hộ thành công");
            navigate("/apartments");
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="container py-4">
            <div className="card shadow">
                <div className="card-body">
                    <h3>Thêm căn hộ</h3>

                    <form onSubmit={handleSubmit}>
                        <input
                            type="text"
                            name="number"
                            placeholder="Số căn hộ"
                            className="form-control mb-3"
                            onChange={handleChange}
                        />

                        <input
                            type="number"
                            name="area"
                            placeholder="Diện tích"
                            className="form-control mb-3"
                            onChange={handleChange}
                        />

                        <input
                            type="text"
                            name="status"
                            placeholder="Trạng thái"
                            className="form-control mb-3"
                            onChange={handleChange}
                        />

                        <button className="btn btn-success">
                            Tạo mới
                        </button>

                        <button className="btn btn-secondary" onClick={() => navigate("/home")}>Trang chủ</button>
                    </form>
                </div>
            </div>
        </div>
    );
}