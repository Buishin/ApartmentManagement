import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import apartmentApi from "../../services/apartmentApi";
import { useSearchParams } from "react-router-dom";


export default function ApartmentDetail() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [apartment, setApartment] = useState(null);
    const [searchParams] = useSearchParams();
    const apartmentId = searchParams.get("apartmentId");

    useEffect(() => {
        fetchApartment();
    }, []);

    const fetchApartment = async () => {
        try {
            const res = await apartmentApi.getById(id);
            setApartment(res.data);
        } catch (err) {
            console.error(err);
        }
    };

    if (!apartment) {
        return <h4 className="text-center mt-5">Đang tải...</h4>;
    }

    return (
        <div className="container py-4">

            {/* APARTMENT INFO */}
            <div className="card shadow mb-4">
                <div className="card-body">
                    <h3>Chi tiết căn hộ</h3>
                    <hr />

                    <p><b>ID:</b> {apartment.id}</p>
                    <p><b>Số căn hộ:</b> {apartment.number}</p>
                    <p><b>Diện tích:</b> {apartment.area} m²</p>
                    <p><b>Trạng thái:</b> {apartment.status}</p>
                </div>
            </div>

            {/* RESIDENT LIST */}
            <div className="card shadow">
                <div className="card-body">

                    <div className="d-flex justify-content-between align-items-center mb-3">
                        <h4>Cư dân trong căn hộ</h4>

                        <button
                            type="button"
                            className="btn btn-success"
                            onClick={() =>
                                navigate(`/residents/create?apartmentId=${apartment.id}`)
                            }
                        >
                            + Thêm cư dân
                        </button>
                    </div>

                    {apartment.residents?.length > 0 ? (
                        <table className="table table-hover">
                            <thead className="table-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>Họ tên</th>
                                    <th>SĐT</th>
                                    <th>Username</th>
                                </tr>
                            </thead>

                            <tbody>
                                {apartment.residents.map((r) => (
                                    <tr key={r.id}>
                                        <td>{r.id}</td>
                                        <td>{r.fullName}</td>
                                        <td>{r.phone}</td>
                                        <td>{r.username}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    ) : (
                        <p className="text-muted">Căn hộ trống</p>
                    )}
                </div>
            </div>

            <button
                className="btn btn-secondary mt-3"
                onClick={() => navigate("/apartments")}
            >
                Quay lại
            </button>
        </div>
    );
}