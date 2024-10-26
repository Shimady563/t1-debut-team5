import axios from 'axios';
import { useEffect, useState } from 'react';
import { toast } from 'react-toastify';

export const useUserTechnologies = () => {
  const [userTechnologies, setUserTechnologies] = useState<any>([]);

  const UserTechnologiesRequest = async () => {
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
          method: 'GET',
        }
      );
      console.log(response.data.technologies);
      setUserTechnologies(response.data.technologies);
    } catch {
      toast.success('Упс...Что-то пошло не так');
    }
  };

  useEffect(() => {
    UserTechnologiesRequest();
  }, []);

  return userTechnologies;
};
