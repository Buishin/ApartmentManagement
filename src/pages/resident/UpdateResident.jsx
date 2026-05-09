import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import residentApi from "../../services/residentApi";

export default function UpdateResident() {
    const { id } = useParams();
    const navigate = useNavigate();

    const [form, setForm] = useState({
        fullName: "",
        phone: ""
    });

    useEffect(() => {
        fetchResident();
    }, []);

    const fetchResident = async () => {
        try {
            const res = await residentApi.getById(id);

            setForm({
                fullName: res.data.fullName,
                phone: res.data.phone
            });

        } catch (error) {
            console.error(error);
        }
    };

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await residentApi.update(id, {
                fullName: form.fullName,
                phone: form.phone
            });

            alert("Cập nhật cư dân thành công");
            navigate("/residents");

        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="container py-4">
            <div className="card shadow">
                <div className="card-body">
                    <h3>Cập nhật cư dân</h3>

                    <form onSubmit={handleSubmit}>
                        <input
                            name="fullName"
                            value={form.fullName}
                            placeholder="Tên cư dân"
                            className="form-control mb-2"
                            onChange={handleChange}
                        />

                        <input
                            name="phone"
                            value={form.phone}
                            placeholder="Số điện thoại"
                            className="form-control mb-3"
                            onChange={handleChange}
                        />

                        <button className="btn btn-warning">
                            Cập nhật
                        </button>

                        <button
                            type="button"
                            className="btn btn-secondary ms-2"
                            onClick={() => navigate("/residents")}
                        >
                            Quay lại
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
}