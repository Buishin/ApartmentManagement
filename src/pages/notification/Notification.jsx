import { useEffect, useState } from "react";
import notificationApi from "../../services/notificationApi";

export default function Notification() {
    const [list, setList] = useState([]);
    const [form, setForm] = useState({
        title: "",
        content: ""
    });

    useEffect(() => {
        fetchNotifications();
    }, []);

    const fetchNotifications = async () => {
        try {
            const res = await notificationApi.getAll();
            setList(res.data.content || []);
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

        if (!form.title.trim() || !form.content.trim()) {
            alert("Vui lòng nhập đầy đủ thông tin");
            return;
        }

        try {
            await notificationApi.create({
                title: form.title,
                content: form.content
            });

            alert("Tạo thông báo thành công");

            setForm({
                title: "",
                content: ""
            });

            fetchNotifications();
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="container py-4">

            {/* Form tạo thông báo */}
            <div className="card shadow mb-4">
                <div className="card-body">
                    <h3>Tạo thông báo</h3>

                    <form onSubmit={handleSubmit}>
                        <input
                            type="text"
                            name="title"
                            value={form.title}
                            onChange={handleChange}
                            placeholder="Tiêu đề"
                            className="form-control mb-3"
                        />

                        <textarea
                            name="content"
                            value={form.content}
                            onChange={handleChange}
                            placeholder="Nội dung thông báo"
                            className="form-control mb-3"
                            rows="4"
                        />

                        <button
                            type="submit"
                            className="btn btn-primary"
                        >
                            Tạo thông báo
                        </button>
                    </form>
                </div>
            </div>

            {/* Danh sách thông báo */}
            <div className="card shadow">
                <div className="card-body">
                    <h3>Danh sách thông báo</h3>

                    {list.length > 0 ? (
                        <table className="table table-hover mt-3">
                            <thead className="table-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>Tiêu đề</th>
                                    <th>Nội dung</th>
                                    <th>Ngày tạo</th>
                                </tr>
                            </thead>

                            <tbody>
                                {list.map((notification) => (
                                    <tr key={notification.id}>
                                        <td>{notification.id}</td>
                                        <td>{notification.title}</td>
                                        <td>{notification.content}</td>
                                        <td>{notification.createdAt}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    ) : (
                        <p className="text-muted mt-3">
                            Chưa có thông báo nào
                        </p>
                    )}
                </div>
            </div>
        </div>
    );
}