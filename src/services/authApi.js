import api from "../api/api";

const authService = {
    login(data) {
        return api.post("/auth/login", data);
    },

    register(data) {
        return api.post("/auth/register", data);
    },
    logout() {
        localStorage.removeItem("accessToken");
    },
    getProfile() {
        return api.get("auth/me");
    }
};

export default authService;