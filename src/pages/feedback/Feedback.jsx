import { useEffect, useState } from "react";
import feedbackApi from "../../services/feedbackApi";

export default function FeedbackPage() {
    const [list, setList] = useState([]);
    const [content, setContent] = useState("");

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const res = await feedbackApi.getAll();
            setList(res.data.content || []);
        } catch (error) {
            console.error(error);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!content.trim()) {
            alert("Vui lòng nhập nội dung");
            return;
        }

        try {
            await feedbackApi.create({
                content
            });

            setContent("");
            fetchData();
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="container py-4">
            <div className="card shadow mb-4">
                <div className="card-body">
                    <h3>Gửi phản ánh</h3>

                    <form onSubmit={handleSubmit}>
                        <textarea
                            className="form-control mb-3"
                            rows="4"
                            value={content}
                            onChange={(e) =>
                                setContent(e.target.value)
                            }
                            placeholder="Nhập nội dung phản ánh..."
                        />

                        <button
                            type="submit"
                            className="btn btn-primary"
                        >
                            Gửi phản ánh
                        </button>
                    </form>
                </div>
            </div>

            <div className="card shadow">
                <div className="card-body">
                    <h3>Danh sách phản ánh</h3>

                    {list.length > 0 ? (
                        <table className="table table-hover mt-3">
                            <thead className="table-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>Nội dung</th>
                                    <th>Trạng thái</th>
                                    <th>Cư dân</th>
                                </tr>
                            </thead>

                            <tbody>
                                {list.map((feedback) => (
                                    <tr key={feedback.id}>
                                        <td>{feedback.id}</td>
                                        <td>{feedback.content}</td>
                                        <td>{feedback.status}</td>
                                        <td>{feedback.residentName}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    ) : (
                        <p>Chưa có phản ánh nào</p>
                    )}
                </div>
            </div>
        </div>
    );
}