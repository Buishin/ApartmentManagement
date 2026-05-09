import api from "./api";

const paymentApi = {
    getAll(params) {
        return api.get("/payments", { params });
    },

    getById(id) {
        return api.get(`/payments/${id}`);
    },

    getMyPayments(params) {
        return api.get("/payments/my", { params });
    },

    getMyPaymentDetail(id) {
        return api.get(`/payments/my/${id}`);
    },

    create(data) {
        return api.post("/payments", data);
    }
};

export default paymentApi;