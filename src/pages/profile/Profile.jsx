import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import authService from "../../services/authApi";

export default function Profile() {
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
    if (!user) {
        return (
            <div className="container mt-5 text-center"><h4>Đang tải thông tin...</h4></div>
        );
    }

    return (
        <div className="container py-5">
            <div className="row justify-content-center">
                <div className="col-md-8">
                    <div className="card shadow border-0">
                        <div className="card-header bg-primary text-white text-center py-4">
                            <h2>Hồ sơ cá nhân</h2>
                            <p className="mb-0"> Thông tin tài khoản của bạn</p>
                        </div>
                        <div className="card-body p-4">
                            <div className="text-center mb-4">
                                <h3 className="fw-bold">
                                    {user.fullName}
                                </h3>
                                <span className="badge bg-success px-3 py-2">{user.role} </span>
                            </div>
                            <hr />
                            <div className="row mb-3"> <div className="col-4 fw-bold">ID</div>
                                <div className="col-8">{user.id}</div>
                            </div>
                            <div className="row mb-3">
                                <div className="col-4 fw-bold">Username</div>
                                <div className="col-8">{user.username} </div>
                            </div>

                            <div className="row mb-3">
                                <div className="col-4 fw-bold">
                                    Email
                                </div>
                                <div className="col-8">
                                    {user.email}
                                </div>
                            </div>

                            <div className="row mb-3">
                                <div className="col-4 fw-bold">
                                    Họ tên
                                </div>
                                <div className="col-8">
                                    {user.fullName}
                                </div>
                            </div>

                            <div className="row mb-3">
                                <div className="col-4 fw-bold">
                                    Vai trò
                                </div>
                                <div className="col-8">
                                    {user.role}
                                </div>
                            </div>

                            <div className="row mb-3">
                                <div className="col-4 fw-bold">
                                    Ngày tạo
                                </div>
                                <div className="col-8">
                                    {new Date(user.createdAt).toLocaleString()}
                                </div>
                            </div>

                            <div className="row mb-4">
                                <div className="col-4 fw-bold">
                                    Cập nhật lần cuối
                                </div>
                                <div className="col-8">
                                    {new Date(user.updatedAt).toLocaleString()}
                                </div>
                            </div>

                            {/* Buttons */}
                            <div className="d-flex gap-2">
                                <button
                                    className="btn btn-secondary"
                                    onClick={() => navigate("/home")}
                                >
                                    Quay lại
                                </button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}