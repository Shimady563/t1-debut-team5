import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { TUser } from '@/types';
import { useSelector } from 'react-redux';

interface IUserState {
  user: TUser | null;
  isAuth: boolean;
}

const initialState: IUserState = {
  user: null,
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

export const useUserInfo = () =>
  useSelector((state: { userData: IUserState }) => state.userData.user);

export const useAuth = () =>
  useSelector((state: { userData: IUserState }) => state.userData.isAuth);

export const { clearUser, setUser } = userSlice.actions;
export default userSlice.reducer;
