import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  timeout: 10000,//10s
  headers: {
    'Content-Type': 'application/json',
    Accept: 'application/json'
  }
})
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("accessToken");

    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config
  },
  (error) => {
    return Promise.reject(error);
  }
);
api.interceptors.response.use(
  (response) => {
    return response
  },
  (error) => {
    const status = error.response?.status
    if (status === 401) {
      localStorage.removeItem('accessToken')
      window.location.href = '/login'
    }
    if (status === 403) {
      console.error('Bạn không có quyền truy cập resource này')
    }
    if (status >= 500) {
      console.error('lỗi server — vui lòng thử lại sau')
    }
    return Promise.reject(error)
  }
)


export default api