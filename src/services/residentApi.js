import api from "../api/api";


const residentApi = {
    getAll(params) {
        return api.get("/residents", { params });
    },

    getById(id) {
        return api.get(`/residents/${id}`);
    },

    getMyProfile() {
        return api.get("/residents/me");
    },

    create(data) {
        return api.post("/residents", data);
    },

    update(id, data) {
        return api.put(`/residents/${id}`, data);
    }
};

export default residentApi;