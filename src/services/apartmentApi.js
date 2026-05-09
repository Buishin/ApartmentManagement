import api from "../api/api";

const apartmentApi = {
    getAll() {
        return api.get("/apartments");
    },

    getById(id) {
        return api.get(`/apartments/${id}`);
    },

    create(data) {
        return api.post("/apartments", data);
    },

    update(id, data) {
        return api.put(`/apartments/${id}`, data);
    }
};

export default apartmentApi;