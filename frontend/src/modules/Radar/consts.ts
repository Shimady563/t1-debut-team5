import { TTechnologiesList } from '../../types';

export const mockLevels = [
  { id: 0, label: 'Adopt', slug: 'Adopt' },
  { id: 1, label: 'Trial', slug: 'Trial' },
  { id: 2, label: 'Assess', slug: 'Assess' },
  { id: 3, label: 'Hold', slug: 'Hold' },
];

export const mockElements = [
  {
    id: 1,
    name: 'Amazon1 Platforms',
    moved: 0,
    type: 0,
    level: 'Hold',
    active: 1,
  },
  {
    id: 2,
    name: 'DataRobot1 Databases',
    moved: 0,
    type: 2,
    level: 'Hold',
    active: 1,
  },
  {
    id: 3,
    name: 'Amazon1 Tools',
    moved: 0,
    type: 3,
    level: 'Trial',
    active: 1,
  },
  {
    id: 4,
    name: 'Amazon2 Platforms',
    moved: 0,
    type: 0,
    level: 'Trial',
    active: 1,
  },
  {
    id: 5,
    name: 'DataRobot2 Databases',
    moved: 0,
    type: 2,
    level: 'Assess',
    active: 1,
  },
  {
    id: 6,
    name: 'Amazon2 Tools',
    moved: 0,
    type: 3,
    level: 'Assess',
    active: 1,
  },
  {
    id: 7,
    name: 'Amazon3 Platforms',
    moved: 0,
    type: 0,
    level: 'Adopt',
    active: 1,
  },
  {
    id: 8,
    name: 'DataRobot3 Databases',
    moved: 0,
    type: 2,
    level: 'Trial',
    active: 1,
  },
  {
    id: 9,
    name: 'Amazon3 Tools',
    moved: 0,
    type: 3,
    level: 'Trial',
    active: 1,
  },
  {
    id: 10,
    name: 'Amazon1 Languages',
    moved: 0,
    type: 1,
    level: 'Trial',
    active: 1,
  },
  {
    id: 11,
    name: 'Amazon2 Languages',
    moved: 0,
    type: 1,
    level: 'Adopt',
    active: 1,
  },
  {
    id: 12,
    name: 'Amazon3 Languages',
    moved: 0,
    type: 1,
    level: 'Hold',
    active: 1,
  },
];

export const mockTypes = [
  {
    id: 0,
    label: 'Platforms',
    slug: 0,
    color: '#fa9',
  },
  { id: 1, label: 'Languages', slug: 1, color: '#7dc' },

  { id: 2, label: 'Databases', slug: 2, color: '#ad0' },
  { id: 3, label: 'Tools', slug: 3, color: '#caf' },
];

export const mockOptions = {
  totalAngle: Math.PI * 2,
  minPlotRadius: 120,
  baseDimension: 620,
};

export const mockStatuses = [
  {
    id: -1,
    label: 'Понижено',
  },
  {
    id: 0,
    label: 'Без изменений',
  },
  {
    id: 1,
    label: 'Повышено',
  },
];
