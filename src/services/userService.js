import api from "../api/api";

const userService = {

    getAllUsers() {
        return api.get("/users");
    }
};

export default userService;