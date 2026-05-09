import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import authService from "../../services/authApi";

export default function Home() {
    const navigate = useNavigate();
    const [user, setUser] = useState(null);
    useEffect(() => {
        fetchProfile();
    }, []);

    const fetchProfile = async () => {
        try {
            const response = await authService.getProfile();
            setUser(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    const handleLogout = () => {
        authService.logout();
        navigate("/login");
    };

    return (
        <div className="d-flex" style={{ minHeight: "100vh" }}>
            <div className="bg-dark text-white p-3" style={{ width: "250px" }}>
                <h4 className="mb-4">Apartment System</h4>
                <ul className="nav flex-column">
                    <li className="nav-item mb-2">
                        <button className="btn btn-outline-light w-100" onClick={() => navigate("/home")}>Trang chủ</button>
                    </li>

                    <li className="nav-item mb-2">
                        <button className="btn btn-outline-light w-100" onClick={() => navigate("/profile")} >Hồ sơ </button>
                    </li>

                    {user?.role === "ADMIN" && (
                        <li className="nav-item mb-2">
                            <button className="btn btn-outline-light w-100" onClick={() => navigate("/residents")}>Quản lý cư dân</button>
                        </li>
                    )}
                    {user?.role === "ADMIN" && (
                        <li className="nav-item mb-2">
                            <button className="btn btn-outline-light w-100" onClick={() => navigate("/apartments")}> Quản lý căn hộ</button>
                        </li>
                    )}
                    {user?.role === "MANAGER" && (
                        <li className="nav-item mb-2">
                            <button className="btn btn-outline-light w-100" onClick={() => navigate("/bills")}>Quản lý hóa đơn </button>
                        </li>
                    )}
                </ul>
            </div>

            <div className="flex-grow-1 bg-light">
                <div className="bg-success text-white p-3 d-flex justify-content-between align-items-center">
                    <h3 className="mb-0">Trang quản lý chung cư</h3>
                    <button className="btn btn-danger" onClick={handleLogout}>
                        Đăng xuất
                    </button>
                </div>
                <div className="container mt-4">
                    {user ? (
                        <>
                            <div className="card shadow border-0 mb-4">
                                <div className="card-body p-4">
                                    <div className="d-flex justify-content-between align-items-center">
                                        <div>
                                            <h3 className="fw-bold mb-1"> Xin chào, {user.fullName}</h3>
                                            <p className="text-muted mb-2">{user.email}</p>
                                            <span className="badge bg-primary px-3 py-2">
                                                {user.role}
                                            </span>
                                        </div>

                                        <button className="btn btn-outline-primary" onClick={() => navigate("/profile")}>Hồ sơ cá nhân</button>
                                    </div>
                                </div>
                            </div>
                            <div className="row g-4">
                                <div className="col-md-4">
                                    <div
                                        className="card shadow-sm h-100"
                                        style={{ cursor: "pointer" }}
                                        onClick={() =>
                                            navigate("/bills")
                                        }
                                    >
                                        <div className="card-body">
                                            <h5>Quản lý hóa đơn</h5>
                                            <p className="text-muted">
                                                Xem và thanh toán các khoản phí
                                                chung cư
                                            </p>
                                        </div>
                                    </div>
                                </div>

                                <div className="col-md-4">
                                    <div className="card shadow-sm h-100" style={{ cursor: "pointer" }}
                                        onClick={() => navigate("/feedback")}>
                                        <div className="card-body">
                                            <h5>Phản ánh</h5>
                                            <p className="text-muted">
                                                Gửi phản ánh về sự cố và dịch vụ
                                            </p>
                                        </div>
                                    </div>
                                </div>

                                <div className="col-md-4">
                                    <div
                                        className="card shadow-sm h-100"
                                        style={{ cursor: "pointer" }}
                                        onClick={() => navigate("/notification")}>
                                        <div className="card-body">
                                            <h5>Khảo sát</h5>
                                            <p className="text-muted"> các thông báo từ ban quản lý</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </>
                    ) : (
                        <div className="text-center mt-5">
                            <h4>Đang tải dữ liệu...</h4>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
}