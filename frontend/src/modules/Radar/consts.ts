import { TTechnologiesList } from '../../types';

export const mockLevels = [
  { label: 'Adopt', slug: 'Adopt' },
  { label: 'Trial', slug: 'Trial' },
  { label: 'Assess', slug: 'Assess' },
  { label: 'Hold', slug: 'Hold' },
];

export const mockElements = [
  { id: 1, name: 'Amazon1', moved: 0, type: 0, level: 'Assess' },
  { id: 2, name: 'DataRobot', moved: 0, type: 2, level: 'Hold' },
  { id: 3, name: 'Amazon3', moved: 0, type: 3, level: 'Trial' },
];

export const mockTypes = [
  {
    id: 0,
    label: 'Platform & Infrastructure',
    slug: 0,
    color: '#fa9',
  },
  { id: 1, label: 'Data management', slug: 1, color: '#ad0' },
  { id: 2, label: 'Languages & Frameworks', slug: 2, color: '#7dc' },
  { id: 3, label: 'Techniques & Tools', slug: 3, color: '#caf' },
];

export const mockOptions = {
  totalAngle: Math.PI * 2,
  minPlotRadius: 120,
  baseDimension: 620,
};
