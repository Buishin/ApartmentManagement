import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import authService from "../../services/authApi";

export default function Login() {
    const [usernameOrEmail, setUsernameOrEmail] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await authService.login({
                usernameOrEmail,
                password
            });

            localStorage.setItem(
                "accessToken",
                response.data.accessToken
            );

            alert("Đăng nhập thành công");
            navigate("/home");

        } catch (err) {
            console.log(err.response?.data);
            alert("Sai tài khoản hoặc mật khẩu");
        }
    };

    return (
        <div>
            <h1>Đăng nhập</h1>

            <form onSubmit={handleLogin}>
                <input
                    type="text"
                    placeholder="Username hoặc Email"
                    value={usernameOrEmail}
                    onChange={(e) =>
                        setUsernameOrEmail(e.target.value)
                    }
                />

                <input
                    type="password"
                    placeholder="Mật khẩu"
                    value={password}
                    onChange={(e) =>
                        setPassword(e.target.value)
                    }
                />

                <button type="submit">
                    Đăng nhập
                </button>
            </form>

            <p>
                Chưa có tài khoản?
                <Link to="/register"> Đăng ký</Link>
            </p>
        </div>
    );
}