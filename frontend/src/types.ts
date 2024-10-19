export type TTechnologie = {
  id: number;
  name: string; // Название технологии
  ring: number; // Номер области на радаре
  moved: number; // Тенденция (1 - поднялась, -1 - опустилась)
  quadrant: number; // Номер сегмента
};

export type TTechnologiesList = {
  data: TTechnologie[];
};
