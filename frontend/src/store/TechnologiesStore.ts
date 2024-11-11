import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { TTechnology } from '@/types';
import { useSelector } from 'react-redux';
import { useMemo } from 'react';

const initialState: TTechnology[] = [];

export const technologiesSlice = createSlice({
  name: 'technologiesSlice',
  initialState,
  reducers: {
    setTechnologies: (state, action: PayloadAction<TTechnology[]>) => {
      state;
      return [...action.payload];
    },
  },
});

/**
 * Получение всех технологий из store
 */
export const useAllTechnologies = () =>
  useSelector(
    (state: { technologiesData: TTechnology[] }) => state.technologiesData
  );

/**
 * Получение активных технологий из store
 */
export const useActiveTechnologies = (): TTechnology[] => {
  const technologies = useSelector(
    (state: { technologiesData: TTechnology[] }) => state.technologiesData
  );

  return useMemo(
    () => technologies.filter((tech) => tech.isActive === true),
    [technologies]
  );
};
/**
 * Получение активных технологий заданной области из store
 */
export const useFilteredActiveTechnologies = (type: number): TTechnology[] => {
  const technologies = useSelector(
    (state: { technologiesData: TTechnology[] }) => state.technologiesData
  );

  return useMemo(
    () => technologies.filter((tech) => tech.isActive && tech.type === type),
    [type, technologies]
  );
  // return technologies.filter((tech) => tech.type === type);
};
/**
 * Получение всех технологий заданной области из store
 */
export const useFilteredTechnologies = (type: number): TTechnology[] => {
  const technologies = useSelector(
    (state: { technologiesData: TTechnology[] }) => state.technologiesData
  );

  return useMemo(
    () => technologies.filter((tech) => tech.type === type),
    [type, technologies]
  );
  // return technologies.filter((tech) => tech.type === type);
};

/**
 * Получение технологии по id из store
 */
export const useTechnologyById = (id: number): TTechnology | undefined => {
  const technologies = useSelector(
    (state: { technologiesData: TTechnology[] }) => state.technologiesData
  );

  return technologies.find((tech) => tech.id === id);
};

export const { setTechnologies } = technologiesSlice.actions;

export default technologiesSlice.reducer;
