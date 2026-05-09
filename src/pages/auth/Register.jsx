import { useState } from "react";
import { Link } from "react-router-dom";
import authService from "../../services/authApi";

export default function Register() {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleRegister = async (e) => {
        e.preventDefault();

        try {
            await authService.register({
                username,
                email,
                password
            });

            alert("Đăng ký thành công");
        } catch (err) {
            console.log(err.response?.data);
            alert("Đăng ký thất bại");
        }
    };

    return (
        <div>
            <h1>Đăng ký</h1>

            <form onSubmit={handleRegister}>
                <input
                    type="text"
                    placeholder="Username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />

                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />

                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />

                <button type="submit">
                    Đăng ký
                </button>
            </form>
            <p>
                Đã có tài khoản? <Link to="/login">Đăng nhập</Link>
            </p>
        </div>
    );
}