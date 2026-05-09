import api from "../api/api";

const feedbackApi = {
    getAll() {
        return api.get("/feedbacks");
    },

    create(data) {
        return api.post("/feedbacks", data);
    }
};

export default feedbackApi;