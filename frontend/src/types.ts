export type TTechnology = {
  id: number;
  name: string; // Название технологии
  level: string; // Номер области на радаре
  moved: number; // Тенденция (1 - поднялась, -1 - опустилась)
  type: number; // Номер сегмента
  isActive: boolean;
};
export type TUserVoteResponse = {
  id: number;
  level: string;
  technology: TTechnology;
};

export type TPoll = {
  technology: TTechnology;
  isVoted: boolean;
  level: string;
};

export type TTechnologiesList = {
  data: TTechnology[];
};

export type TUser = {
  id: number;
  email: string;
  admin: boolean;
  specialization: string;
};

export type TLevel = {
  label: string;
  slug: string;
};

export type TType = {
  id: number;
  label: string;
  slug: number;
  color: string;
};

export type TSpecialization = {
  id: number;
  label: string;
};

export type TVoteStat = {
  technology: TTechnology;
  votes: {
    Assess?: number;
    Hold?: number;
    Adopt?: number;
    Trial?: number;
  };
};
