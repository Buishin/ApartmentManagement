import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import residentApi from "../../services/residentApi";

export default function ResidentList() {
    const navigate = useNavigate();
    const [residents, setResidents] = useState([]);

    useEffect(() => {
        fetchResidents();
    }, []);

    const fetchResidents = async () => {
        try {
            const res = await residentApi.getAll();
            setResidents(res.data.content);
        } catch (err) {
            console.error(err);
        }
    };

    return (
        <div className="container py-4">

            <div className="d-flex justify-content-between mb-3">
                <h2>Quản lý cư dân</h2>
            </div>

            <div className="card shadow">
                <div className="card-body">
                    <table className="table table-hover">
                        <thead className="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Họ tên</th>
                                <th>SĐT</th>
                                <th>Username</th>
                                <th>Thao tác</th>
                            </tr>
                        </thead>

                        <tbody>
                            {residents.map((r) => (
                                <tr key={r.id}>
                                    <td>{r.id}</td>
                                    <td>{r.fullName}</td>
                                    <td>{r.phone}</td>
                                    <td>{r.username}</td>

                                    <td>
                                        <button
                                            className="btn btn-info btn-sm me-2"
                                            onClick={() =>
                                                navigate(`/residents/${r.id}`)
                                            }
                                        >
                                            Xem
                                        </button>

                                        <button
                                            className="btn btn-warning btn-sm"
                                            onClick={() =>
                                                navigate(`/residents/edit/${r.id}`)
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