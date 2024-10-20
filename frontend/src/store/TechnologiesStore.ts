import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { TTechnologiesList, TTechnology } from '@/types';
import { useSelector } from 'react-redux';

const initialState: TTechnologiesList = {
  data: [],
};

export const technologiesSlice = createSlice({
  initialState: initialState,
  name: 'technologiesSlice',
  reducers: {
    setTechnologies: (state, action: PayloadAction<TTechnologiesList>) => {
      state.data = action.payload;
    },
  },
});

export const useTechnologies = () =>
  useSelector(
    (state: { technologiesData: TTechnologiesList }) =>
      state.technologiesData.data
  );

export const useFilteredTechnologies = (type: number): TTechnology[] => {
  const technologies = useSelector(
    (state: { technologiesData: TTechnologiesList }) =>
      state.technologiesData.data
  );

  return technologies.filter((tech) => tech.type === type);
};

export const { setTechnologies } = technologiesSlice.actions;

export default technologiesSlice.reducer;
