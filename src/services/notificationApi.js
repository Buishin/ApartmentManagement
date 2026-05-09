import api from "../api/api";


const notificationApi = {
    getAll(params) {
        return api.get("/notifications", { params });
    },

    getById(id) {
        return api.get(`/notifications/${id}`);
    },

    getMyNotifications(params) {
        return api.get("/notifications/my", { params });
    },

    getMyNotificationDetail(id) {
        return api.get(`/notifications/my/${id}`);
    },

    create(data) {
        return api.post("/notifications", data);
    }
};

export default notificationApi;