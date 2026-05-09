import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import apartmentApi from "../../services/apartmentApi";

export default function EditApartment() {
    const { id } = useParams();
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        number: "",
        area: "",
        status: ""
    });

    useEffect(() => {
        fetchApartment();
    }, []);

    const fetchApartment = async () => {
        try {
            const response = await apartmentApi.getById(id);
            setFormData(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await apartmentApi.update(id, formData);
            alert("Cập nhật thành công");
            navigate("/apartments");
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="container py-4">
            <div className="card shadow">
                <div className="card-body">
                    <h3>Sửa căn hộ</h3>

                    <form onSubmit={handleSubmit}>
                        <input
                            type="text"
                            name="number"
                            className="form-control mb-3"
                            value={formData.number}
                            onChange={handleChange}
                        />

                        <input
                            type="number"
                            name="area"
                            className="form-control mb-3"
                            value={formData.area}
                            onChange={handleChange}
                        />

                        <input
                            type="text"
                            name="status"
                            className="form-control mb-3"
                            value={formData.status}
                            onChange={handleChange}
                        />

                        <button className="btn btn-primary">
                            Cập nhật
                        </button>
                        <button className="btn btn-secondary" onClick={() => navigate("/home")}>Trang chủ</button>
                    </form>
                </div>
            </div>
        </div>
    );
}