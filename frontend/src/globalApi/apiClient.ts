import axios, { AxiosInstance } from 'axios';
import { API_BASE_URL } from '@/globalConsts';

const apiClient: AxiosInstance = axios.create({
  baseURL: `${API_BASE_URL}/api/v1/`,
  headers: {
    'Content-Type': 'application/json',
  },
});

export default apiClient;
