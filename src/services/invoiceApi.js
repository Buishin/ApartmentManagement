import api from "./api";

const invoiceApi = {
    getAll(params) {
        return api.get("/invoices", { params });
    },

    getById(id) {
        return api.get(`/invoices/${id}`);
    },

    getMyInvoices(params) {
        return api.get("/invoices/my", { params });
    },

    getMyInvoiceDetail(id) {
        return api.get(`/invoices/my/${id}`);
    },

    create(data) {
        return api.post("/invoices", data);
    },

    update(id, data) {
        return api.put(`/invoices/${id}`, data);
    }
};

export default invoiceApi;