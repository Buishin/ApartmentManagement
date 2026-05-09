import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import apartmentApi from "../../services/apartmentApi";

export default function ApartmentList() {
    const navigate = useNavigate();
    const [apartments, setApartments] = useState([]);

    useEffect(() => {
        fetchApartments();
    }, []);

    const fetchApartments = async () => {
        try {
            const response = await apartmentApi.getAll();
            setApartments(response.data.content);
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="container py-4">
            <div className="d-flex justify-content-between align-items-center mb-4">
                <h2 className="fw-bold">Quản lý căn hộ</h2>

                <button
                    className="btn btn-success"
                    onClick={() => navigate("/apartments/create")}
                >
                    + Thêm căn hộ
                </button>
            </div>

            <div className="card shadow border-0">
                <div className="card-body">
                    <table className="table table-hover align-middle">
                        <thead className="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Số căn hộ</th>
                                <th>Diện tích</th>
                                <th>Trạng thái</th>
                                <th>Thao tác</th>
                            </tr>
                        </thead>

                        <tbody>
                            {apartments.map((apartment) => (
                                <tr key={apartment.id}>
                                    <td>{apartment.id}</td>
                                    <td>{apartment.number}</td>
                                    <td>{apartment.area} m²</td>
                                    <td>
                                        <span className="badge bg-primary">
                                            {apartment.status}
                                        </span>
                                    </td>
                                    <td>
                                        <button
                                            className="btn btn-info btn-sm me-2"
                                            onClick={() =>
                                                navigate(
                                                    `/apartments/${apartment.id}`
                                                )
                                            }
                                        >
                                            Xem
                                        </button>

                                        <button
                                            className="btn btn-warning btn-sm"
                                            onClick={() =>
                                                navigate(
                                                    `/apartments/edit/${apartment.id}`
                                                )
                                            }
                                        >
                                            Sửa
                                        </button>
                                    </td>
                                </tr>

                            ))}
                        </tbody>
                    </table>
                    <button className="btn btn-secondary" onClick={() => navigate("/home")}>Trang chủ</button>
                </div>
            </div>
        </div>
    );
}