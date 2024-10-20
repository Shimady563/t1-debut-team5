import { TTechnologiesList } from '../../types';

export const mockLevels = [
  { label: 'Adopt', slug: 'adopt' },
  { label: 'Trial', slug: 'trial' },
  { label: 'Assess', slug: 'assess' },
  { label: 'Hold', slug: 'hold' },
];

// {
//   label: 'Microsoft Azure Machine Learning',
//   segment: 1,
//   ring: 'hold',
// },

export const mockElements = [
  { id: 1, name: 'Amazon1', moved: 0, type: 1, level: 'assess' },
  { id: 2, name: 'DataRobot', moved: 0, type: 2, level: 'hold' },
  { id: 3, name: 'Amazon3', moved: 0, type: 3, level: 'trial' },
  { id: 4, name: 'Edge AI', moved: 0, type: 4, level: 'assess' },
  { id: 5, name: 'Amazon5', moved: 0, type: 1, level: 'hold' },
  { id: 6, name: 'Data labeling tools', moved: 0, type: 2, level: 'adopt' },
  { id: 7, name: 'Amazon7', moved: 0, type: 3, level: 'assess' },
  { id: 8, name: 'Federated Learning', moved: 0, type: 4, level: 'adopt' },
  { id: 9, name: 'Amazon9', moved: 0, type: 1, level: 'trial' },
  {
    id: 10,
    name: 'Feature engineering methods',
    moved: 0,
    type: 2,
    level: 'assess',
  },
  { id: 11, name: 'TensorFlow', moved: 0, type: 3, level: 'hold' },
];

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
