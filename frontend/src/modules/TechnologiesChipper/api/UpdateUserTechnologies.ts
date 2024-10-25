import axios from 'axios';
import { toast } from 'react-toastify';

export const useUpdateUserTechnologies = () => {
  const UserTechnologiesUpdateRequest = async (data: number[]) => {
    try {
      const token = document.cookie
        .split('; ')
        .find((row) => row.startsWith('jwt='))
        ?.split('=')[1];
      const response = await axios(
        `http://localhost:8080/api/v1/users/technology`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
          data: data,
          method: 'PUT',
        }
      );
      toast.success('Ваш стек успешно изменён!');
    } catch {
      toast.error('Упс...Что-то пошло не так');
    }
  };

  return UserTechnologiesUpdateRequest;
};
