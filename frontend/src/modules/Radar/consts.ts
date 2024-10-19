import { TTechnologiesList } from '../../types';

export const mockTechnologies: TTechnologiesList = {
  data: [
    {
      id: 1,
      name: 'Redis',
      ring: 0,
      moved: 0,
      quadrant: 1,
    },
    {
      id: 2,
      name: 'Redis',
      ring: 1,
      moved: 0,
      quadrant: 2,
    },
    {
      id: 3,
      name: 'Redis',
      ring: 0,
      moved: 0,
      quadrant: 3,
    },
    {
      id: 4,
      name: 'Redis',
      ring: 2,
      moved: 0,
      quadrant: 4,
    },
    {
      id: 5,
      name: 'Redis',
      ring: 0,
      moved: 0,
      quadrant: 1,
    },
    {
      id: 6,
      name: 'Redis',
      ring: 1,
      moved: 0,
      quadrant: 2,
    },
    {
      id: 7,
      name: 'Redis',
      ring: 3,
      moved: 0,
      quadrant: 3,
    },
    {
      id: 8,
      name: 'Redis',
      ring: 0,
      moved: 0,
      quadrant: 4,
    },
    {
      id: 9,
      name: 'Redis',
      ring: 1,
      moved: 0,
      quadrant: 1,
    },
    {
      id: 10,
      name: 'Redis',
      ring: 2,
      moved: 0,
      quadrant: 2,
    },
    {
      id: 11,
      name: 'Redis',
      ring: 0,
      moved: 0,
      quadrant: 4,
    },
    {
      id: 12,
      name: 'Redis',
      ring: 1,
      moved: 0,
      quadrant: 1,
    },
    {
      id: 13,
      name: 'Redis',
      ring: 2,
      moved: 0,
      quadrant: 2,
    },
  ],
};
export const mockRings = [
  { label: 'Adopt', slug: 'adopt' },
  { label: 'Trial', slug: 'trial' },
  { label: 'Assess', slug: 'assess' },
  { label: 'Hold', slug: 'hold' },
];
export const mockElements = [
  { label: 'Amazon SageMaker', segment: 1, ring: 'assess' },
  {
    label: 'Microsoft Azure Machine Learning',
    segment: 1,
    ring: 'hold',
  },
  { label: 'IBM Watson', segment: 1, ring: 'trial' },
  { label: 'DataRobot', segment: 1, ring: 'adopt' },
  {
    label: 'Data labeling tools',
    segment: 2,
    ring: 'assess',
  },

  {
    label: 'Feature engineering methods',
    segment: 2,
    ring: 'adopt',
  },
  {
    label: 'Data quality assurance',
    segment: 2,
    ring: 'hold',
  },

  { label: 'Data lakes', segment: 2, ring: 'trial' },
  { label: 'TensorFlow', segment: 3, ring: 'trial' },
  { label: 'PyTorch', segment: 3, ring: 'hold' },
  { label: 'Keras', segment: 3, ring: 'assess' },
  { label: 'Apache MXNet', segment: 3, ring: 'adopt' },

  { label: 'Edge Computing', segment: 4, ring: 'adopt' },

  { label: 'Federated Learning', segment: 4, ring: 'hold' },
  { label: 'Edge AI', segment: 4, ring: 'trial' },
  { label: 'Swarm Intelligence', segment: 4, ring: 'assess' },
];
export const mockSegments = [
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
