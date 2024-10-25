import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { TTechnologiesList, TTechnology } from '@/types';
import { useSelector } from 'react-redux';
import { useMemo } from 'react';

const initialState: TTechnology[] = [];

export const technologiesSlice = createSlice({
  name: 'technologiesSlice',
  initialState,
  reducers: {
    setTechnologies: (state, action: PayloadAction<TTechnology[]>) => {
      return [...action.payload];
    },
  },
});

export const useAllTechnologies = () =>
  useSelector(
    (state: { technologiesData: TTechnology[] }) => state.technologiesData
  );

export const useActiveTechnologies = (): TTechnology[] => {
  const technologies = useSelector(
    (state: { technologiesData: TTechnology[] }) => state.technologiesData
  );

  return useMemo(
    () => technologies.filter((tech) => tech.isActive === true),
    [technologies]
  );
};

export const useFilteredTechnologies = (type: number): TTechnology[] => {
  const technologies = useSelector(
    (state: { technologiesData: TTechnology[] }) => state.technologiesData
  );
  return technologies.filter((tech) => tech.type === type);
};

export const useTechnologyById = (id: number): TTechnology | undefined => {
  const technologies = useSelector(
    (state: { technologiesData: TTechnology[] }) => state.technologiesData
  );

  return technologies.find((tech) => tech.id === id);
};

export const { setTechnologies } = technologiesSlice.actions;

export default technologiesSlice.reducer;
