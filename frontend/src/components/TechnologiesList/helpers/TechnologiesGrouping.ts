import { TTechnology } from '@/types';

export const groupTechnologies = (
  technologies: TTechnology[]
): Record<number, TTechnology[]> => {
  return technologies.reduce((acc, tech) => {
    if (!acc[tech.type]) {
      acc[tech.type] = [];
    }
    acc[tech.type].push(tech);
    return acc;
  }, {} as Record<number, TTechnology[]>);
};
