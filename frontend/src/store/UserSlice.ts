import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { TUser } from '@/types';
import { useSelector } from 'react-redux';

interface IUserState {
  user: TUser;
  isAuth: boolean;
}

const initialState: IUserState = {
  user: {
    id: -1,
    email: '',
    admin: false,
    specialization: '',
  },
  isAuth: false,
};

export const userSlice = createSlice({
  initialState: initialState,
  name: 'userSlice',
  reducers: {
    clearUser: () => initialState,
    setUser: (state, action: PayloadAction<TUser>) => {
      state.user = action.payload;
      state.isAuth = true;
    },
  },
});
/**
 * Получение информации о пользователя из store
 */
export const useUserInfo = () =>
  useSelector((state: { userData: IUserState }) => state.userData);

/**
 * Получение информации об авторизации пользователя из store
 */
export const useAuth = () =>
  useSelector((state: { userData: IUserState }) => state.userData.isAuth);

export const { clearUser, setUser } = userSlice.actions;
export default userSlice.reducer;
