export type TTechnology = {
  id: number;
  name: string; // Название технологии
  level: string; // Номер области на радаре
  moved: number; // Тенденция (1 - поднялась, -1 - опустилась)
  type: number; // Номер сегмента
  active: boolean;
};

export type TTechnologiesList = {
  data: TTechnology[];
};

export type TUser = {
  id: number;
  email: string;
  isAdmin: boolean;
};

export type TLevel = {
  level: string;
  slug: string;
};
