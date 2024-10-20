import { TTechnologiesList } from '../../types';

export const mockLevels = [
  { label: 'Adopt', slug: 'Adopt' },
  { label: 'Trial', slug: 'Trial' },
  { label: 'Assess', slug: 'Assess' },
  { label: 'Hold', slug: 'Hold' },
];

// {
//   label: 'Microsoft Azure Machine Learning',
//   segment: 1,
//   ring: 'hold',
// },

export const mockElements = [
  { id: 1, name: 'Amazon1', moved: 0, type: 1, level: 'Assess' },
  { id: 2, name: 'DataRobot', moved: 0, type: 2, level: 'Hold' },
  { id: 3, name: 'Amazon3', moved: 0, type: 3, level: 'Trial' }
]

export const mockTypes = [
  {
    id: 1,
    label: 'Platform & Infrastructure',
    slug: 1,
    color: '#fa9',
  },
  { id: 2, label: 'Data management', slug: 2, color: '#ad0' },
  { id: 3, label: 'Languages & Frameworks', slug: 3, color: '#7dc' },
  { id: 4, label: 'Techniques & Tools', slug: 4, color: '#caf' },
];

export const mockOptions = {
  totalAngle: Math.PI * 2,
  minPlotRadius: 120,
  baseDimension: 620,
};
